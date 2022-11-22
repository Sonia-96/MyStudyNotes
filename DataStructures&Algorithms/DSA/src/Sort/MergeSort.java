package Sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSort {
    public static void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, temp, 0, nums.length);
    }

    private static void mergeSort(int[] nums, int[] temp, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, temp, start, mid);
        mergeSort(nums, temp, mid, end); // half-open range
        merge(nums, temp, start, mid, end);
    }

    private static void merge(int[] nums, int[] temp, int start, int mid, int end) { // sort happends in merge()
        int left = start, right = mid, p = start;
        for (; left < mid && right < end; p++) {
            if (nums[left] <= nums[right]) {
                temp[p] = nums[left++];
            } else {
                temp[p] = nums[right++];
            }
        }
        for (; left < mid; left++) {
            temp[p++] = nums[left];
        }
        for (; right < end; right++) {
            temp[p++] = nums[right];
        }
        System.arraycopy(temp, start, nums, start, end - start);
    }

    @Test
    public void test() {
        Random rand = new Random();
        int size = 100000;
        int[] randomNums = new int[size];
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int temp = rand.nextInt(1000);
            randomNums[i] = temp;
            result[i] = temp;
        }
        Arrays.sort(result);
        mergeSort(randomNums);
        assertArrayEquals(result, randomNums);
    }
}
