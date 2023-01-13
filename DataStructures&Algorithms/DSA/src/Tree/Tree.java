package Tree;

import java.util.ArrayList;

public class Tree<T extends Comparable<? super T>> {
    private static class Node<T> {
        T item;
        ArrayList<Node<T>> children;

        public Node(T x) {
            item = x;
            children = new ArrayList<>();
        }
    }

    Node<T> root;
}
