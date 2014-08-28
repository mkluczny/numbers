package com.mkluczny.numbers;

import com.mkluczny.numbers.input.PhoneNumbers;
import com.mkluczny.numbers.dictionary.Dictionary;
import com.mkluczny.numbers.dictionary.DictionaryReader;
import com.mkluczny.numbers.dictionary.WordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.String.format;

public class Numbers {

    private static final Logger LOG = LogManager.getLogger(Numbers.class);

    public static void main(final String[] args) throws FileNotFoundException {

        if (args.length != 2) {
            System.out.println("Usage: ./numbers.sh <dictionary-file> <input-file>");
            System.exit(0);
        }

        final File dictionaryFile = new File(args[0]);
        final File inputFile      = new File(args[1]);

        if (!dictionaryFile.exists()) {
            LOG.error("Dictionary file does not exist");
            System.out.println("Dictionary file does not exist");
            System.exit(0);
        }

        if (!inputFile.exists()) {
            LOG.error("Input file does not exist");
            System.out.println("Input file does not exist");
            System.exit(0);
        }

        try {
            final Dictionary dictionary         = new DictionaryReader().load(dictionaryFile);
            final Encoder encoder               = new Encoder(dictionary);
            final Iterator<String> iterator     = new PhoneNumbers(inputFile).iterator();
            final WordEncoder wordEncoder       = new WordEncoder();

            String number;

            while (iterator.hasNext()) {
                number = iterator.next();
                LOG.debug(format("Encoding number: [%s]", number));

                encoder.encode(new LinkedList<String>(), wordEncoder.normalize(number).toCharArray(), number);
            }
        } catch (Exception e) {
            LOG.fatal("Fatal error occurred", e);
            System.out.println("Fatal error occurred");
            System.exit(1);
        }

    }
}
