package Queue;

import java.util.NoSuchElementException;

public class Queue_LinkedList<T> {
    private class Node {
        T item;
        Node next;

        public Node(T x, Node n) {
            item = x;
            next = n;
        }
    }

    private Node head;
    private Node tail;
    int size;

    public Queue_LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T dequeue() {
        if (head == null) {
            throw new NoSuchElementException("The queue is empty!");
        }
        T x = head.item;
        head = head.next;
        size--;
        return x;
    }

    public void enqueue(T x) {
        Node node = new Node(x, null);
        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }
}
