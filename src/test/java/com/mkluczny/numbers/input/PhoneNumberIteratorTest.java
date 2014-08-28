package com.mkluczny.numbers.input;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class PhoneNumberIteratorTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldSkipToLongNumbers() throws Exception {
        // given
        final String numbers =  "111111111111111111111111111111111111111111111111111\n" +   //too long
                                "33333333333333333333333333333333333333333333333333\n" +    // exactly 50 characters
                                "222222222222222222222222222222222222222222222222222";      // too long

        final BufferedReader reader       = new BufferedReader(new StringReader(numbers));
        final Iterator<String> iterator   = new PhoneNumberIterator(reader);
        final List<String> results        = new LinkedList<String>();

        // when
        while(iterator.hasNext()) {
            results.add(iterator.next());
        }

        // then
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    public void shouldSkipNumbersNotMatchingPhoneNumberPattern() throws Exception {
        // given
        final String numbers =  "\n" +          // empty line
                                "asd\n" +       // invalid format
                                "123-123/12";   // OK

        final BufferedReader reader       = new BufferedReader(new StringReader(numbers));
        final Iterator<String> iterator   = new PhoneNumberIterator(reader);
        final List<String> results        = new LinkedList<String>();

        // when
        while(iterator.hasNext()) {
            results.add(iterator.next());
        }

        // then
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    public void shouldIterateOverAllPhoneNumbers() throws Exception {
        // given
        final String numbers =  "123\n" +
                                "123---1\n" +
                                "123-123/12";

        final BufferedReader reader       = new BufferedReader(new StringReader(numbers));
        final Iterator<String> iterator   = new PhoneNumberIterator(reader);
        final List<String> results        = new LinkedList<String>();

        // when
        while(iterator.hasNext()) {
            results.add(iterator.next());
        }

        // then
        assertThat(results.size()).isEqualTo(3);
        assertThat(results).containsOnly("123", "123---1", "123-123/12");
    }
}
