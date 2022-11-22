package LinkedList;

import org.junit.jupiter.api.Test;

public class DoublyLinkedList<T> {
    private Node<T> dummy;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(T i, Node<T> p, Node<T> n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    DoublyLinkedList() {
        dummy = new Node<>(null, null, null);
        dummy.next = dummy;
        dummy.prev = dummy;
    }

    public void printForwards() {
        for (Node n = dummy.next; n != dummy; n = n.next) {
            System.out.print(n.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public void printBackwards() {
        for (Node<T> n = dummy.prev; n != dummy; n = n.prev) {
            System.out.print(n.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public void prepend(T x) {
        Node<T> node = new Node<>(x, dummy, dummy.next);
        dummy.next = node;
        node.next.prev = node;
    }

    public void prepend(Node<T> n) {
        n.prev = dummy;
        n.next = dummy.next;
        dummy.next = n;
        if (n.next != null) {
            n.next.prev = n;
        }
    }

    public void splice(Node<T> n, T x) {
        Node<T> node = new Node<>(x, n, n.next);
        n.next = node;
        node.next.prev = node;
    }

    public void remove(Node<T> n) {
       n.prev.next = n.next;
       n.next.prev = n.prev;
    }

    public int length(Node<T> n) {
        int count = 0;
        for (Node<T> curr = n; curr != null; curr = curr.next) {
            count++;
        }
        return count;
    }

    public int lengthRecursion(Node<T> n) {
        // ? is ternary operator
        return n == null ? 0 : 1 + lengthRecursion(n.next);
    }

    @Test
    public void test() {
        DoublyLinkedList<Integer> DLL = new DoublyLinkedList<>();
        Node<Integer>[] nodes = new Node[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new Node<>(i, null, null);
            DLL.prepend(nodes[i]);
        }
        DLL.remove(nodes[5]);
        DLL.remove(nodes[3]);
        DLL.splice(nodes[0], -1);
        DLL.printBackwards();
        DLL.printForwards();
    }
}
