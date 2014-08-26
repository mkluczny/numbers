package com.mkluczny.numbers.dictionary;

import java.util.*;

public class Dictionary {

    /*
     *  Private Fields
     */

    private final Map<String, List<String>> dictionary;
    private final WordEncoder encoder;

    /*
     *  Constructors
     */

    public Dictionary() {
        dictionary  = new HashMap<String, List<String>>();
        encoder     = new WordEncoder();
    }

    /*
     *  Public
     */

    public void build(final List<String> words) {

        String encoding;

        for (String word : words) {

            encoding = encoder.encode(word);
            dictionary.putIfAbsent(encoding, new LinkedList<String>());
            dictionary.get(encoding).add(word);
        }
    }

    public List<String> get(final String key) {
        return dictionary.get(key);
    }

    public boolean containsKey(final String key) {
        return dictionary.containsKey(key);
    }

    public Set<String> keySet() {
        return dictionary.keySet();
    }
}
