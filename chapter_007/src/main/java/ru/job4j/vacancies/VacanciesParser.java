package ru.job4j.vacancies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class VacanciesParser.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class VacanciesParser {

    private static final Logger LOG = LogManager.getLogger(VacanciesParser.class.getName());

    private boolean limit = true;
    private LinkedList<Vacancy> vacancies = new LinkedList<>();
    private long initTime;

    public VacanciesParser(long initTime) {
        this.initTime = initTime;
    }

    /**
     * Метод парсит вакансии из раздела работа сайта sql.ru.
     * @return список вакансий со словом Java в названии.
     */
    public List<Vacancy> parse() {
        int page = 1;
        LOG.info("Parsing of the work section on sql.ru.");
        try {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/").get();
            this.parsePage(doc);
            while (this.limit) {
                this.parsePage(Jsoup.connect("https://www.sql.ru/forum/job-offers/" + ++page).get());
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return this.vacancies;
    }

    /**
     * Метод парсит отдельные страницы с вакансиями
     * из раздела работа сайта sql.ru.
     * @param doc документ.
     */
    private void parsePage(Document doc) {
        LOG.info("Parsing of one page of the work section on sql.ru.");
        Elements elems = doc.getElementsByClass("forumTable").select("tr").next();
        for (Element e1 : elems) {
            long vacTime = this.convertTime(e1.select("td").last().text());
            this.limit = e1.getElementsByClass("postslisttopic").first().text().startsWith("Важно")
                    || vacTime > this.initTime;
            if (!this.limit) {
                break;
            }
            if (this.isJavaVacancy(e1.text())) {
                Element current = e1.getElementsByClass("postslisttopic").select("a").first();
                this.vacancies.addFirst(new Vacancy(
                        current.text(), current.attr("href"), this.getTextVacancy(current.attr("href")), vacTime));
            }
        }
    }

    /**
     * Метод возвращает текс вакансии.
     * @param reference ссылка на текст вакансии.
     * @return текст вакансии.
     */
    private String getTextVacancy(String reference) {
        String text = "";
        try {
            Document doc = Jsoup.connect(reference).get();
            Element element = doc.getElementsByClass("msgTable").first().getElementsByClass("msgBody").last();
            text = element.text();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return text;
    }

    /**
     * Метод проверяет является ли данная вакансия Java-вакансией.
     * @param title название вакансии.
     * @return true - данная вакансия является Java-вакансией, false - нет.
     */
    private boolean isJavaVacancy(String title) {
        Pattern patt = Pattern.compile(".*java(?!script|\\sscript).*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patt.matcher(title);
        return matcher.matches();
    }

    /**
     * Метод возвращает время создания вакансии в виде числа.
     * @param time время создания вакансии.
     * @return время создания вакансии в виде числа.
     */
    private long convertTime(String time) {
        String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String t = time.substring(time.lastIndexOf(" ") + 1).replace(":", "");
        return time.startsWith("сегодня") ? Long.parseLong(today + t)
                : time.startsWith("вчера") ? Long.parseLong(this.getYesterdayDate() + t)
                : this.getDateAndTime(time);
    }

    /**
     * Метод возвращает вчерашнюю дату.
     * @return строка с вчерашней датой.
     */
    private String getYesterdayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    /**
     * Метод преобразует время создания вакансии из текста в число.
     * @param time время создания вакансии.
     * @return время создания вакансии в виде числа.
     */
    private long getDateAndTime(String time) {
        long vacTime = 0;
        try {
            Locale loc = new Locale("ru");
            DateFormatSymbols symbols = DateFormatSymbols.getInstance(loc);
            symbols.setMonths(new String[]
                    {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"});
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, HH:mm", new Locale("ru"));
            format.setDateFormatSymbols(symbols);
            Date date = format.parse(time);
            vacTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(date));
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacTime;
    }
}