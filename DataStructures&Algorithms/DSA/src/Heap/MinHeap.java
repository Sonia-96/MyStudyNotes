package Heap;

import java.util.*;

// min heap with changePriority
public class MinHeap<T> {
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

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return i * 2 + 2;
    }

    /**
     * Add an item with the given priority.
     * @param item the item to be added
     * @param priority the priority of the item
     * @return true if this element is added, false if it already exists.
     */
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

    public T getMin() {
        return heap.get(0).data;
    }

    /**
     * Remove the top (smallest) element from the heap.
     * @return true if this element is deleted, or false if it doesn't exist.
     */
    public T deleteMin() {
        T ret = getMin();
        swap(0, size() - 1);
        size--;
        siftDown(0);
        return ret;
    }

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

    /**
     * Check if an item exists.
     * @param item the item to be checked
     * @return true if the PQ contains the item, otherwise false.
     */
    public boolean contains(T item) {
        return indices.containsKey(item);
    }

    private void siftUp(int i) {
        int p = parent(i);
        while (p >= 0 && heap.get(i).priority < heap.get(p).priority) {
            swap(i, p);
            i = p;
            p = parent(i);
        }
    }

    private void siftDown(int i) {
        while (i < size()) {
            int left = leftChild(i), right = rightChild(i), minIndex = i;
            if (left < size() && heap.get(i).priority > heap.get(left).priority) {
                minIndex = left;
            }
            if (right < size() && heap.get(minIndex).priority > heap.get(right).priority) {
                minIndex = right;
            }
            if (i != minIndex) {
                swap(i, minIndex);
                i = minIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        if (i != j) {
            heap.set(i, heap.set(j, heap.get(i)));
            indices.put(heap.get(i).data, i);
            indices.put(heap.get(j).data, j);
        }
    }

    public int size() {
        return size;
    }
}
