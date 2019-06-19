package ru.job4j.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAskExitThenBotStop() throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenAskQuestionThenReturnIDontUnderstand() throws IOException {
        this.testServer(new StringBuilder()
                        .append("How are you?")
                        .append(System.lineSeparator())
                        .append("exit")
                        .toString(),
                new StringBuilder()
                        .append("I don't understand.")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString());
    }

    @Test
    public void whenAskHelloThenReturnGreeting() throws IOException {
        this.testServer(new StringBuilder()
                        .append("Hello")
                        .append(System.lineSeparator())
                        .append("exit")
                        .toString(),
                new StringBuilder()
                        .append("Hello, dear friend, I'm an oracle.")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString());
    }
}