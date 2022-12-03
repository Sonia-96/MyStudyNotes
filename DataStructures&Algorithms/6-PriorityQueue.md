# Priority Queue

A Priority Queue is an ADT in which each element has a priority associate to it. The element with a higher priority is serverd before the elements with lower priorities. 

## Binary Heap

1. Binary Heap is an implementation of Priority Queue. And it should be a **complete tree**.

   - Min Heap: any node is smaller than its children
   - Max Heap: any node is bigger than its children

2. Operations of binary heap (I'll take Min Heap as an example in this note):

   - main operations:

     - `void add(i)`: O(logN) in worst case, O(1) in average and best cases

     - `T findMin()`: O(logN)

     - `T deleteMin()`: O(logN)

   - helper functions:

     - `swim(index)`: **percolate up**. If the element is bigger than its parent, swap it with the parent. Keep swaping until the node is smaller than the parent.
     - `sink(index):` **percolate down**. If the element is smaller than one of its children, swap it with the smaller child. Keep swaping until the node is bigger than its children.

3. Representation: use an array to store the elements. For the element at the index `i`

   - If the index is 1-based

     - parent: `i / 2`

     - left child: `i * 2`

     - right child: `i * 2 + 1`

   - if the index is 0-based

     - parent: `(i - 1) / 2`
     - left child: `i * 2 + 1`
     - right child: `i * 2 + 2`

### Implementations

Take Min Heap as an example:

1. `add`: put the element to the last of the array, then `swim(size)`

   ```java
   
   ```

   Time coplexity: O(logN) in worst case, O(1) in average

2. `deleteMin()`: swap the first element with the last element, size -= 1, then `sink(1)`

   ```java
   ```

   Time complexity: O(logN)

3. `findMin()`: return the first element

## Applications

### Heap Sort 

1. Build the heap (heapify): O(N)
   - add all elements to the array
   - Ranging from the last non-leaf element (the index is ` size / 2`, 1-based) to the root, `sink(i)`
2. Heap sort: O(NlogN)
   - repeat `size - 1` times: 
     - non-inplace: (sorted array)
       - output.add(deleteMin())
     - inplace: (reverse-sorted array)
       - deleteMin(): 
         - swap(1, size): put the smallest element to the last position
         - size--
       - sink(1) 
   - after this is done, we get an sorted array in descending order

Complexity: O(NlogN) with a big constant!

Compare to MergeSort and QuickSort:

- Not recursive
- unlike quicksort, heap sort is O(NlogN) in all cases
- onn-inplace version: need extra O(N) memory like merge sort
- inplace version: no extra space needed, kind of like selection sort
- the time complexity is  O(NlogN) with a big constant! In practice, it's slower than quick sort and merge sort

### Djikstra's Algorithm

// TODO: watch implementation of course recordings

Find the shortest path in a weight graph

use PriorityQueue<Integer, Node> (key is weight, value is node).

```java
```

