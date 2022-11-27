package Sort;

import org.w3c.dom.Node;

public class BinarySearch {
    // check if an array contains an element
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

    // find the index to insert the element into an array
    public static int getIndex(int[] nums, int goal) {
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= goal) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
