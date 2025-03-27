package com.rug.tno.url;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that allows for the generation of one-time-use urls. These urls
 * can refer to arbitrary data and can be read by any java code running in this vm.
 * Once a connection to one of these urls is opened, the data is freed.
 */
public class OneTimeUseUrl {
    private static final OneTimeUseUrl INSTANCE = new OneTimeUseUrl();

    private final Map<String, ByteBuffer> urls = new HashMap<>();
    private HttpServer server;
    private Lock serverLock = new ReentrantLock();

    public String registerUrl(ByteBuffer bytes, String filename, String extension) {
        ensureServerRunning();

        String path;
        do {
            var uuid = UUID.randomUUID().toString();
            path = filename == null ? uuid : uuid+"/"+filename;
            if (extension != null) {
                path += "." + extension;
            }
            path = "/" + path;
        } while (urls.containsKey(path)); // Ensure we haven't by chance generated the same name twice
        urls.put(path, bytes);

        return "http://127.0.0.1:"+server.getAddress().getPort()+path;
    }

    private void ensureServerRunning() {
        serverLock.lock();
        try {
            if (server == null) {
                    server = HttpServer.create(new InetSocketAddress(0), 0);
                    server.createContext("/", this::handleConnection);
                    server.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            serverLock.unlock();
        }
    }

    private void handleConnection(HttpExchange exchange) throws IOException {
        var p = exchange.getRequestURI().getPath();
        var bytes = urls.get(p);
        if (bytes == null) {
            exchange.sendResponseHeaders(404, 0);
            exchange.getResponseBody().close();
        } else {
            bytes.mark();
            exchange.sendResponseHeaders(200, bytes.remaining());
            var output = exchange.getResponseBody();
            var c = Channels.newChannel(output);
            c.write(bytes);
            bytes.reset();
            c.close();

            // One-time-use
            urls.remove(p);
            closeServerIfUseless();
        }
    }

    private void closeServerIfUseless() {
        serverLock.lock();
        try {
            if (this.urls.isEmpty()) {
                this.server.stop(10);
                this.server = null;
            }
        } finally {
            serverLock.unlock();
        }
    }

    public static String generateUrl(ByteBuffer bytes, String filename, String extension) {
        return INSTANCE.registerUrl(bytes, filename, extension);
    }
}
