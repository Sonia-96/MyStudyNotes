package Queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueSLL<T> {
    private class Node {
        T data;
        Node next;

        public Node(T x, Node n) {
            data = x;
            next = n;
        }
    }

    private Node head;
    private Node tail;
    int size;

    public QueueSLL() {
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
        T x = head.data;
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
        size++;
    }

    public T peek() {
        if (head == null) {
            throw new NoSuchElementException("The queue is empty!");
        }
        return head.data;
    }

    @Test
    public void test() {
        Queue<Integer> expectedQueue = new ArrayDeque<>();
        QueueSLL<Integer> myQueue = new QueueSLL<Integer>();
        Random rand = new Random(1);
        int size = 1000;
        for (int i = 0; i < size; i++) {
            int num  = rand.nextInt();
            expectedQueue.add(num);
            myQueue.enqueue(num);
        }
        for (int i = 0; i < size; i++) {
            assertEquals(expectedQueue.peek(), myQueue.peek());
            assertEquals(expectedQueue.poll(), myQueue.dequeue());
        }
    }
}
