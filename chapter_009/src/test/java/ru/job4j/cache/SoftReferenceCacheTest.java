package ru.job4j.cache;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SoftReferenceCacheTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SoftReferenceCacheTest {

    private String dir = System.getProperty("java.io.tmpdir");

    private void createFile(String name, String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.dir, name)))) {
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFileAddedThenReturnTextFromThisFile() {
        this.createFile("file1.txt", "Text1");
        Cache<String, String, Function<String, String>> cache = new SoftReferenceCache(this.dir);
        assertThat(cache.get("file1.txt"), is("Text1" + System.lineSeparator()));
    }

    @Test
    public void whenFileDidNotAddThenReturnNull() {
        this.createFile("file2.txt", "Text2");
        this.createFile("file1.txt", "Text1");
        Cache<String, String, Function<String, String>> cache = new SoftReferenceCache(this.dir);
        assertNull(cache.get("file3.txt"));
    }
}