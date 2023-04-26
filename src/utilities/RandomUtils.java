package utilities;

import java.util.Random;

public final class RandomUtils {
    private static final Random RANDOM = new Random();

    private RandomUtils() {
        throw new UnsupportedOperationException("Suppress default constructor for non-instantiability");
    }

    public static int[] generateRandomArray(int arrayLength, int startRange, int bound) {
        int[] emptyArray = new int[arrayLength];
        for (int i = 0; i < emptyArray.length; i++) {
            emptyArray[i] = RANDOM.nextInt(bound - startRange + 1) + startRange;
        }
        return emptyArray;
    }

    public static int generateRandomNumber(int startRange, int bound) {
        return RANDOM.nextInt(bound - startRange + 1) + startRange;
    }
}
