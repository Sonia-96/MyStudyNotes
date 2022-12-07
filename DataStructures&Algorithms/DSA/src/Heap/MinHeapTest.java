package Heap;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {
    @Test
    public void test() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        assertEquals(0, minHeap.size());
    }

    @Test
    public void oracleTest() {
        PriorityQueue<MinHeap.Node<Integer>> expected = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.priority, o2.priority));
        MinHeap<Integer> actual = new MinHeap<>();
        Random rand = new Random(1);
        for(int i = 0; i < 10000; i++) {
            int item = rand.nextInt(), priority = rand.nextInt();
            expected.add(new MinHeap.Node<>(item, priority));
            actual.add(item, priority);
            assertEquals(expected.size(), actual.size());
            assertEquals(expected.peek().data, actual.getMin());
        }
        for (int i = 0; i < 10000; i++) {
            assertEquals(expected.poll().data, actual.deleteMin());
            assertEquals(expected.size(), actual.size());
        }
    }
}
