package ru.sbt.javaschool.example1;

import ru.sbt.javaschool.ChatBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.util.Calendar;

import static java.lang.System.*;

/**
 * Created by Home on 01.02.2017.
 */
// java -cp target/net.jar ru.sbt.javaschool.example1.ServerChat
// java -cp target/net.jar ru.sbt.javaschool.example1.ClientChat
public class ServerChat extends ChatBase {

    public static void main( String args[] ) {
        port = ChatBase.getPort( args );

        try {
            ServerSocket serverSocket = new ServerSocket( port );

            out.println( "Register listener on " + serverSocket.getInetAddress().toString() + ":" + port );
            do {
                out.println( "Waiting new client..." );
                try ( Socket client = serverSocket.accept() ) {
                    out.println( "Connected client " + client.getInetAddress().toString() + ":" + client.getPort() );

                    BufferedReader reader = new BufferedReader( new InputStreamReader( client.getInputStream() ) );
                    PrintWriter writer = new PrintWriter( client.getOutputStream() );

                    writer.println( "Server " + ZonedDateTime.now() );
                    writer.flush();

                    out.println( "From client " + reader.readLine() );

                    out.println( "Closing client connection..." );
                    client.shutdownInput();
                    client.shutdownOutput();
                    client.close();
                    out.println( "Closed" );
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
            while ( true );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
