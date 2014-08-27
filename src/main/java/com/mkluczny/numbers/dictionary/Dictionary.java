package com.mkluczny.numbers.dictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static java.lang.String.format;

public class Dictionary {

    /*
     *  Constants
     */

    public static final int MAX_DICTIONARY_WORD_LENGTH  = 50;
    public static final int MAX_DICTIONARY_SIZE         = 75000;

    /*
     *  Private Fields
     */

    private static final Logger LOG = LogManager.getLogger(Dictionary.class);
    private final Map<String, List<String>> dictionary;
    private final WordEncoder encoder;
    private int size;

    /*
     *  Constructors
     */

    public Dictionary() {
        dictionary  = new HashMap<String, List<String>>();
        encoder     = new WordEncoder();
        size        = 0;
    }

    /*
     *  Public
     */

    public void build(final List<String> words) {

        LOG.info("Building dictionary...");

        for (final String word : words) {

            if (isFull()) {
                LOG.warn(format("Dictionary reached it's maximum size of %d - all remaining entries will be skipped", MAX_DICTIONARY_SIZE));
                break;
            }

            if (isTooLong(word)) {
                LOG.warn(format("Given dictionary word [%s] is too long and will be skipped", word));
                continue;
            }

            addToDictionary(word, encoder.encode(word));
        }

        LOG.info("Dictionary is ready to be used.");
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

    public int size() {
        return size;
    }

    /*
     *  Private
     */

    private boolean isTooLong(final String word) {
        return word.length() > MAX_DICTIONARY_WORD_LENGTH;
    }

    private boolean isFull() {
        return (size == MAX_DICTIONARY_SIZE);
    }

    private void addToDictionary(final String word, final String encoding) {
        dictionary.putIfAbsent(encoding, new LinkedList<String>());
        dictionary.get(encoding).add(word);
        size += 1;
    }
}
