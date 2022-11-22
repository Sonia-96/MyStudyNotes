package Stack;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackSLL<T> {
    private class Node {
        T item;
        Node next;

        public Node(T x, Node n) {
            item = x;
            next = n;
        }
    }

    Node head;

    void push(T x) {
        head = new Node(x, head);
    }

    public T pop() {
        if (head == null) {
            throw new NoSuchElementException("The stack is empty!");
        }
        T x = head.item;
        head = head.next;
        return x;
    }

    public T peek() {
        if (head == null) {
            throw new NoSuchElementException("The stack is empty!");
        }
        return head.item;
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
        assertThrows(NoSuchElementException.class, stack::peek);
        assertThrows(NoSuchElementException.class, stack::pop);
    }
}
