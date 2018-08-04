package ru.sbt.javaschool.example2;

import ru.sbt.javaschool.ChatBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Random;

import static java.lang.System.*;

/**
 * Created by Home on 01.02.2017.
 */

public class FortuneTellerServer extends ChatBase {

    public static void main(String args[]){
        port = ChatBase.getPort(args);

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            Random random = new Random(Calendar.getInstance().getTimeInMillis());

            out.println("Register listener on " + serverSocket.getInetAddress().toString() + ":" + port);
            do{
                out.println("Waiting new client...");
                Socket client = serverSocket.accept();
                FortuneTellerThread teller = new FortuneTellerThread(client, random.nextInt(100));
                out.println("Start talking...");
                teller.start();
            }
            while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
