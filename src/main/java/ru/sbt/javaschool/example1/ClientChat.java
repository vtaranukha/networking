package ru.sbt.javaschool.example1;

import ru.sbt.javaschool.ChatBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Calendar;

import static java.lang.System.*;

/**
 * Created by Home on 01.02.2017.
 */
public class ClientChat extends ChatBase {

    public static void main( String args[] ) {
        port = ChatBase.getPort( args );

        Socket socket = new Socket();
        try {
            InetSocketAddress target = new InetSocketAddress( Inet4Address.getLocalHost(), port );
            out.println( "Connecting to" + target.toString() );
            socket.connect( target );
            out.println( "Connected. " + socket.toString() );

            PrintWriter writer = new PrintWriter( socket.getOutputStream() );

            String ourId = "Client_" + Calendar.getInstance().getTimeInMillis();
            out.println( "OurId " + ourId );
            writer.println( ourId );
            writer.flush();

            BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            String input;
            while ( ( input = reader.readLine() ) != null )
                out.println( "Server says " + input );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
