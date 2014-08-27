package com.mkluczny.numbers.dictionary;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class DictionaryReaderTest {

    private DictionaryReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new DictionaryReader();
    }

    @Test
    public void shouldLoadDictionary() throws Exception {
        // when
        final Dictionary dictionary = reader.load("src/test/resources/dictionary.txt");

        // then
        assertThat(dictionary).isNotNull();
        assertThat(dictionary.size()).isEqualTo(73113);
    }
}
