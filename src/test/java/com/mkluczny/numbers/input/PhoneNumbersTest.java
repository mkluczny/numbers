package com.mkluczny.numbers.input;

import org.junit.Test;

import java.io.File;
import java.util.Iterator;

import static org.fest.assertions.Assertions.assertThat;

public class PhoneNumbersTest {

    @Test
    public void shouldReturnPhoneNumberIterator() throws Exception {
        // given
        final File input = new File("src/test/resources/input.txt");
        final PhoneNumbers phoneNumbers = new PhoneNumbers(input);

        // when
        final Iterator<String> iterator = phoneNumbers.iterator();

        // then
        assertThat(iterator).isNotNull();
        assertThat(iterator instanceof PhoneNumberIterator).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenPhoneNumbersFileDoesNotExist() throws Exception {
        // given
        final File input = new File("not/existing/file");
        final PhoneNumbers phoneNumbers = new PhoneNumbers(input);

        // when
        phoneNumbers.iterator();
    }
}
