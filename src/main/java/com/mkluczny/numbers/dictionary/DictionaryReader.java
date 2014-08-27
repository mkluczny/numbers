package com.mkluczny.numbers.dictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

import static java.lang.String.format;

public class DictionaryReader {

    /*
     *  Private Fields
     */

    private static final Logger LOG = LogManager.getLogger(DictionaryReader.class);

    /*
     *  Public
     */

    public Dictionary load(final String filename) {

        LOG.info(format("Loading dictionary from file %s", filename));

        try {
            final File file = new File(filename);

            if (!file.exists()) {
                LOG.error(format("File %s does not exist", filename));
                return null;
            }

            return new Dictionary().build(readWords(file));
        } catch (Exception e) {
            LOG.error(format("There was a problem loading dictionary from file %s", filename), e);
            return null;
        }
    }

    /*
     *  Private
     */

    private LinkedList<String> readWords(final File file) throws Exception {

        final LinkedList<String> words      = new LinkedList<String>();
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line, word;

        while ((line = bufferedReader.readLine()) != null) {

            word = line.trim();

            if (isBlank(word)) {
                continue;
            }

            words.add(word);
        }
        bufferedReader.close();

        return words;
    }

    private boolean isBlank(final String word) {
        return (word == null || word.equals(""));
    }
}
