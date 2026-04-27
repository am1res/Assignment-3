import java.util.Arrays;

public class Experiment {

    private Sorter sorter = new Sorter();
    private Searcher searcher = new Searcher();

    public long measureSortTime(int[] arr, String type) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        long start = System.nanoTime();
        if (type.equals("basic")) {
            sorter.basicSort(copy);
        } else if (type.equals("advanced")) {
            sorter.advancedSort(copy);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.binary_search(arr, target);
        long end = System.nanoTime();
        return end - start;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};
        String[] sizeNames = {"Small (10)", "Medium (100)", "Large (1000)"};

        System.out.println("  --- SORTING & SEARCHING EXPERIMENT RESULTS --- ");

        for (int s = 0; s < sizes.length; s++) {
            int size = sizes[s];
            System.out.println("\n--- " + sizeNames[s] + " elements ---");

            int[] randomArr = sorter.generateRandomArray(size);

            int[] sortedArr = Arrays.copyOf(randomArr, randomArr.length);
            Arrays.sort(sortedArr);

            long bubbleRandom = measureSortTime(randomArr, "basic");
            System.out.println("[Bubble Sort]  Random array:  " + bubbleRandom + " ns");

            long bubbleSorted = measureSortTime(sortedArr, "basic");
            System.out.println("[Bubble Sort]  Sorted array:  " + bubbleSorted + " ns");

            long mergeRandom = measureSortTime(randomArr, "advanced");
            System.out.println("[Merge Sort]   Random array:  " + mergeRandom + " ns");

            long mergeSorted = measureSortTime(sortedArr, "advanced");
            System.out.println("[Merge Sort]   Sorted array:  " + mergeSorted + " ns");

            int target = sortedArr[size / 2];
            long searchTime = measureSearchTime(sortedArr, target);
            System.out.println("[Binary Search] Target=" + target + "  Time: " + searchTime + " ns");
        }

        System.out.println(" --- SMALL ARRAY DEMO (10 elements) --- ");
        int[] demo = sorter.generateRandomArray(10);
        System.out.print("Original:  ");
        sorter.printArray(demo);

        int[] demoCopy = Arrays.copyOf(demo, demo.length);
        sorter.basicSort(demoCopy);
        System.out.print("Bubble Sort: ");
        sorter.printArray(demoCopy);

        int[] demoCopy2 = Arrays.copyOf(demo, demo.length);
        sorter.advancedSort(demoCopy2);
        System.out.print("Merge Sort:  ");
        sorter.printArray(demoCopy2);

        int target = demoCopy2[5];
        int index = searcher.binary_search(demoCopy2, target);
        System.out.println("Binary Search for " + target + ": found at index " + index);
    }
}
