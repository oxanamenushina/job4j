package ru.job4j.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class FileSearch {

    private final String dir;
    private final String name;
    private final String crit;
    private final String output;

    public FileSearch(Arguments args) {
        args.validate();
        this.dir = args.directory();
        this.name = args.fileName();
        this.crit = args.criterion();
        this.output = args.output();
    }

    /**
     * Метод осуществляет поиск файлов.
     */
    public void findFiles() {
        info();
        StringBuilder result = new StringBuilder();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(this.dir));
        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] files = current.listFiles();
            if (current.isDirectory() && files != null) {
                for (File file : files) {
                    queue.offer(file);
                }
            } else if ("-m".equals(this.crit) ? equalsByMask(current.getName()) : this.name.equals(current)) {
                result.append(current.getName()).append(System.lineSeparator());
            }
        }
        writeFile(result.toString());
    }

    /**
     * Метод метод выводит на консоль информацию о входных параметрах.
     */
    private void info() {
        System.out.println(new StringBuilder()
                .append("Введите ключи:")
                .append(System.lineSeparator())
                .append("-d - директория в которой начинать поиск;")
                .append(System.lineSeparator())
                .append("-n - имя файла или маска;")
                .append(System.lineSeparator())
                .append("В маске символ * - произвольное количество (в том числе 0) любых символов;")
                .append(System.lineSeparator())
                .append("-m - искать по макс, либо -f - полное совпадение имени;")
                .append(System.lineSeparator())
                .append("-o - файл для записи результата.")
                .append(System.lineSeparator())
                .toString());
    }

    /**
     * Метод записывает имена найденных файлов в заданный файл.
     * @param fls имена найденных файлов.
     */
    private void writeFile(String fls) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.output))) {
            bw.write(fls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записывает имена найденных файлов в заданный файл.
     * @param current проверяемый файл.
     * @return true - файл соответствует маске, false - нет.
     */
    private boolean equalsByMask(String current) {
        int indN = current.lastIndexOf(".");
        int indA = this.name.lastIndexOf(".");
        String[] nameArr = {current.substring(0, indN), current.substring(indN + 1)};
        String[] regArr = Stream.of(this.name.substring(0, indA), this.name.substring(indA + 1))
                .map(n -> n.replaceAll("\\*", ".*")).toArray(String[]::new);
        return IntStream.range(0, nameArr.length).allMatch(i -> nameArr[i].matches(regArr[i]));
    }

    public static void main(String[] args) {
        Arguments arguments = new Arguments(args);
        FileSearch fs = new FileSearch(arguments);
        fs.findFiles();
    }
}