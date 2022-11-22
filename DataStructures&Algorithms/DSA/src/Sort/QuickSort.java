package Sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSort {
    private static void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void quickSort(int[] nums) {
        testQuickSort(nums, 0, nums.length);
    }

    private static void testQuickSort(int[] nums, int start, int end) {
        if (end - start < 2) return;
        int p = partition(nums, start, end); // [start, end)
        testQuickSort(nums, start, p);
        testQuickSort(nums, p + 1, end);
    }

    public static int partition(int[] nums, int start, int end) {
        int p = start + (end - start) / 2;
        int pivot = nums[p];
        swap(nums, p, end - 1);
        int left = start; // the right border of elements smaller than pivot
        int right = end - 2; // the left border of elements bigger than pivot
        while (left < right) {
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            while (right > left && nums[right ] >= pivot) {
                right--;
            }
            swap(nums, left, right);
        }
        if (nums[left] >= nums[end - 1]) {
            swap(nums, end - 1, left);
            return left;
        }
        return end - 1;
    }

    private static int partition2(int[] nums, int start, int end) {
        int p = start + (int) (Math.random() * (end - start));
        int pivot = nums[p];
        int left = start; // right border of smaller elements
        swap(nums, start, p);
        for (int i = start + 1; i < end; i++) {
            if (nums[i] < pivot) {
                left++;
                swap(nums, left, i);
            }
        }
        swap(nums, left, start);
        return left;
    }

    @Test
    void testPartition() {
        int[] arr1 = {10, -2, 5, 7, 0};
        assertEquals(2, partition(arr1, 0, 5));
        assertArrayEquals(new int[] {0, -2, 5, 7, 10}, arr1);
        assertEquals(0, partition(arr1, 0, 2));
        assertArrayEquals(new int[] {-2, 0, 5, 7, 10}, arr1);
        assertEquals(4, partition(arr1, 3, 5));
        assertArrayEquals(new int[] {-2,0, 5, 7, 10}, arr1);
    }

    @Test
    void testQuickSort() {
        int[] nums = {3, 3, 3};
        quickSort(nums);
        assertArrayEquals(new int[] {3, 3, 3}, nums);

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
        quickSort(randomNums);
        assertArrayEquals(result, randomNums);

        int[] arr = {17850135, -1154715079, -423279216, 1260042744};
        int[] arr2 = {17850135, -1154715079, -423279216, 1260042744};
        Arrays.sort(arr2);
        quickSort(arr);
        Assertions.assertArrayEquals(arr2,arr);
    }
}
