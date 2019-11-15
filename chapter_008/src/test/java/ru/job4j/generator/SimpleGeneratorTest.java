package ru.job4j.generator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleGeneratorTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleGeneratorTest {

    @Test
    public void whenGenerateWithKeyNameThenPhraseIsGeneratedWithTheValueOfThisKey() {
        Generator generator = new SimpleGenerator();
        String str = "Солнечный ${name}.";
        Map<String, String> words = new HashMap<>();
        words.put("name", "день");
        assertThat(generator.generate(str, words), is("Солнечный день."));
    }

    @Test
    public void whenGenerateWithKeySosThenPhraseIsGeneratedWithAaa() {
        Generator generator = new SimpleGenerator();
        String str = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> words = new HashMap<>();
        words.put("sos", "Aaa");
        assertThat(generator.generate(str, words), is("Help, Aaa, Aaa, Aaa"));
    }

    @Test
    public void whenGenerateWithKeyNameAndSubjectThenPhraseIsGeneratedWithTheValuesOfTheseKeys() {
        Generator generator = new SimpleGenerator();
        String str = "Черная ${name} смотрит в ${subject}.";
        Map<String, String> words = new HashMap<>();
        words.put("name", "кошка");
        words.put("subject", "окно");
        assertThat(generator.generate(str, words), is("Черная кошка смотрит в окно."));
    }

    @Test(expected = KeyNotFoundException.class)
    public void whenGenerateWithKeyNameThenThrowNoWayException() {
        Generator generator = new SimpleGenerator();
        String str = "Солнечный ${name}.";
        Map<String, String> words = new HashMap<>();
        words.put("invalid", "день");
        assertThat(generator.generate(str, words), is("Солнечный день."));
    }

    @Test(expected = ExtraKeysException.class)
    public void whenGenerateWithKeyNameThenThrowExtraKeysException() {
        Generator generator = new SimpleGenerator();
        String str = "Солнечный ${name}.";
        Map<String, String> words = new HashMap<>();
        words.put("name", "день");
        words.put("invalid", "луч");
        assertThat(generator.generate(str, words), is("Солнечный день."));
    }
}