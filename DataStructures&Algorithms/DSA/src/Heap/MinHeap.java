package Heap;

import java.util.ArrayList;
import java.util.HashMap;

public class MinHeap<T> { // min heap with change priority
    private class Node {
        T data;
        int priority;

        public Node(T x, int p) {
            data = x;
            priority = p;
        }
    }

    private ArrayList<Node> heap;
    private HashMap<Node, Integer> indices; // TODO use Node or T sd key?

    public MinHeap() {
        heap = new ArrayList<>();
        indices = new HashMap<>();
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
     * Add an item to the boolean
     * @param item the item to be added
     * @param priority the priority of the item
     * @return true if this element is added. Otherwise, return false.
     */
    public boolean add(T item, int priority) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item, priority);
        heap.add(node);
        indices.put(node, heap.size() - 1);
        siftUp(heap.size() - 1);
        return true;
    }

    private void siftUp(int i) {
        int p = parent(i);
        while (p >= 0 && heap.get(i).priority < heap.get(p).priority) {
            swap(i, p);
            i = p;
            p = parent(i);
        }
    }

    private void swap(int i, int j) {
        heap.set(i, heap.set(j, heap.get(i)));
        indices.put(heap.get(i), i);
        indices.put(heap.get(j), j);
    }

}
