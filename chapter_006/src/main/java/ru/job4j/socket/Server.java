package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version $Id$
 * @since 0.1
 */
public class Server {

    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод запускает сервер, который отвечает на вопросы клиента.
     * Если Ораклу сказали пока, то приложение выключается.
     */
    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("Hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm an oracle.");
                    out.println();
                } else if (!"exit".equals(ask)) {
                    out.println("I don't understand.");
                    out.println();
                }
            } while (!"exit".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(11111).accept()) {
            Server serv = new Server(socket);
            serv.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}