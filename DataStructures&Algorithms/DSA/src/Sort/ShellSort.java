package Sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShellSort {
    private static void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void shellSort(int[] nums) {
        int gap = nums.length >> 1;
        while (gap > 0) {
            for (int i = gap; i < nums.length; i++) {
                for (int j = i; j >= gap && nums[j] < nums[j - gap]; j -= gap) {
                    swap(nums, j, j - gap);
                }
            }
            gap >>= 1;
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
        shellSort(randomNums);
        assertArrayEquals(result, randomNums);
    }
}
