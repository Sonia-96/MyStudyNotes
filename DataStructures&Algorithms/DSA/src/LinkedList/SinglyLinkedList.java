package LinkedList;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedList<T> {

    /**
     * usually an inner class needs a pointer to the outer class,
     * but a static inner class doesn't need that
     */
    protected static class Node<T> {
        T data;
        Node<T> next;
        Node(T x, Node<T> n) {
            data = x;
            next = n;
        }
    }

    Node<T> dummy; // head is dummy.next

    public SinglyLinkedList () {
        dummy = new Node<>(null, null);
    }

    /**
     * add a node before head or after dummy
     * @param x the element to be prepended
     */
    public void prepend(T x) {
        dummy.next = new Node<>(x, dummy.next);
    }

    /**
     * delete first occurrence of x in the list
     * @param x the value for the element to be removed
     */
    public void removeFirst(T x) {
        Node<T> n = dummy;
        while (!n.next.data.equals(x)) {
            n = n.next;
        }
        n.next = n.next.next;
    }

    public void removeFirst() {
        if (dummy.next == null) {
            throw new NoSuchElementException();
        }
        dummy.next = dummy.next.next;
    }

    public T getFirst() {
        if (dummy.next == null) {
            throw new NoSuchElementException();
        }
        return dummy.next.data;
    }

    public void print() {
        Node<T> n = dummy.next;
        while (n != null) {
            System.out.print(n.data);
            System.out.print(" ");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        list.prepend(1);
        list.prepend(3);
        list.prepend(2);
        list.removeFirst(3);
        list.print();

        Random rand = new Random();
        int size = 1000;
        LinkedList<Integer> expected = new LinkedList<>();
        SinglyLinkedList<Integer> lst = new SinglyLinkedList<Integer>();
        for (int i = 0; i < size; i++) {
            int num = rand.nextInt();
            lst.prepend(num);
            expected.addFirst(num);
        }

        for (int i = 0; i < size; i++) {
            assertEquals(expected.getFirst(), lst.getFirst());
            expected.removeFirst();
            lst.removeFirst();
        }

        assertThrows(NoSuchElementException.class, lst::removeFirst);

    }
}
