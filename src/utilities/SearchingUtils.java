package utilities;

import java.util.ArrayList;
import java.util.List;

public final class SearchingUtils {

    private static final String NUMBER_NOT_FOUND = "\tNumber %d not found in Array%n";
    private static final String NUMBER_FOUND_AT = "\tNumber %d found %d times at positions: %s%n";
    private static final String NUMBER_FOUND_RANGE_BETWEEN = "\tNumber %d found %d times at range between: [%s...%s]%n";

    private SearchingUtils() {
        throw new UnsupportedOperationException("Suppress default constructor for non-instantiability");
    }

    public static void searchByUsingLinearSearch(int[] array, int toFind) {
        int sizeOfArray = array.length;
        List<Integer> listOfFoundIndexes = new ArrayList<>();
        if (sizeOfArray != 0) {
            for (int i = 0; i < sizeOfArray; i++) {
                if (array[i] == toFind) {
                    listOfFoundIndexes.add(i);
                }
            }
        }
        showIndexes(listOfFoundIndexes, toFind);
    }

    /**
     Wiedząc, że binary search działa na posortowanej tablicy i wyszukuje zazwyczaj jedną konkretną wartość
     musiałem go zmodyfikować w następujący sposób:
      ~ muszę wywołać go dwukrotnie, raz aby znaleźć leftMostElement, czyli pierwszy indeks pod którym się pojawia,
     a następnie rightMostElement, czyli ostatnie wystąpienie w tablicy.
      ~ następnie wyświetlam zakres pomiędzy pierwszym a ostatnim wystapieniem (wszystkie elementy pomiędzy tymi
     wartościami są równe szukanej wartości).
     */
    public static void searchByUsingBinarySearch(int[] array, int toFind) {
        int leftMostIndex = findBorderElement(array, toFind, true);
        int rightMostIndex = findBorderElement(array, toFind, false);
        showIndexes(toFind, leftMostIndex, rightMostIndex);
    }

    private static int findBorderElement(int[] array, int toFind, boolean isLeftMostElement) {
        int leftPoint = 0;
        int rightPoint = array.length - 1;

        while (rightPoint > leftPoint) {
            int middlePoint = leftPoint + (rightPoint - leftPoint) / 2;
            if (!isLeftMostElement) {
                if (array[middlePoint] > toFind) {
                    rightPoint = middlePoint;
                } else {
                    leftPoint = middlePoint + 1;
                }
            } else {
                if (array[middlePoint] < toFind) {
                    leftPoint = middlePoint + 1;
                } else {
                    rightPoint = middlePoint;
                }
            }
        }
        if (isLeftMostElement) {
            if (array[leftPoint] != toFind) {
                leftPoint = -1;
            }
        } else {
            if (array[rightPoint - 1] != toFind) {
                rightPoint = -1;
            }
        }
        return isLeftMostElement ? leftPoint : rightPoint - 1;
    }

    private static void showIndexes(List<Integer> indexes, int toFind) {
        if (indexes.isEmpty()) {
            System.out.printf(NUMBER_NOT_FOUND, toFind);
            return;
        }
        System.out.printf(NUMBER_FOUND_AT, toFind, indexes.size(), indexes);
    }

    private static void showIndexes(int toFind, int firstIndex, int lastIndex) {
        if (firstIndex == -1 && lastIndex == -1) {
            System.out.printf(NUMBER_NOT_FOUND, toFind);
            return;
        }
        System.out.printf(NUMBER_FOUND_RANGE_BETWEEN, toFind, lastIndex + 1 - firstIndex, firstIndex, lastIndex);
    }
}
