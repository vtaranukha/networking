package ru.sbt.javaschool.ChatHT2;

import ru.sbt.javaschool.ChatBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

import static java.lang.System.console;
import static java.lang.System.out;

/** для старта используем java -jar school-chat-1.0-SNAPSHOT.jar
 * Created by Home on 01.02.2017.
 */
public class Client extends ChatBase {

    public static void main(String args[]){
        port = ChatBase.getPort(args);

        Socket socket = new Socket();
        try {
            InetSocketAddress target = new InetSocketAddress(Inet4Address.getLocalHost(), port);
            out.println("Connecting to" + target.toString());
            socket.connect(target);
            out.println("Connected. "+ socket.toString());

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;
            while ((input = reader.readLine()) != null) {
                out.println("Вопрос сервера: " + input);
                String output = console().readLine();
                out.println("Client says " + output);

                writer.println(output);
                writer.flush();

                input = reader.readLine();
                out.println("Server says " + input);

                if ("Yes, it's correct!".equals(input))
                    out.println("Ура, угадали!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
