package utilities;

public final class SortingUtils {

    private SortingUtils() {
        throw new UnsupportedOperationException("Suppress default constructor for non-instantiability");
    }

    public static int[] sortUsingMergeSort(int[] originalArray) {
        int sizeOfArray = originalArray.length;
        sort(originalArray, 0, sizeOfArray - 1);
        return originalArray;
    }

    private static void merge(int[] array, int leftPoint, int middlePoint, int rightPoint) {
        int sizeOfFirstSubArray = middlePoint - leftPoint + 1;
        int sizeOfSecondSubArray = rightPoint - middlePoint;

        int[] temporaryLeftArray = new int[sizeOfFirstSubArray];
        int[] temporaryRightArray = new int[sizeOfSecondSubArray];

        for (int i = 0; i < sizeOfFirstSubArray; i++) {
            temporaryLeftArray[i] = array[leftPoint + i];
        }
        for (int i = 0; i < sizeOfSecondSubArray; i++) {
            temporaryRightArray[i] = array[middlePoint + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = leftPoint;

        while (i < sizeOfFirstSubArray
                && j < sizeOfSecondSubArray) {
            if (temporaryLeftArray[i] <= temporaryRightArray[j]) {
                array[k] = temporaryLeftArray[i];
                i++;
            } else {
                array[k] = temporaryRightArray[j];
                j++;
            }
            k++;
        }

        while (i < sizeOfFirstSubArray) {
            array[k] = temporaryLeftArray[i];
            i++;
            k++;
        }

        while (j < sizeOfSecondSubArray) {
            array[k] = temporaryRightArray[j];
            j++;
            k++;
        }
    }

    private static void sort(int[] array, int leftPoint, int rightPoint) {
        if (leftPoint < rightPoint) {
            int middlePoint = leftPoint + (rightPoint - leftPoint) / 2;

            sort(array, leftPoint, middlePoint);
            sort(array, middlePoint + 1, rightPoint);

            merge(array, leftPoint, middlePoint, rightPoint);
        }
    }
}
