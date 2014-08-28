package com.mkluczny.numbers;

import com.mkluczny.numbers.dictionary.Dictionary;
import com.mkluczny.numbers.dictionary.WordEncoder;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

public class PhoneNumberEncoderTest {

    private PhoneNumberEncoder encoder;
    private WordEncoder wordEncoder;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private static final List<String> words = asList(
            "an",
            "blau",
            "Bo\"",
            "Boot",
            "bo\"s",
            "da",
            "Fee",
            "fern",
            "Fest",
            "fort",
            "je",
            "jemand",
            "mir",
            "Mix",
            "Mixer",
            "Name",
            "neu",
            "o\"d",
            "Ort",
            "so",
            "Tor",
            "Torf",
            "Wasser"
    );

    private static Dictionary dictionary = new Dictionary();

    static {
        dictionary.build(words);
    }

    @Before
    public void setUp() throws Exception {

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        encoder     = new PhoneNumberEncoder(dictionary);
        wordEncoder = new WordEncoder();
    }

    @Test
    public void shouldEncodeWord() throws Exception {
        // given
        final List<String> numbers = asList("112", "5624-82", "4824", "0721/608-4067", "10/783--5", "1078-913-5", "381482", "04824");
        final String output = "5624-82: mir Tor\n" +
                              "5624-82: Mix Tor\n" +
                              "4824: Tor 4\n" +
                              "4824: fort\n" +
                              "4824: Torf\n" +
                              "10/783--5: je Bo\" da\n" +
                              "10/783--5: je bo\"s 5\n" +
                              "10/783--5: neu o\"d 5\n" +
                              "381482: so 1 Tor\n" +
                              "04824: 0 Tor 4\n" +
                              "04824: 0 fort\n" +
                              "04824: 0 Torf\n";

        // when
        for (String number : numbers) {
            encoder.encode(new LinkedList<String>(), wordEncoder.normalize(number).toCharArray(), number);
        }

        // then
        assertThat(outContent.toString()).isEqualTo(output);
    }
}
