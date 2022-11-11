import java.lang.reflect.Array;

public class Sort {
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static boolean binarySearch(int[] nums, int goal) {
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = start + (end - start) >> 1;
            if (nums[mid] == goal) {
                return true;
            }
            if (nums[mid] < goal) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return false;
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

    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
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

    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
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

    private static int partition(int[] nums, int start, int end) {
        int p = start + (int) (Math.random() * (end - start));
        int pivot = nums[p];
        int j = start; // right border of smaller elements
        swap(nums, start, p);
        for (int i = start + 1; i < end; i++) {
            if (nums[i] < pivot) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, j, start);
        return j;
    }

    // sort nums[start : end)
    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int p = partition(nums, start, end);
        partition(nums, start, p - 1);
        partition(nums, p + 1, end);
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] result = new int[right - left];
        int l = left, r = mid, p = 0;
        while (l < mid && r < right) {
            if (nums[l] <= nums[r]) {
                result[p++] = nums[l++];
            } else {
                result[p++] = nums[r++];
            }
        }
        for (; l < mid; l++) {
            result[p++] = nums[l];
        }
        for (; r < right; r++) {
            result[p++] = nums[r];
        }
        System.arraycopy(result, 0, nums, left, right - left);
    }

    private static void sort(int[] nums, int start, int end) {
        if (start + 1 == end) {
            return;
        }
        // int mid = (start + end) / 2; // may cause overflow
        int mid = start + (end - start) / 2;
        sort(nums, start, mid);
        sort(nums, mid, end);
        merge(nums, start, mid, end);
    }

    public static void mergeSort(int[] nums) {
        sort(nums, 0, nums.length);
    }
}
