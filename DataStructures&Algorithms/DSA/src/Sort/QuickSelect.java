package Sort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class QuickSelect {

    /**
     * Find the n-th the smallest element in an array
     * @param nums the array used to find the element
     * @param n the order
     */
    public static int quickSelect(int[] nums, int n) {
        if (n >= nums.length) {
            throw new NoSuchElementException();
        }
        return quickSelect(nums, 0, nums.length, n);
    }

    // find n-th the smallest element in the array
    public static int quickSelect(int[] nums, int start, int end, int n) {
        int p = partition(nums, start, end);
        if (p == n) {
            return nums[p];
        }
        if (p < n) {
            return quickSelect(nums, p + 1, end, n);
        }
        return quickSelect(nums, start, p, n);
    }

    public static int partition(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        int pivot = nums[mid];
        swap(nums, mid, end - 1);
        int left = start, right = end - 2;
        while (left <= right) {
            if (nums[left] <= pivot) {
                left++;
                continue;
            }
            if (nums[right] >= pivot) {
                right--;
                continue;
            }
            swap(nums, left, right);
            left++;
            right--;
        }
        swap(nums, left, end - 1);
        return left;
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
        int[] test = {2, 5, 8, 1};
        assertEquals(5, quickSelect(test, 2));
        for (int size = 10; size < 10000; size++) {
            ArrayList<Integer> lst = TestUtils.generateAverageCase(size);
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = lst.get(i);
            }
            assertEquals(size / 2 + 1, quickSelect(arr, size / 2));
            assertEquals(10, quickSelect(arr, 9));
        }
    }
}
