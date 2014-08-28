package com.mkluczny.numbers;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.mkluczny.numbers.Numbers.main;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.fest.assertions.Assertions.assertThat;

public class NumbersTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void shouldPrintEncodings() throws Exception {
        // given
        final String dictionary = "src/test/resources/dictionary.txt";
        final String input      = "src/test/resources/input.txt";
        final String results    = "src/test/resources/results.txt";

        // when
        main(new String[]{dictionary, input});

        // then
        assertThat(outContent.toString()).isEqualTo(new String(readAllBytes(get(results))));
    }

    @Test
    public void shouldPrintEncodings2() throws Exception {
        // given
        final String dictionary = "src/test/resources/dictionary2.txt";
        final String input      = "src/test/resources/input2.txt";
        final String results    = "src/test/resources/results2.txt";

        // when
        main(new String[]{dictionary, input});

        // then
        assertThat(outContent.toString()).isEqualTo(new String(readAllBytes(get(results))));
    }
}
