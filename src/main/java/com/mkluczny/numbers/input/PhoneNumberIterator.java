package com.mkluczny.numbers.input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class PhoneNumberIterator implements Iterator<String> {

    /*
     *  CONSTANTS
     */

    public static final int     MAX_PHONE_NUMBER_LENGTH = 50;
    public static final Pattern PHONE_NUMBER_FORMAT     = Pattern.compile("^[0-9\\/\\-]+$");

    /*
     *  Private Fields
     */

    private static final Logger LOG = LogManager.getLogger(PhoneNumberIterator.class);

    private final BufferedReader reader;
    private String number;
    private boolean finished;

    /*
     *  Constructor
     */

    public PhoneNumberIterator(final BufferedReader reader) {
        this.reader     = reader;
        this.finished   = false;
    }

    /*
     *  Public
     */

    @Override
    public boolean hasNext() {
        try {
            if (number != null) {
                return true;
            }

            if (finished) {
                return false;
            }

            while(true) {

                number = reader.readLine();

                if (number == null) {
                    closeQuietly();
                    return false;
                }

                if (isValidPhoneNumber(number)) {
                    return true;
                } else {
                    LOG.warn(String.format("Invalid phone number [%s]", number));
                    number = null;
                }
            }

        } catch (IOException ioe) {
            closeQuietly();
            throw new IllegalStateException(ioe.toString());
        }
    }

    @Override
    public String next() {

        if (number == null && !hasNext()) {
            closeQuietly();
            throw new NoSuchElementException("No more phone numbers");
        }

        final String nextNumber = number;
        number = null;
        return nextNumber;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on PhoneNumberIterator");
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
        throw new UnsupportedOperationException("Remove unsupported on PhoneNumberIterator");
    }

    /*
     *  Private
     */

    private void closeQuietly() {
        number      = null;
        finished    = true;

        try {
            reader.close();
        } catch (Exception e) {

        }
    }

    private boolean isValidPhoneNumber(final String number) {
        if (number.length() > MAX_PHONE_NUMBER_LENGTH) {
            return false;
        }

        if (!PHONE_NUMBER_FORMAT.matcher(number).matches()) {
            return false;
        }

        return true;
    }
}
