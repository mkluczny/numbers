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

        if (args.length != 2) {
            logErrorAndExit("Usage: ./numbers.sh <dictionary-file> <input-file>", 0);
        }

        final File dictionaryFile = new File(args[0]);
        final File inputFile      = new File(args[1]);

        if (!dictionaryFile.exists()) {
            logErrorAndExit("Directory file not found", 0);
        }

        if (!inputFile.exists()) {
            logErrorAndExit("Input file not found", 0);
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
            logErrorAndExit("Fatal error occurred", 1);
        }

    }

    /*
     *  Private
     */

    private static void logErrorAndExit(final String error, final int statusCode) {
        LOG.error(error);
        System.out.println(error);
        System.exit(statusCode);
    }
}
