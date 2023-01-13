package Heap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class HeapSort {
    int[] nums;
    int size;

    public HeapSort(int[] nums) {
         this.nums = nums;
         size = nums.length;
    }

    public void heapSort() {
        for (int i = (nums.length - 1)/ 2; i >= 0; i--) {
            siftDown(i);
        }
        int n = size;
        for (int i = 0; i < n - 1; i++) {
            swap(0, --size);
            siftDown(0);
        }
    }

    private int left(int i) {
        return i * 2 + 1;
    }

    private int right(int i) {
        return i * 2 + 2;
    }

    private void siftDown(int i) {
        while (i < size) {
            int left = left(i), right = right(i), minIndex = i;
            if (left < size && nums[i] > nums[left]) {
                minIndex = left;
            }
            if (right < size && nums[minIndex] > nums[right]) {
                minIndex = right;
            }
            if (minIndex != i) {
                swap(i, minIndex);
                i = minIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    @Test
    public void test() {
        int[] nums = {7, -1, 0, 92, 54, 10};
        HeapSort heapSort = new HeapSort(nums);
        heapSort.heapSort();
        assertArrayEquals(new int[] {92, 54, 10, 7, 0, -1}, nums);
    }

    @Test
    public static void main(String[] args) {
        int size = 10000;
        Random rand = new Random();
        int[] nums1 = new int[size];
        int[] nums2 = new int[size];
        for (int i = 0; i < size; i++) {
            int num = rand.nextInt();
            nums1[i] = num;
            nums2[i] = num;
        }
        Arrays.sort(nums1);
        int[] nums = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            nums[size - i - 1] = nums1[i];
        }
        HeapSort heapSort = new HeapSort(nums2);
        heapSort.heapSort();
        assertArrayEquals(nums, nums2);
    }
}
