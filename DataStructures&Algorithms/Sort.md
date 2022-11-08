# Sort

In this document, all sorting methods sort in ascending order.



Helper function:

```java
private static void swap(int[] nums, int i, int j) {
  int temp = nums[i];
  nums[i] = nums[j];
  nums[j] = temp;
}
```

## Selection Sort

1. Divide the array into two parts: sorted part, and unsorted part. In the beginning, the unsorted part is [0 : n), the sorted part is [0 : 0), which is empty. 
2. Iterate over unsorted part, find the minimum element, and put it in the begining of the unsorted part. Repeate this step until the whole array is sorted. 

```java
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
```

- Time complexity:

  - worst case: O(n<sup>2</sup>)

  - best case: O(n<sup>2</sup>)

  - average: O(n<sup>2</sup>)

- space complexity: O(1)
- unstable sorting algorithm, e.g., {5, 8, 5, 2, 9}
- inplace sort

## Bubble Sort

## Insertion Sort

```java
void static insertionSort(int[] nums) {
  for (int i = 1; i < nums.length; i++) {
    for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
      swamp(nums, j, j - 1);
    }
  }
}
```

- Time complexity:

  - worst case: O(n<sup>2</sup>);

  - best case: O(n), the array is already sorted

  - average: O(n<sup>2</sup>)

- space complexity: O(1)

- stable sorting algorithm

- inplace sort

## Shell Sort

## Merge Sort

## Quick Sort

1. choose a random number in the array as a pivot
2. iterate over the elements, put the elements smaller than pivot on its left, bigger elements on its right
3. repeat step1 & 2 on subarrays besides the pivot, until the length of the array is 1

```java
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
```

Time complexity:

- worst case: O(n<sup>2</sup>), everytime we choose the smallest or biggest element as the pivot
  - T(n) = n + T(n-1) = n + (n - 1) + T(n - 2) = ... = n + (n - 1) + ... + 1 = O(n<sup>2</sup>)

- best case: O(nlogn), the size of the two subarrays after `partition` are equal
  - T(n) = n + 2T(n/2) = n + n + 4 T(n/4) = ... = n + n + ... + n (repeat nlog<sub>2</sub>n times) = O(nlogn)

- average: O(nlogn)

## Count Sort

## Heap Sort

