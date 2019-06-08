package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class AnalysisTest {

    @Test
    public void whenUnavailableThenTargetFileHasRanges() {
        String[] str = {"200 10:56:01", "500 10:57:01", "400 10:58:01", "200 10:59:01", "500 11:01:02", "200 11:02:02"};
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("server.log"))) {
            for (String s : str) {
                bw.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Analysis analysis = new Analysis();
        analysis.unavailable("server.log", "unavailable.csv");
        List<String> ranges = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("unavailable.csv"))) {
            String range;
            while ((range = br.readLine()) != null) {
                ranges.add(range);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(ranges, is(List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;")));
    }
}
