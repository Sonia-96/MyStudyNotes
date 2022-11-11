# Sorting

In this document, all sorting methods sort in ascending order.

## Simple Sorts

Helper function:

```java
private static void swap(int[] nums, int i, int j) {
  int temp = nums[i];
  nums[i] = nums[j];
  nums[j] = temp;
}
```

### Selection Sort

1. Divide the array into two parts: sorted part, and unsorted part. In the beginning, the unsorted part is [0 : n), the sorted part is [0 : 0), which is empty. 
2. Iterate over unsorted part, find the minimum element, and put it in the begining of the unsorted part. Then the sorted part becomes [0: 1), sorted part becomes [1: n).
3. Repeate step 2, until the whole array is sorted. 

```java
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
```

selection sort always does O(n) swaps, O(n<sup>2</sup>) comparisons

- Time complexity:
  - worst case: O(n<sup>2</sup>)

  - best case: O(n<sup>2</sup>)

  - average: O(n<sup>2</sup>)

- space complexity: O(1)
- unstable sorting algorithm, e.g., {5, 8, 5, 2, 9}
- inplace sort

### Bubble Sort

1. Divide the array into 2 parts: unsorted part, sorted part. In the beginning, the unsorted part is [0 : n), sorted part is [n : n).
2. Compare every 2 adjacent elements in the unsorted part, if the left element is bigger than the right one, swap them. After this round, we can put the biggest element on the rightmost of the unsorted part. Then the sorted part becomes [n - 1 : n) (right border - 1), unsorted part becomes [0: n - 1) (left border - 1). The whole process is like bubbling. That's why we call this algorithm as bubble sort.
3. Repeat step 2. If no elements are swaped in this round, this means that the whole array is already sorted, then the program will stop. Otherwise, we'll repeat step2 until the unsorted part is empty.

#### Approach 1

```java
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
```

#### Approach 2

```java
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
```

- Time complexity:

  - worst case: O(n<sup>2</sup>) for reversely/backward sorted array

  - best case: O(n) for sorted array

  - average: O(n<sup>2</sup>)

- space complexity: O(1)
- stable sorting algorithm
- inplace sort

### Insertion Sort

1. Divide the array into 2 parts: sorted part and unsorted part. In the begining, the sorted part is [0: 1), unsorted part is [1: n)
2. Put the first element of unsorted part to the right position of the sorted part, then the right border of sorted part + 1, the left border of unsorted part + 1
3. Repeat step2 until the unsorted part becomes empty

```java
void static insertionSort(int[] nums) {
  for (int i = 1; i < nums.length; i++) { // unsorted part
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

### Summary

1. adjacent swaps

   - There are `n * (n - 1) / 2` pairs in an array. For reversely sorted array, we should do `n * (n - 1) / 2` inversions.

   - Any algorithms that swap adjacent pairs have O(n<sup>2</sup>) inversions in worst case.

2. Q: Difference between bubble sort and insertion sort?

   A: insertion sort is more efficient than bubble sort. To be specific, once Insertion Sort finds a sorte pair, it will ends this round of comparisons. But bubble sort will continue until finih this round.

## Shell Sort

希尔排序是插入排序的改进版，也称**缩小增量排序**。

插入排序慢的原因在于元素一次只能移动一位。对于那些在数组末尾的小元素，如{5, 4, 3, 2, 1}中的1，要移动n-1位才能到正确位置。希尔排序在此基础上进行了改进，采用**跳跃式分组**的策略，使得元素一次可以挪动多位。

具体实施：（这里采用增量缩小的方式为缩小为原来的一半）

- 对数组按一定增量（这里设置为数组长度的二分之一）进行分组，对每组使用插入排序算法进行排序
- 将增量缩小一半，重复上面的操作
- 不断缩小增量，当增量缩小至0时，排序完成

![Shell Sort](./assets/ShellSort.jpg)

```java
public static void shellSort(int[] nums) {
  int gap = nums.length >> 1;
  while (gap > 0) {
    for (int i = gap; i < nums.length; i++) {
      for (int j = i; j >= gap && nums[j] < nums[j - gap]; j -= gap) {
          swap(nums, j, j - gap)
      }
    }
    gap >>= 1;
  }
}
```



## Comparison Sorts

3 elements array (x, y, z) has 3! = 6 possibilities

Every comparison we make can reduce the possibilities by a factor of 2. (e.g., x < z make possibilities become 3)

So we need to do log<sub>2</sub>n! comparisons to get only 1 possibility.

logn! = log(n * (n - 1) * (n - 2) * ... * 1) = log n + log n-1 + ... 1 + 0 < nlogn = O(nlogn) (acutally log n! = Θ(nlogn))

**Conclusion**: for any comparison sort, the worst case numbers of comparison is O(nlogn)

Any sort algorithm has at least O(n) comparisons because it need to check if the array is already sorted.

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

# Search

## Binary Search

```java
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
```

Time complexity: O(logN)
