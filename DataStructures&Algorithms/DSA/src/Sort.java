public class Sort {
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
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

    /**
     * sort nums[start : end)
     */
    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int p = partition(nums, start, end);
        partition(nums, start, p - 1);
        partition(nums, p + 1, end);
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length);
    }
}
