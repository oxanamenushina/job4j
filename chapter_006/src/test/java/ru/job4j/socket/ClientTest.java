package ru.job4j.socket;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version $Id$
 * @since 0.1
 */
public class ClientTest {

    private void testClient(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.start(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAskExitThenExit() throws IOException {
        this.testClient("exit", new StringBuilder().append("exit").append(System.lineSeparator()).toString());
    }

    @Test
    public void whenAskQuestionThenReturnThisQuestion() throws IOException {
        this.testClient(new StringBuilder()
                        .append("How are you?")
                        .append(System.lineSeparator())
                        .append("exit")
                        .toString(),
                new StringBuilder()
                        .append("How are you?")
                        .append(System.lineSeparator())
                        .append("exit")
                        .append(System.lineSeparator())
                        .toString());
    }

    @Test
    public void whenAskHelloThenReturnHello() throws IOException {
        this.testClient(new StringBuilder()
                        .append("Hello")
                        .append(System.lineSeparator())
                        .append("exit")
                        .toString(),
                new StringBuilder()
                        .append("Hello")
                        .append(System.lineSeparator())
                        .append("exit")
                        .append(System.lineSeparator())
                        .toString());
    }
}