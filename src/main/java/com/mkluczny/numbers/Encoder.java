package com.mkluczny.numbers;

import com.mkluczny.numbers.dictionary.Dictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.copyOfRange;

public class Encoder {

    /*
     *  Private Fields
     */

    private final Dictionary dictionary;

    /*
     *  Constructors
     */

    public Encoder(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /*
     *  Public
     */

    public final void encode(final LinkedList<String> words, final char[] digits, final String originalNumber) {

        boolean canAddNumber = true;

        for (int i = 1; i <= digits.length; i++) {

            final String code = new String(copyOfRange(digits, 0, i));

            if (dictionary.containsKey(code)) {

                canAddNumber = false;

                for (String word : dictionary.get(code)) {
                    if (code.length() == digits.length) {
                        print(union(words, word), originalNumber);
                    } else {
                        encode(union(words, word), copyOfRange(digits, i, digits.length), originalNumber);
                    }
                }
            }
        }

        if (canAddNumber && !isLastElementNumeric(words)) {

            final String word = new String(copyOfRange(digits, 0, 1));

            if (digits.length > 1) {
                encode(union(words, word), copyOfRange(digits, 1, digits.length), originalNumber);
            } else {
                print(union(words, word), originalNumber);
            }
        }
    }

    /*
     *  Private
     */

    private void print(final List<String> words, final String originalNumber) {
        final StringBuilder builder     = new StringBuilder();
        final Iterator<String> iterator = words.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append(' ');
            }
        }

        System.out.println(format("%s: %s", originalNumber, builder.toString()));
    }

    private boolean isLastElementNumeric(final LinkedList<String> words) {

        if (words.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(words.getLast());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private LinkedList<String> union(final List<String> words, final String element) {
        final LinkedList<String> list = new LinkedList<String>(words);
        list.add(element);
        return list;
    }
}
