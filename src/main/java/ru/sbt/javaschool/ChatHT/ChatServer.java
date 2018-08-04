package ru.sbt.javaschool.ChatHT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;


public class ChatServer {
    public static void main(String[] args) {

        try {

            byte[] data = new byte[1000];
            data = "sdfsdf".getBytes();
            DatagramSocket s = null;
            try {
                s = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            InetAddress addr = InetAddress.getLocalHost();
            DatagramPacket pac;
            pac = new DatagramPacket(data, data.length, addr, 8033);
            try {
                if (s != null) {
                    s.send(pac);//отправление пакета
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Сообщение отправлено");
        } catch (UnknownHostException e) {
            // неверный адрес получателя
            e.printStackTrace();
        }

    }
}
