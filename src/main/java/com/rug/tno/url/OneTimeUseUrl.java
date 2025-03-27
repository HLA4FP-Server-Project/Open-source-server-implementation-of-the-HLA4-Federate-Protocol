package com.rug.tno.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Class that allows for the generation of one-time-use urls. These urls
 * can refer to arbitrary data and can be read by any java code running in this vm.
 * Once a connection to one of these urls is opened, the data is freed.
 */
public class OneTimeUseUrl {
    private static Map<String, ByteBuffer> URLS = new HashMap<>();

    static {
        URL.setURLStreamHandlerFactory(protocol -> {
            if (Objects.equals(protocol, "one-time-use-url")) {
                return new OneTimeUrlStreamHandler();
            }

            return null;
        });
    }

    public static String generateUrl(ByteBuffer bytes, String filename, String extension) {
        String name;
        do {
            var uuid = UUID.randomUUID().toString();
            name = filename == null ? uuid : uuid+"/"+filename;
            if (extension != null) {
                name += "." + extension;
            }
            name = "/" + name;
        } while (URLS.containsKey(name)); // Ensure we haven't by chance generated the same name twice
        URLS.put(name, bytes);
        return "one-time-use-url://"+name;
    }

    private static class OneTimeUrlStreamHandler extends URLStreamHandler {
        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            var name = u.getPath();
            var bytes = URLS.get(name);
            if (bytes == null) {
                throw new IOException(name+" is not a valid one-time-use url");
            }
            // Remove it, since it's one-time-use
            URLS.remove(name);

            return new ByteUrlConnection(bytes, u);
        }
    }

    private static class ByteUrlConnection extends URLConnection {
        private final ByteBuffer bytes;
        private final long size;

        private ByteUrlConnection(ByteBuffer bytes, URL url) {
            super(url);
            this.bytes = bytes;
            this.size = bytes.remaining();
        }

        @Override
        public void connect() throws IOException {

        }

        @Override
        public long getContentLengthLong() {
            return bytes.remaining();
        }

        @Override
        public String getContentType() {
            return "application/octet-stream";
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteBufferBackedInputStream(this.bytes);
        }
    }

    private static class ByteBufferBackedInputStream extends InputStream {
        private final ByteBuffer buf;

        public ByteBufferBackedInputStream(ByteBuffer buf) {
            this.buf = buf;
        }

        public int read() throws IOException {
            if (!buf.hasRemaining()) {
                return -1;
            }
            return buf.get() & 0xFF;
        }

        public int read(byte[] bytes, int off, int len)  {
            if (!buf.hasRemaining()) {
                return -1;
            }

            len = Math.min(len, buf.remaining());
            buf.get(bytes, off, len);
            return len;
        }
    }
}
