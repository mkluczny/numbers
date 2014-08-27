package com.mkluczny.input;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Input implements Iterable<String> {

    @Override
    public Iterator<String> iterator() {
        return new PhoneNumberIterator();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Spliterator<String> spliterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
