# Priority Queue

A Priority Queue is an ADT in which each element has a priority associate to it. The element with a higher priority is serverd before the elements with lower priorities. 

## Binary Heap

1. Binary Heap is an implementation of Priority Queue. And bianry heap should be a **complete tree**.

   - Min Heap: any node is smaller than its children
   - Max Heap: any node is bigger than its children

2. Operations of binary heap (I'll take Min Heap as an example in this note):

   - main operations:

     - `void add(T item, int priority)`: O(logN) in worst case, O(1) in average and best cases

     - `T getMin()`: O(1)

     - `T deleteMin()`: O(logN)

     - `void changePriority(T item, int priority)`: change the priority of the item, O(logN)

   - helper functions:

     - `siftUp(index)` (or percolateUp): If the element is bigger than its parent, swap it with the parent. Keep swaping until the node is smaller than the parent.
     - `siftDown(index)` (or percolateDown): If the element is smaller than one of its children, swap it with the smaller child. Keep swaping until the node is bigger than its children.

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

Take Min Heap as an example (0-based):

#### Initialization

```java
public class MinHeap<T> { // no duplicates are allowed
  static class Node<T> {
    T data;
    int priority;

    public Node(T x, int p) {
      data = x;
      priority = p;
    }
  }

  private final ArrayList<Node<T>> heap;
  private final HashMap<T, Integer> indices;
  private int size;

  public MinHeap() {
    heap = new ArrayList<>();
    indices = new HashMap<>();
    size = 0;
  }
}
```

#### add

put the element to the last of the array, then `siftUp(size - 1)`

```java
// return true if this element is added, or false if it already exists.
public boolean add(T item, int priority) {
  if (item == null) {
    throw new NullPointerException();
  }
  if (contains(item)) {
    return false;
  }
  heap.add(new Node<>(item, priority));
  size++;
  indices.put(item, size() - 1);
  siftUp(size() - 1);
  return true;
}
```

Time coplexity: O(logN) in worst case, O(1) in average

#### deleteMin

swap the first element with the last element, size -= 1, then `siftDown(0)`

```java
public T deleteMin() {
  T ret = getMin();
  swap(0, size() - 1);
  size--;
  siftDown(0);
  return ret;
}
```

Time complexity: O(logN)

#### getMin

return the top (smallest) element

```java
public T getMin() {
  return heap.get(0).data;
}
```

Time complexity: O(1)

#### changePriority

```java
public void changePriority(T item, int priority) {
  if (!contains(item)) {
    throw new NoSuchElementException();
  }
  int index = indices.get(item);
  Node<T> node = heap.get(index);
  int oldPriority = node.priority;
  node.priority = priority;
  if (priority > oldPriority) {
    siftDown(index);
  } else {
    siftUp(index);
  }
}
```

Time complexity: O(logN)

## Applications

### Heap Sort 

1. Build the heap (**heapify**): O(N)
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

