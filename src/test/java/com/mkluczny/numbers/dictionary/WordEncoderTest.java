package com.mkluczny.numbers.dictionary;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class WordEncoderTest {

    private WordEncoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new WordEncoder();
    }

    @Test
    public void shouldEncodeWord() throws Exception {
        // given
        final String word = "eEJNQjnqRWXrwxDSYdsyFTftAMamCIVcivBKUbkuLOPlopGHZghz";

        // when
        final String encodedWord = encoder.encode(word);

        // then
        assertThat(encodedWord).isEqualTo("0011111122222233333344445555666666777777888888999999");
    }

    @Test
    public void shouldEncodeWordContainingIgnoredCharacters() throws Exception {
        // given
        final String word = "e/e\"e-e";

        // when
        final String encodedWord = encoder.encode(word);

        // then
        assertThat(encodedWord).isEqualTo("0000");
    }
}
