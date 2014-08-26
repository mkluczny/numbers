package com.mkluczny.numbers.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Collections.unmodifiableMap;

public class WordEncoder {

    /*
     *  Static Fields
     */

    private static final Map<Character, Character> codes;

    static {
        Map<Character, Character> map = new HashMap<Character, Character>();

        map.put('e', '0');
        map.put('E', '0');

        map.put('J', '1'); map.put('j', '1');
        map.put('N', '1'); map.put('n', '1');
        map.put('Q', '1'); map.put('q', '1');

        map.put('R', '2'); map.put('r', '2');
        map.put('W', '2'); map.put('w', '2');
        map.put('X', '2'); map.put('x', '2');

        map.put('D', '3'); map.put('d', '3');
        map.put('S', '3'); map.put('s', '3');
        map.put('Y', '3'); map.put('y', '3');

        map.put('F', '4'); map.put('f', '4');
        map.put('T', '4'); map.put('t', '4');

        map.put('A', '5'); map.put('a', '5');
        map.put('M', '5'); map.put('m', '5');

        map.put('C', '6'); map.put('c', '6');
        map.put('I', '6'); map.put('i', '6');
        map.put('V', '6'); map.put('v', '6');

        map.put('B', '7'); map.put('b', '7');
        map.put('K', '7'); map.put('k', '7');
        map.put('U', '7'); map.put('u', '7');

        map.put('L', '8'); map.put('l', '8');
        map.put('O', '8'); map.put('o', '8');
        map.put('P', '8'); map.put('p', '8');

        map.put('G', '9'); map.put('g', '9');
        map.put('H', '9'); map.put('h', '9');
        map.put('Z', '9'); map.put('z', '9');

        codes = unmodifiableMap(map);
    }

    /*
     *  Private Fields
     */

    private final Pattern charactersToBeRemoved = Pattern.compile("[\\-\\/\"]");

    /*
     *  Public
     */

    public final String encode(final String word) {
        char[] result = normalize(word).toCharArray();

        for (int i = 0; i < result.length; i ++) {
            result[i] = codes.get(result[i]);
        }

        return new String(result);
    }

    public final String normalize(String word) {
        word = charactersToBeRemoved.matcher(word).replaceAll("");
        return word;
    }
}
