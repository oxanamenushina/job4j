package ru.job4j.io;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Args {

    private String dir;
    private String exc;
    private String outp;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d") && i < args.length - 1) {
                dir = args[i + 1];
            }
            if (args[i].equals("-e") && i < args.length - 1) {
                exc = args[i + 1].substring(2);
            }
            if (args[i].equals("-o") && i < args.length - 1) {
                outp = args[i + 1];
            }
        }
    }

    /**
     * Метод возвращает директорию проекта.
     * @return директория проекта.
     */
    public String directory() {
        return this.dir;
    }

    /**
     * Метод возвращает расширение файлов,
     * которые не нужно включать в архив.
     * @return расширение файлов.
     */
    public String exclude() {
        return this.exc;
    }

    /**
     * Метод возвращает название архива.
     * @return название архива.
     */
    public String output() {
        return this.outp;
    }
}
