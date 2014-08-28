package com.mkluczny.numbers.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class PhoneNumbers implements Iterable<String> {

    private final File input;

    public PhoneNumbers(final File input) {
        this.input = input;
    }

    @Override
    public Iterator<String> iterator() {
        try {
            return new PhoneNumberIterator(new BufferedReader(new FileReader(input)));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Cannot create PhoneNumberIterator", e);
        }
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        throw new UnsupportedOperationException("forEach unsupported on Input");
    }

    @Override
    public Spliterator<String> spliterator() {
        throw new UnsupportedOperationException("spliterator unsupported on Input");
    }
}
