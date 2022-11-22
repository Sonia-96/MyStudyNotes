# Sorting

In this document, all sorting methods sort in ascending order.

[0, N): half open range. I use this convention in all sorting algorithms.

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
2. Compare every 2 adjacent elements in the unsorted part, if the left element is bigger than the right one, swap them. After this round, we can put the biggest element on the rightmost of the unsorted part. Then the sorted part becomes [n - 1 : n) (right border - 1), unsorted part becomes [0: n - 1) (left border - 1). The whole process is like bubbling. So we call this algorithm bubble sort.
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

  - average: O(n<sup>2</sup>) (accurately it's O(n<sup>2</sup>/4))

- space complexity: O(1)
- stable sorting algorithm
- inplace sort

### Insertion Sort

1. Divide the array into 2 parts: sorted part and unsorted part. In the begining, the sorted part is [0: 1), unsorted part is [1: n)
2. Put the first element of unsorted part to the correct position of the sorted part, then the right border of sorted part + 1, the left border of unsorted part + 1
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

## Merge Sort

1. Split the array from middle into 2 subarrays, then sort them (use recursion) -- sort()
2. Merge 2 subarrays -- merge()

```java
private static void merge(int[] nums, int[] temp, int start, int mid, int end) { // sort happends in merge()
  int left = start, right = mid, p = start;
  for (; left < mid && right < end; p++) {
    if (nums[left] <= nums[right]) {
      temp[p] = nums[left++];
    } else {
      temp[p] = nums[right++];
    }
  }
  for (; left < mid; left++) {
    temp[p++] = nums[left];
  }
  for (; right < end; right++) {
    temp[p++] = nums[right];
  }
  System.arraycopy(temp, start, nums, start, end - start);
}

private static void mergeSort(int[] nums, int[] temp, int start, int end) {
  if (end - start < 2) {
    return;
  }
  int mid = start + (end - start) / 2;
  mergeSort(nums, temp, start, mid);
  mergeSort(nums, temp, mid, end); // half-open range
  merge(nums, temp, start, mid, end);
}

public static void mergeSort(int[] nums) {
  int[] temp = new int[nums.length];
  mergeSort(nums, temp, 0, nums.length);
}
```

- Time Complexity: O(NlogN) (average case, best-case, worst case are all O(NlogN))
  - f(N) = N + 2f(N/2) = N +  2(N/2 + 2f(N/4)) = 2N + 2<sup>2</sup>f(N/4) = ... = Nlog<sub>2</sub>N+ 2<sup>log<sub>2</sub>N</sup>f(1) = Nlog<sub>2</sub>N+ log<sub>2</sub>N = (N + 1) log<sub>2</sub>N
- Space Complexity: O(N)
- Stable sorting algorithm
- Non-inplace algorithm

Tricks:

- allocating a temp array for merging is costly. It's better to allocate the whole thing all at one in the driver method, then pass it to all recursive calls

- When N is small, intertion sort is faster than merge sort. To exaplain, the time complexity of merge sort is O(NlogN), and insertion sort is O(n<sup>2</sup> / 4). If N = 10, merge sort costs 33 to sort the array, and insertion sort only costs 25. Therefore, in the recursive call, when `end - start < 10`, we can switch to insertion sort.

  ```java
  if (end - start < threshold) {
    selectionSort(nums, start, end);
  } else {
    int mid = start + (end - start) / 2;
    mergeSort(nums, start, mid);
    mergeSort(nums, mid, end);
    merge(nums, start, mid, end);
  }
  ```

// TODO: [iterative merge sort](https://softwareengineering.stackexchange.com/questions/367544/in-merge-sort-why-not-just-split-to-individual-items-immediately-rather-than-re)

## Quick Sort

A disadvantage of Merge Sort is that it takes O(N) space to merge two subarrays. Quick Sort can improve this. It uses `partition()` to move elements to the right place in O(N) time, so we don't need to merge.

1. `partition(nums)`: (trickest part in Quick Sort!!!)
   - choose a `pivot` (there are three strategies to choose a pivot - random, middle, median-of-three)

   - iterate over the elements, put the elements smaller than the pivot on its left, bigger elements on its right

   - return the index of the pivot
2. repeat step1 on the left subarray and the right subarray, until the length of the array is smaller than 2

### Recursion

```java
public static void quickSort(int[] nums) {
  quickSort(nums, 0, nums.length);
}

private static void quickSort(int[] nums, int start, int end) {
  if (end - start > 2) return;
  int p = partition(nums, start, end);
  partition(nums, start, p); // half-open range
  partition(nums, p + 1, end);
}
```

### Partition

Partitions have many different implementations. 

1. two-sided version.

   ```java
       public static int partition(int[] nums, int start, int end) {
           int p = start + (end - start) / 2;
           int pivot = nums[p];
           swap(nums, p, end - 1);
           int left = start; // the right border of elements smaller than pivot
           int right = end - 2; // the left border of elements bigger than pivot
           while (left < right) {
               while (left < right && nums[left] <= pivot) {
                   left++;
               }
               while (right > left && nums[right ] >= pivot) {
                   right--;
               }
               swap(nums, left, right);
           }
           if (nums[left] >= nums[end - 1]) {
               swap(nums, end - 1, left);
               return left;
           }
           return end - 1;
       }
   ```

   ```java
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
   ```

   

2. left-sided version

   ```java
   private static int partition(int[] nums, int start, int end) { // TODO 复习这种partition
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
   ```

Time complexity of Quick Sort: 

- worst case: O(n<sup>2</sup>), everytime we choose the smallest or biggest element as the pivot
  - T(n) = n + T(n-1) = n + (n - 1) + T(n - 2) = ... = n + (n - 1) + ... + 1 = O(n<sup>2</sup>)

- best case: O(nlogn), the size of the two subarrays after `partition` are equal
  - T(n) = n + 2T(n/2) = n + n + 4 T(n/4) = ... = n + n + ... + n (repeat nlog<sub>2</sub>n times) = O(nlogn)

- average: O(nlogn)

###  3-way quick sort //TODO

// TODO 有问题，如果array所有元素都一样，会陷入死循环

How to choose the pivot? // 重温这段zoom

- random
- middle value: if the array is close to be completely sorted, the middle value would be close the median -> median-ish pivot (pretty good!)
  - but you might still get O(n<sup>2</sup>) when you are un lucky. e.g., every time you pick a smallest number 

## Summary

### Comparison Sorts

An array with n elements can have n! possibilities. Every comparison we make can reduce the possibilities by a factor of 2. For example, a 3-element array (x, y, z) has 3! = 6 possibilities. If we know x < z, the possibilities will reduce to 3. Therefore, we need to do log<sub>2</sub>n! comparisons to get only 1 possibility.

logn! = log(n * (n - 1) * (n - 2) * ... * 1) = log n + log n-1 + ... 1 + 0 < nlogn = O(nlogn) (acutally log n! = Θ(nlogn))

(Actually log<sub>2</sub>n ≈ nlogn, [refer](https://mathworld.wolfram.com/StirlingsApproximation.html))

【option + x -> ≈】

:star:**Conclusion**: 

1. For any sorting method based on comparison, its time complexity is at leastO(nlogn).

2. Any sort algorithm has at least O(n) comparisons because it need to check if the array is already sorted.

### Time complexity

|                | Best             | Worst              | Average             | Stable | Notes                                              |
| -------------- | ---------------- | ------------------ | ------------------- | ------ | -------------------------------------------------- |
| Selection Sort | O(N<sup>2</sup>) | O(N<sup>2</sup>)   | O(N<sup>2</sup>)    | No     |                                                    |
| Bubble Sort    | O(N)             | O(N<sup>2</sup>)   | O(N)                | Yes    |                                                    |
| Insertion Sort | O(N)             | O(N<sup>2</sup>)   | O(N<sup>2</sup>)    | Yes    | O(N<sup>2</sup>/4), takes advantages of sortedness |
| Shell Sort     | O(NlogN)         | O(N<sup>1.5</sup>) | O(N<sup>1.25</sup>) | No     |                                                    |
| Merge Sort     | O(NlogN)         | O(NlogN)           | O(NlogN)            | Yes    | requires O(N) space                                |
| Quick Sort     | O(NlogN)         | O(N<sup>2</sup>)   | O(NlogN)            | No     |                                                    |

- In practice, quick sort is faster than merge sort

## Count Sort // TODO

## Heap Sort // TODO

# Search

## Binary Search

Time complexity: O(logN)

1. Check if an array contains this element:

   ```java
   public static boolean binarySearch(int[] nums, int goal) {
     int start = 0, end = nums.length;
     while (start < end) {
       int mid = start + (end - start) / 2; 
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
   or:

   ```java
   public static boolean binarySearch(int[] nums, int goal) {
     int start = 0, end = nums.length - 1;
     while (start <= end) {
       int mid = start + (end - start) / 2; 
       if (nums[mid] == goal) {
         return true;
       }
       if (nums[mid] < goal) {
         start = mid + 1;
       } else {
         end = mid - 1;
       }
     }
     return false;
   }
   ```

   

2. Insert an element:

   ```java
   public static int binarySearch(int[] nums, int element) {
     int start = 0, end = nums.length;
     while (start < end) {
       int mid = start + (end - start) / 2;
       if (nums[mid] <= element) {
         start = mid + 1;
       } else {
         end = mid;
       }
     }
     return start;
   }
   ```

   or:

   ```java
   public static boolean binarySearch(int[] nums, int element) {
     int start = 0, end = nums.length - 1;
     while (start <= end) {
       int mid = start + (end - start) / 2;
       if (nums[mid] <= element) {
         start = mid + 1;
       } else {
         end = mid - 1;
       }
     }
     return start;
   }
   ```

   

## Quick Select

```java
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
```

Time complexity:

- Worst case: O(NlogN)
- best case: O(N)
- average case: O(NlogN)
