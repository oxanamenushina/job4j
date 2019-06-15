package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Zip {

    /**
     * Метод возвращает список всех файлов,
     * содержащихся в папке root и ее всех внутренних папках,
     * за исключением файлов с заданным расширением.
     * @param root путь до каталога, с которого осуществляется поиск.
     * @param exc расширение файлов, которые не нужно включать в список.
     * @return лист всех файлов, содержащихся в папке root
     * и всех ее внутренних папках, за исключением файлов с заданным расширением.
     */
    public List<File> seekBy(String root, String exc) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>(Arrays.asList(new File(root).listFiles()));
        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] files = current.listFiles();
            if (current.isDirectory() && files != null) {
                for (File file : files) {
                    queue.offer(file);
                }
            }
            if (current.isFile() && !current.getName().substring(current.getName().lastIndexOf(".") + 1).equals(exc)) {
                result.add(current);
            }
        }
        return result;
    }

    /**
     * Метод архивирует указанную директорию в zip архив,
     * исключая файлы с заданным расширением и сохраняя структуру проекта.
     * @param source директория проекта.
     * @param target архив.
     * @param exc расширение файлов, которые не нужно включать в архив.
     */
    public void pack(File source, File target, String exc) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : this.seekBy(source.getParent(), exc)) {
                if (!file.getName().equals(target.getName())) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args arg = new Args(args);
        Zip zip = new Zip();
        zip.pack(new File(arg.directory()), new File(arg.output()), arg.exclude());
    }
}
