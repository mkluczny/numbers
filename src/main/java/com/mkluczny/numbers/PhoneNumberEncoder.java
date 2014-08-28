package com.mkluczny.numbers;

import com.mkluczny.numbers.dictionary.Dictionary;
import com.mkluczny.numbers.dictionary.WordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.copyOfRange;

public class PhoneNumberEncoder {

    /*
     *  Private Fields
     */

    private static final Logger LOG = LogManager.getLogger(PhoneNumberEncoder.class);

    private final Dictionary dictionary;
    private final WordEncoder wordEncoder;

    /*
     *  Constructors
     */

    public PhoneNumberEncoder(final Dictionary dictionary) {
        this.dictionary     = dictionary;
        this.wordEncoder    = new WordEncoder();
    }

    /*
     *  Public
     */

    public final void encode(final String number) {

        if (LOG.isDebugEnabled()) {
            LOG.debug(format("Encoding number: [%s]", number));
        }

        encode(new LinkedList<String>(), wordEncoder.normalize(number).toCharArray(), number);
    }

    /*
     *  Private
     */

    public final void encode(final LinkedList<String> words, final char[] digits, final String number) {

        boolean canAddNumber = true;

        for (int i = 1; i <= digits.length; i++) {

            final String code = new String(copyOfRange(digits, 0, i));

            if (dictionary.containsEncoding(code)) {

                canAddNumber = false;

                for (String word : dictionary.get(code)) {
                    if (code.length() == digits.length) {
                        print(union(words, word), number);
                    } else {
                        encode(union(words, word), copyOfRange(digits, i, digits.length), number);
                    }
                }
            }
        }

        if (canAddNumber && !isLastElementNumeric(words)) {

            final String word = new String(copyOfRange(digits, 0, 1));

            if (digits.length > 1) {
                encode(union(words, word), copyOfRange(digits, 1, digits.length), number);
            } else {
                print(union(words, word), number);
            }
        }
    }

    private void print(final List<String> words, final String number) {
        final StringBuilder builder     = new StringBuilder();
        final Iterator<String> iterator = words.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append(' ');
            }
        }

        System.out.println(format("%s: %s", number, builder.toString()));
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
