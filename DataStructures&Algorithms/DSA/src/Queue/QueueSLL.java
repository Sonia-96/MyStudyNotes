package Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueSLL<T> {
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

    public boolean parenthesisValidation(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                char top = stack.pop();
                if (c == ')' && top != '(' || c == ']' && top != '[' || c == '}' && top != '}') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        String s1 = "[(()){[}]";
        Assertions.assertFalse(parenthesisValidation(s1));
    }
}
