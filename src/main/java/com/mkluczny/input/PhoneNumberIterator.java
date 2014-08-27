package com.mkluczny.input;

import java.io.*;
import java.util.Iterator;
import java.util.function.Consumer;

public class PhoneNumberIterator implements Iterator<String> {

    private final BufferedReader bufferedReader;
    private String currentLine;
    private boolean finished;

    public PhoneNumberIterator(final String filename) throws FileNotFoundException {
        bufferedReader  = new BufferedReader(new FileReader(new File(filename)));
        finished        = false;
    }

    @Override
    public boolean hasNext() {
        try {
            if (currentLine != null) {
                return true;
            }

            if (finished) {
                return false;
            }

            currentLine = bufferedReader.readLine();

            if (currentLine != null) {
                return true;
            } else {
                finished = true;
                return false;
            }
        } catch (IOException ioe) {
            closeQuietly();
            throw new IllegalStateException(ioe.toString());
        }
    }

    @Override
    public String next() {
        final String line = currentLine;
        currentLine = null;
        return line;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on PhoneNumberIterator");
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
        throw new UnsupportedOperationException("Remove unsupported on PhoneNumberIterator");
    }

    private void closeQuietly() {
        currentLine = null;
        finished = true;

        try {
            bufferedReader.close();

        } catch (Exception e) {

        }
    }
}
