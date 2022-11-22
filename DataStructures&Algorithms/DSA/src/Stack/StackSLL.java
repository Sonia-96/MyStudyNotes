package Stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;

public class StackSLL<T> {
    private class Node {
        T data;
        Node next;

        Node(T x, Node n) {
            data = x;
            next = n;
        }
    }

    Node dummy;
    int size;

    public StackSLL() {
        dummy = new Node(null, null);
        size = 0;
    }

    public void push(T x) {
        dummy.next = new Node(x, dummy.next);
        size++;
    }

    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return dummy.next.data;
    }

    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T res = dummy.next.data;
        dummy.next = dummy.next.next;
        size--;
        return res;
    }

    @Test
    public void test() {
        StackSLL<Integer> stack = new StackSLL<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.peek());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.peek());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.peek());
        assertEquals(1, stack.pop());
        assertThrows(EmptyStackException.class, stack::peek);
        assertThrows(EmptyStackException.class, stack::pop);
    }
}
