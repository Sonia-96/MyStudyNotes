import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class test {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("x"));
    }

    @Test
    public void fizzBuzz() {
        for (int i = 0; i < 101; i++) {
            boolean dividedBy3 = i % 3 == 0, dividedBy5 = i % 5 == 0;
            if (dividedBy3) {
                System.out.print("Fizz");
            }
            if (dividedBy5) {
                System.out.print("Buzz");
            }
            if (!dividedBy3 & !dividedBy5) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    @Test
    public void testFibonacci() {
        assertEquals(0, fibonacciRecur(0));
        assertEquals(1, fibonacciRecur(1));
        assertEquals(1, fibonacciRecur(2));
        assertEquals(fibonacciIter(10), fibonacciRecur(10));
        System.out.println(fibonacciRecur(10));
        System.out.println(fibonacciIter(100));
    }

    public int fibonacciRecur(int n) {
        if (n == 0 || n == 1) return n;
        return fibonacciRecur(n - 1) + fibonacciRecur(n - 2);
    }

    public long fibonacciIter(int n) {
        long[] res = new long[n + 1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }

    public void removeLinkedListDuplicates() {
        // use hash table
    }

    public void minStack() {
        Stack<Integer> stack = new Stack();
        Stack<Integer> min = new Stack();

    }
}
