package ru.sbt.javaschool;

/**
 * Created by Home on 01.02.2017.
 */
public abstract class ChatBase {
    protected static int port;

    public static int DEFAULT_PORT = 12345;

    protected static int getPort(String[] args) {
        int port = DEFAULT_PORT;

        if (args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e){
                e.printStackTrace();
                port = DEFAULT_PORT;
            }
        }

        return port;
    }
}
