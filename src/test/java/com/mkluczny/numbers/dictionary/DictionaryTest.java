package com.mkluczny.numbers.dictionary;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

public class DictionaryTest {

    private Dictionary dictionary;

    @Before
    public void setUp() throws Exception {
        dictionary = new Dictionary();
    }

    @Test
    public void shouldBuildDictionary() throws Exception {
        // given
        final List<String> wordsEncodedToZeroZero       = asList("eE", "ee", "EE");
        final List<String> wordsEncodedToTwoThreeFour   = asList("RST", "RsT", "Rs\"T");

        final List<String> words = new LinkedList<String>();

        words.addAll(wordsEncodedToZeroZero);
        words.addAll(wordsEncodedToTwoThreeFour);

        // when
        dictionary.build(words);

        // then
        assertThat(dictionary.keySet().size()).isEqualTo(2);
        assertThat((dictionary.get("00"))).isEqualTo(wordsEncodedToZeroZero);
        assertThat((dictionary.get("234"))).isEqualTo(wordsEncodedToTwoThreeFour);
    }
}
