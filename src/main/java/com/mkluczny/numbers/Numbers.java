package com.mkluczny.numbers;

import com.mkluczny.numbers.dictionary.Dictionary;
import com.mkluczny.numbers.dictionary.DictionaryReader;
import com.mkluczny.numbers.input.PhoneNumbers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Numbers {

    /*
     *  Private Fields
     */

    private static final Logger LOG = LogManager.getLogger(Numbers.class);

    /*
     *  Main
     */

    public static void main(final String[] args) throws FileNotFoundException {

        if (args.length != 2 || args[0] == null || args[1] == null) {
            logError("Usage: ./numbers.sh <dictionary-file> <input-file>");
            return;
        }

        final File dictionaryFile = new File(args[0]);
        final File inputFile      = new File(args[1]);

        if (!dictionaryFile.exists()) {
            logError("Dictionary file not found");
            return;
        }

        if (!inputFile.exists()) {
            logError("Input file not found");
            return;
        }

        try {
            final Dictionary dictionary         = new DictionaryReader().load(dictionaryFile);
            final PhoneNumberEncoder encoder    = new PhoneNumberEncoder(dictionary);
            final Iterator<String> iterator     = new PhoneNumbers(inputFile).iterator();

            while (iterator.hasNext()) {
                encoder.encode(iterator.next());
            }
        } catch (Exception e) {
            LOG.fatal(e);
            logError("Fatal error occurred");
            return;
        }

    }

    /*
     *  Private
     */

    private static void logError(final String error) {
        LOG.error(error);
        System.out.println(error);
    }
}
