import enums.Algorithm;

import java.util.function.Supplier;

import static enums.Algorithm.*;
import static utilities.CleaningUtils.*;
import static utilities.RandomUtils.*;
import static utilities.SearchingUtils.*;
import static utilities.SortingUtils.*;
import static utilities.SearchingPatternsUtils.*;

public class Main {
    private static final int MIN_RANGE_VALUE = 65;
    private static final int MAX_RANGE_VALUE = 91;

    /** Sugeruje zwiększyć text_array_length lub zmniejszyć pattern_array_length,
    ponieważ podczas testów okazało się, że przy tych wartościach bardzo rzadko przytrafia się match */
    private static final int TEXT_ARRAY_LENGTH = 10000;
    private static final int PATTERN_ARRAY_LENGTH = 5;

    public static void main(String[] args) {

        int[] textAsIntegers = generateRandomArray(TEXT_ARRAY_LENGTH, MIN_RANGE_VALUE, MAX_RANGE_VALUE);
        int[] patternAsIntegers = generateRandomArray(PATTERN_ARRAY_LENGTH, MIN_RANGE_VALUE, MAX_RANGE_VALUE - 1); //bez garbage value

        int[] copyOverCleanedArray = runAlgorithm(COPY_OVER,
                () -> copyOverCleanUp(textAsIntegers.clone(), MAX_RANGE_VALUE));

        int[] convergingPointersCleanUp = runAlgorithm(CONVERGING_POINTERS,
                () -> convergingPointersCleanUp(textAsIntegers.clone(), MAX_RANGE_VALUE));

        int number = generateRandomNumber(MIN_RANGE_VALUE, MAX_RANGE_VALUE - 1);

        runAlgorithm(LINEAR_SEARCH,
                () -> searchByUsingLinearSearch(copyOverCleanedArray.clone(), number));

        runAlgorithm(BINARY_SEARCH,
                () -> searchByUsingBinarySearch(sortUsingMergeSort(convergingPointersCleanUp.clone()), number));

        String textAsString = convertNumbersToText(copyOverCleanedArray.clone());
        String patternAsString = convertNumbersToText(patternAsIntegers);

        runAlgorithm(KMP_ALGORITHM,
                () -> searchPatternByKMPAlgorithm(patternAsString, textAsString));

        runAlgorithm(BOYER_MOORE_ALGORITHM,
                () -> searchPatternByBoyerMooreAlgorithm(patternAsString, textAsString));
    }

    private static int[] runAlgorithm(Algorithm algorithm, Supplier<int[]> method) {
        long startTime = System.nanoTime();
        int[] arr = method.get();
        System.out.printf("%50s took: %10dns%n", algorithm.getAlgorithmName(), System.nanoTime() - startTime);
        return arr;
    }

    private static void runAlgorithm(Algorithm algorithm, Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        System.out.printf("%50s took: %10dns%n", algorithm.getAlgorithmName(), System.nanoTime() - startTime);
    }

    private static String convertNumbersToText(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : array) {
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }
}