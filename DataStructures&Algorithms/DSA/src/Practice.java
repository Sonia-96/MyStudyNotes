import java.util.SortedSet;
import java.util.TreeSet;

public class Practice {
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        SortedSet<Dog> set = new TreeSet<>();
        set.add(new Dog("nihao", 10));
        set.add(new Dog("nihao", 10));
        for (Dog s : set) {
            System.out.println(s);
        }
    }
}
