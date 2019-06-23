package ru.job4j.search;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Arguments {

    private Map<String, String> args;

    /**
     * Конструктор.
     * Инициализирует ассоциативный массив args,
     * в котором ключи - ключи из массива входных параметров,
     * значения - значения ключей из массива входных параметров,
     * @param input массив входных параметров.
     */
    public Arguments(String[] input) {
        this.args = new HashMap<>();
        this.args.put("-d", null);
        this.args.put("-n", null);
        this.args.put("-m", null);
        this.args.put("-f", null);
        this.args.put("-o", null);
        IntStream.range(0, input.length).forEach(i -> args.replace(input[i], null, (i < input.length - 1)
                && !"-m".equals(input[i]) && !"-f".equals(input[i]) ? input[i + 1] : input[i]));
    }

    /**
     * Метод возвращает директорию,
     * в котораой начинать поис заданных файлов.
     * @return директория,
     * в котораой начинать поис заданных файлов.
     */
    public String directory() {
        return this.args.get("-d");
    }

    /**
     * Метод возвращает имя файла, маску,
     * либо регулярное выражение для поиска файлов.
     * @return имя файла, маску,
     * либо регулярное выражение для поиска файлов.
     */
    public String fileName() {
        return this.args.get("-n");
    }

    /**
     * Метод возвращает критерий поиска файлов:
     * -m - искать по маске,
     * -f - полное совпадение имени,
     * -r - регулярное выражение.
     * @return критерий поиска файлов.
     */
    public String criterion() {
        return this.args.get("-m") != null ? "-m" : "-f";
    }

    /**
     * Метод возвращает название файла,
     * в который нужно записать результаты поиска.
     * @return название файла,
     * для записи результатов поиска.
     */
    public String output() {
        return this.args.get("-o");
    }

    /**
     * Валидация ключей.
     */
    public void validate() {
        if (this.args.get("-d") == null || this.args.get("-n") == null || this.args.get("-o") == null
                || (this.args.get("-m") == null ^ this.args.get("-f") == null)) {
            throw new IncorrectDataException("Введены не все данные.");
        }
        Path path = Paths.get(this.args.get("-d"));
        if (!Files.exists(path)) {
            throw new IncorrectDataException("Данная директория не существует.");
        }
    }
}