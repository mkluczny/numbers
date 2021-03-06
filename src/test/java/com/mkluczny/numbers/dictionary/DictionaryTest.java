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
        assertThat(dictionary.encodingSet().size()).isEqualTo(2);
        assertThat((dictionary.get("00"))).isEqualTo(wordsEncodedToZeroZero);
        assertThat((dictionary.get("234"))).isEqualTo(wordsEncodedToTwoThreeFour);
    }

    @Test
    public void shouldSkipToLongWords() throws Exception {
        // given
        final List<String> words = asList(wordOfLength(50, 'a'), wordOfLength(51, 'e'));

        // when
        dictionary.build(words);

        // then
        assertThat(dictionary.size()).isEqualTo(1);
    }

    @Test
    public void shouldSkipWordsAfterFillingUpTheDictionary() throws Exception {
        // given
        final List<String> words = new LinkedList<String>();

        for(int i = 0; i < Dictionary.MAX_DICTIONARY_SIZE + 10; i++) {
            words.add(wordOfLength(1, 'a'));
        }

        // when
        dictionary.build(words);

        // then
        assertThat(dictionary.size()).isEqualTo(Dictionary.MAX_DICTIONARY_SIZE);
    }

    /*
     *  Private
     */

    private String wordOfLength(final int length, final char character) {
        char[] word  = new char[length];

        for (int i = 0; i < length; i++) {
            word[i] = character;
        }

        return new String(word);
    }
}
