package Stack;

import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;

public class MinStack {
    private final Stack<Integer> stack;
    private final Stack<Integer> min;
    private int size = 0;

    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int item) {
        stack.push(item);
        min.push((size == 0 || min.peek() > item )? item : min.peek());
        size++;
    }

    public void pop() {
        stack.pop();
        min.pop();
        size--;
    }

    public int min() {
        return min.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        assertEquals(3, minStack.min());
        minStack.push(4);
        assertEquals(3, minStack.min());
        minStack.push(1);
        assertEquals(1, minStack.min());
        minStack.pop();
        assertEquals(3, minStack.min());
    }
}
