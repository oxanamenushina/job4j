package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SearchTest {

    @Test
    public void whenRequestedSprThenReturnEmptyList() throws IOException {
        File root = new File(System.getProperty("java.io.tmpdir"));
        root.mkdirs();
        File subDir1 = new File(root, "subDir1");
        subDir1.mkdir();
        File subDir3 = new File(subDir1, "subDir3");
        subDir3.mkdir();
        File file1 = new File(root, "first.txt");
        file1.createNewFile();
        File file4 = new File(subDir1, "fourth.dwg");
        file4.createNewFile();
        File file6 = new File(subDir3, "sixth.dwg");
        file6.createNewFile();
        Search search = new Search();
        assertThat(search.files(System.getProperty("java.io.tmpdir"), List.of("spr")),
                is(List.of()));
    }

    @Test
    public void whenRequestedTxtDwgThenReturnAllFilesTxtDwg() throws IOException {
        File root = new File(System.getProperty("java.io.tmpdir"));
        root.mkdirs();
        File subDir1 = new File(root, "subDir1");
        subDir1.mkdir();
        File subDir2 = new File(root, "subDir2");
        subDir2.mkdir();
        File subDir3 = new File(subDir1, "subDir3");
        subDir3.mkdir();
        File file1 = new File(root, "first.txt");
        file1.createNewFile();
        File file2 = new File(root, "second.doc");
        file2.createNewFile();
        File file3 = new File(subDir1, "third.txt");
        file3.createNewFile();
        File file4 = new File(subDir1, "fourth.dwg");
        file4.createNewFile();
        File file5 = new File(subDir2, "fifth.log");
        file5.createNewFile();
        File file6 = new File(subDir3, "sixth.dwg");
        file6.createNewFile();
        Search search = new Search();
        assertThat(search.files(System.getProperty("java.io.tmpdir"), List.of("txt", "dwg")),
                is(List.of(file1, file4, file3, file6)));
    }
}