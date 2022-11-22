package Queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public class Queue_CircularArray {
    private static int INITIAL_CAPACITY = 10;
    private int[] data;
    private int size;
    private int capacity;
    int front;
    int back;

    Queue_CircularArray() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
        front = 0;
        back = -1;
    }

    public void enqueue(int x) {
        resize();
        back = (back + 1) % capacity;
        data[back] = x;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty!");
        }
        int res = data[front];
        front = (front + 1) % capacity;
        size--;
        return res;
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty!");
        }
        return data[front];
    }

    private void resize() {
        if (size == capacity) {
            int[] temp = new int[capacity * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = data[(front + i) % capacity];
            }
            data = temp;
            capacity *= 2;
            front = 0;
            back = size - 1;
        }
    }

    @Test
    public void test() {
        Queue<Integer> expectedQueue = new ArrayDeque<>();
        Queue_CircularArray myQueue = new Queue_CircularArray();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int num  = rand.nextInt();
            expectedQueue.add(num);
            myQueue.enqueue(num);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(expectedQueue.peek(), myQueue.peek());
            assertEquals(expectedQueue.poll(), myQueue.dequeue());
        }
    }
}
