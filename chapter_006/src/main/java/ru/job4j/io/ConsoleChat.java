package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ConsoleChat {

    boolean stop = false;

    /**
     * Метод создает консольный чат.
     * @param source путь и название файла с текстом.
     * @param target путь и название файла для записи лога чата.
     */
    public void chat(String source, String target) {
        List<String> text = readText(source);
        this.info();
        String log = getLog(text);
        writeLog(target, log);
    }

    /**
     * Метод возвращает весь чат, записанный в строку.
     * @param text список фраз из текстового файла.
     * @return весь чат, записанный в строку.
     */
    private String getLog(List<String> text) {
        Random rand = new Random();
        StringBuilder log = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String inp = br.readLine();
            while (!"закончить".equals(inp)) {
                isStop(inp);
                log.append(inp).append(System.lineSeparator());
                if (!this.stop) {
                    String answer = text.size() != 0 ? text.get(rand.nextInt(text.size() - 1)) : "";
                    System.out.println(answer);
                    log.append(answer).append(System.lineSeparator());
                }
                inp = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log.toString();
    }

    /**
     * Метод записывает лог чата в файл.
     * @param target путь и название файла для записи лога чата.
     * @param log строка с чатом.
     */
    private void writeLog(String target, String log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            bw.write(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод считывает из текстового файла фразы в список.
     * @param source путь и название файла с текстом.
     * @return список фраз из текстового файла.
     */
    private List<String> readText(String source) {
        List<String> text = new ArrayList<>();
        try (BufferedReader fr = new BufferedReader(new FileReader(source))) {
            text = fr.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Метод проверяет введено ли слово "стоп" или "продолжить",
     * если введено какое-либо из этих слов,
     * то изменяет соответственно значение поля stop.
     * @param inp введенная фраза.
     */
    private void isStop(String inp) {
        if ("стоп".equals(inp)) {
            this.stop = true;
        }
        if ("продолжить".equals(inp)) {
            this.stop = false;
        }
    }

    /**
     * Метод выводит на консоль вводную информацию о чате.
     */
    private void info() {
        System.out.println(new StringBuilder()
                .append("Введите фразу.")
                .append(System.lineSeparator())
                .append("стоп - программа замолкает;")
                .append(System.lineSeparator())
                .append("продолжить - программа снова начинает отвечать;")
                .append(System.lineSeparator())
                .append("закончить - программа прекращает работу.")
                .append(System.lineSeparator())
                .toString());
    }

    public static void main(String[] args) {
        ConsoleChat ch = new ConsoleChat();
        ch.chat("text.txt", "logChat.txt");
    }
}
