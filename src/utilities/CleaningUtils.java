package utilities;

public final class CleaningUtils {

    private CleaningUtils() {
        throw new UnsupportedOperationException("Suppress default constructor for non-instantiability");
    }

    public static int[] copyOverCleanUp(int[] originalArray, int garbageValue) {
        int sizeOfArray = originalArray.length;
        int left = 0;
        int newPosition = 0;
        int legit = sizeOfArray;

        if (sizeOfArray > 0) {
            int[] tempArray = new int[sizeOfArray];

            while (left < sizeOfArray) {
                if (originalArray[left] != garbageValue) {
                    tempArray[newPosition] = originalArray[left];
                    left++;
                    newPosition++;
                } else {
                    left++;
                    legit--;
                }
            }
            return getCleanedUpArray(tempArray, legit);
        }
        return originalArray;
    }

    public static int[] convergingPointersCleanUp(int[] originalArray, int garbageValue) {
        int sizeOfArray = originalArray.length;
        int legit = sizeOfArray;
        int left = 0;
        int right = sizeOfArray - 1;

        while (left < right) {
            if (originalArray[left] != garbageValue) {
                left++;
            } else {
                legit--;
                originalArray[left] = originalArray[right];
                right--;
            }
        }
        if (originalArray[left] == garbageValue) {
            legit--;
        }
        return getCleanedUpArray(originalArray, legit);
    }

    private static int[] getCleanedUpArray(int[] originalArray, int legit) {
        int[] tempArray = new int[legit];
        System.arraycopy(originalArray, 0, tempArray, 0, legit);
        return tempArray;
    }
}
