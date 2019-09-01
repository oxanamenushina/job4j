package ru.job4j.vacancies;

/**
 * Class Vacancy.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Vacancy {

    private String name;
    private String link;
    private String text;
    private long time;

    public Vacancy(String name, String link, String text, long time) {
        this.name = name;
        this.link = link;
        this.text = text;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getText() {
        return text;
    }

    public long getTime() {
        return time;
    }
}