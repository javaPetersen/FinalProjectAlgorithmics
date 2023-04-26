package enums;

public enum Algorithm {
    COPY_OVER("Copy Over Algorithm"),
    CONVERGING_POINTERS("Converging Pointers Algorithm"),
    MERGE_SORT("Merge Sort Algorithm"),
    BINARY_SEARCH("Binary Search Algorithm"),
    LINEAR_SEARCH("Linear Search Algorithm"),
    KMP_ALGORITHM("KMP Pattern Search Algorithm"),
    BOYER_MOORE_ALGORITHM("Boyer-Moore Pattern Search Algorithm");

    private final String algorithmName;

    Algorithm(String name) {
        this.algorithmName = name;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
