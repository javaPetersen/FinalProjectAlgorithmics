package utilities;

import enums.Algorithm;

import java.util.ArrayList;
import java.util.List;

import static enums.Algorithm.*;

public final class SearchingPatternsUtils {

    private SearchingPatternsUtils() {
        throw new UnsupportedOperationException("Suppress default constructor for non-instantiability");
    }

    public static void searchPatternByKMPAlgorithm(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        List<Integer> indexes = new ArrayList<>();

        int[] longestPrefixSuffixValues = new int[patternLength];
        int patternIndex = 0;

        computeLongestPrefixSuffixValues(pattern, longestPrefixSuffixValues);

        int textIndex = 0;
        while ((textLength - textIndex) >= (patternLength - patternIndex)) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }
            if (patternIndex == patternLength) {
                indexes.add(textIndex - patternIndex);
                patternIndex = longestPrefixSuffixValues[patternLength - 1];
            } else if (textIndex < textLength
                    && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                if (patternIndex != 0) {
                    patternIndex = longestPrefixSuffixValues[patternIndex - 1];
                } else {
                    textIndex++;
                }
            }
        }
        showIndexes(indexes, KMP_ALGORITHM);
    }

    private static void computeLongestPrefixSuffixValues(String pattern, int[] longestPrefixSuffixValues) {
        int previousLongestPrefixSuffixIndex = 0;
        int index = 1;
        longestPrefixSuffixValues[0] = 0;

        while (index < pattern.length()) {
            if (pattern.charAt(index) == pattern.charAt(previousLongestPrefixSuffixIndex)) {
                previousLongestPrefixSuffixIndex++;
                longestPrefixSuffixValues[index] = previousLongestPrefixSuffixIndex;
                index++;
            } else {
                if (previousLongestPrefixSuffixIndex != 0) {
                    previousLongestPrefixSuffixIndex = longestPrefixSuffixValues[previousLongestPrefixSuffixIndex - 1];
                } else {
                    longestPrefixSuffixValues[index] = previousLongestPrefixSuffixIndex;
                    index++;
                }
            }
        }
    }

    private static final int NUMBER_OF_CHARACTERS = 91;
    private static int getMax(int a, int b) {
        return Math.max(a, b);
    }
    private static void badCharHeuristic(String pattern, int size, int[] badChars) {
        for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
            badChars[i] = -1;
        }
        for (int i = 0; i < size; i++) {
            badChars[pattern.charAt(i)] = i;
        }
    }

    public static void searchPatternByBoyerMooreAlgorithm(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        List<Integer> indexes = new ArrayList<>();

        int[] badChars = new int[NUMBER_OF_CHARACTERS];

        badCharHeuristic(pattern, patternLength, badChars);

        int shiftOfPattern = 0;

        while (shiftOfPattern <= textLength - patternLength) {
            int patternLegit = patternLength - 1;

            while (patternLegit >= 0 &&
            pattern.charAt(patternLegit) == text.charAt(shiftOfPattern + patternLegit)) {
                patternLegit--;
            }
            if (patternLegit < 0) {
                indexes.add(shiftOfPattern);
                int shiftAndPatternLength = shiftOfPattern + patternLength;
                shiftOfPattern += (shiftAndPatternLength < textLength) ?
                        patternLength - badChars[text.charAt(shiftAndPatternLength)] : 1;
            } else {
                shiftOfPattern += getMax(1, patternLegit - badChars[text.charAt(shiftOfPattern + patternLegit)]);
            }
        }
        showIndexes(indexes, BOYER_MOORE_ALGORITHM);
    }

    private static void showIndexes(List<Integer> indexes, Algorithm algorithm) {
        if (indexes.isEmpty()) {
            System.out.printf("\t%s does not found any matches%n", algorithm.getAlgorithmName());
            return;
        }
        System.out.printf("\t%s found matching patterns at positions %s%n", algorithm.getAlgorithmName(), indexes);
    }
}
