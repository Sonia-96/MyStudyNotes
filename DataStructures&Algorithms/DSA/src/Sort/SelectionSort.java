package Sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SelectionSort {
    private static void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    @Test
    public void test() {
        Random rand = new Random();
        int size = 1000;
        int[] randomNums = new int[size];
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int temp = rand.nextInt(1000);
            randomNums[i] = temp;
            result[i] = temp;
        }
        Arrays.sort(result);
        selectionSort(randomNums);
        assertArrayEquals(result, randomNums);
    }
}
