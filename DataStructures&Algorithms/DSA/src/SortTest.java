import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortTest {
    int[] nums;

    @BeforeEach
    void setUp() {
        nums = new int[] {10, -2, 5, 7, 0};
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insertionSort() {
        Sort.insertionSort(nums);
        Assertions.assertArrayEquals(new int[] {-2, 0, 5, 7, 10}, nums);
    }

    @Test
    void selectionSort() {
        Sort.selectionSort(nums);
        Assertions.assertArrayEquals(new int[] {-2, 0, 5, 7, 10}, nums);
    }

    @Test
    void quickSort() {
        Sort.quickSort(nums);
        Assertions.assertArrayEquals(new int[] {-2, 0, 5, 7, 10}, nums);
    }
}