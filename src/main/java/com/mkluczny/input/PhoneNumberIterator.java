package com.mkluczny.input;

import java.io.*;
import java.util.Iterator;
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
