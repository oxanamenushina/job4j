package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @version $Id$
 * @since 0.1
 */
public class Client {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод запускает клиента, который отправляет вопросы Ораклу.
     * @param reader источник ввода вопросов.
     */
    public void start(BufferedReader reader) {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String question;
            do {
                question = reader.readLine();
                out.println(question);
                String str = in.readLine();
                while (str != null && !str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!"exit".equals(question));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("localhost"), 11111)) {
            Client cl = new Client(socket);
            cl.start(new BufferedReader(new InputStreamReader(System.in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}