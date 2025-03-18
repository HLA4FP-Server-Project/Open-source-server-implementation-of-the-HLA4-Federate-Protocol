package com.rug.tno;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {
        Integer port = 15164;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        new Server(port).run();
    }
}
