package Sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BubbleSort {
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean done = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    done = false;
                }
            }
            if (done) break;
        }
    }

    public static void bubbleSort2(int[] nums) {
        int unsortedEnd = nums.length;
        while (unsortedEnd > 1) {
            int lastSwap = 0;
            for (int j = 0; j < unsortedEnd - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    lastSwap = j;
                }
            }
            unsortedEnd = lastSwap + 1;
            // after this round, the unsorted part is [0, lastSwap]
            // in the next round, we only need to compare adjacent elements in unsorted part
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
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
        bubbleSort(randomNums);
        assertArrayEquals(result, randomNums);
    }
}
