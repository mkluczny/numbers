package com.mkluczny.numbers;

import com.mkluczny.numbers.dictionary.DictionaryReader;

public class Numbers {

    public static void main(final String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: ./numbers.sh <dictionary> <input>");
            System.exit(0);
        }

        DictionaryReader dictionaryReader = new DictionaryReader();

        dictionaryReader.load(args[0]);


    }
}
