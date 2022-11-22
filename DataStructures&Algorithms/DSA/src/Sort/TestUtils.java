package Sort;

import java.util.ArrayList;
import java.util.Random;

public class TestUtils {
    /**
     * This method generates and returns an ArrayList of integers 1 to size in ascending order.
     * @param size the size of the ArrayList to be generated
     * @return the generated ArrayList
     */
    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> arr = new ArrayList<>(size);
        for (int i = 1; i <= size; i++ ) {
            arr.add(i);
        }
        return arr;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in permuted order
     * @param size the size of the ArrayList to be generated
     * @return the generated ArrayList
     */
    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> arr = generateBestCase(size);
        Random rand = new Random();
        for (int i = size - 1; i >= 0; i--) {
            swap(arr, i, rand.nextInt(i + 1));
        }
        return arr;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in descending order.
     * @param size the size of the ArrayList to be generated
     * @return the generated ArrayList
     */
    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> arr = new ArrayList<>(size);
        for (int i = size; i > 0; i--) {
            arr.add(i);
        }
        return arr;
    }

    private static <T> void swap(ArrayList<T> arr, int i, int j) {
        if (i != j) {
            arr.set(i, arr.set(j, arr.get(i)));
        }
    }
}
