public class LinkedList<T> {
    private Node head;

    private class Node {
        T item;
        Node next;

        public Node(T i, Node n) {
            item = i;
            next = n;
        }
    }

    public void print() {
//        Node curr = head;
//        while (curr != null) {
//            System.out.print(curr.item);
//            System.out.print(" ");
//        }
        for (Node n = head; n != null; n = n.next) {
            System.out.print(n.item);
            System.out.print(" ");
        }
    }

    public void prepend(T x) {
        Node node = new Node(x, head);
        head = node;
    }

    public void splice(Node n, T x) {
        n.next = new Node(x, n.next);
    }

    public void remove(Node n) {
        Node prev = head;
        while (prev.next != n) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
    }

//    public int length(Node n) {
//        int count = 0;
//        for (Node curr = n; curr != null; curr = curr.next) {
//            count++;
//        }
//        return count;
//    }

    public int length(Node n) {
        // ? is ternary operator
        return n == null ? 0 : 1 + length(n.next);
    }
}
