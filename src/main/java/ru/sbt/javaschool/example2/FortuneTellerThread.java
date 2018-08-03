package ru.sbt.javaschool.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.*;

/**
 * Created by Home on 01.02.2017.
 */
public class FortuneTellerThread extends Thread {
    private Socket socket;
    private int magicNumber;

    public FortuneTellerThread(Socket client, int magicNumber) {
        socket = client;
        this.magicNumber = magicNumber;
    }

    @Override
    public void run() {
        long id = this.getId();
        try {
            out.println(id + " Connected client " + socket.getInetAddress().toString() + ":" + socket.getPort());
            out.println(id + " My Magic Number is " + magicNumber);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            int test;
            do {
                writer.println("Tell me, what does number that i thought?"); writer.flush();

                String clientAnswers = reader.readLine();
                out.println(id + " Client says " + clientAnswers);
                try{  test = Integer.parseInt(clientAnswers); }
                catch (NumberFormatException e){
                    out.println(id + " Client jokes " + e.getMessage());
                    writer.println("Wrong number, try again!"); writer.flush();
                    test = magicNumber - 1;
                }

                if (magicNumber == test) break;
                else writer.println("Wrong, try again!");
            } while (true);

            writer.println("Yes, it's correct!"); writer.flush();
            out.println(id + " Client knows O_o");

        } catch (IOException e) { e.printStackTrace(); }
        finally {
            out.println(id + " Closing client connection...");
            if (socket != null){
                try {
                    socket.shutdownOutput();
                    socket.shutdownInput();
                    socket.close();
                    out.println(id + " Closed");
                }
                catch (IOException ioe){ ioe.printStackTrace(); }
            }
        }
    }
}
