import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortTest {
    int[] nums, result;

    @BeforeEach
    void setUp() {
        nums = new int[] {10, -2, 5, 7, 0};
        result = new int[] {-2, 0, 5, 7, 10};
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void binarySearch() {
        assertFalse(Sort.binarySearch(result, -1));
        assertTrue(Sort.binarySearch(result, 0));
    }

    @Test
    void selectionSort() {
        Sort.selectionSort(nums);
        assertArrayEquals(result, nums);
    }

    @Test
    void bubbleSort() {
        Sort.bubbleSort(nums);
        assertArrayEquals(result, nums);
    }

    @Test
    void bubbleSort2() {
        Sort.bubbleSort2(nums);
        assertArrayEquals(result, nums);
    }

    @Test
    void insertionSort() {
        Sort.insertionSort(nums);
        assertArrayEquals(result, nums);
    }

    @Test
    void shellSort() {
        Sort.shellSort(nums);
        assertArrayEquals(result, nums);
    }

    @Test
    void quickSort() {
        Sort.quickSort(nums);
        assertArrayEquals(result, nums);
    }

    @Test
    void mergeSort() {
        Sort.mergeSort(nums);
        assertArrayEquals(result, nums);
    }
}