package Tree;

import java.util.ArrayList;

public class Tree<T extends Comparable<? super T>> {
    private class Node {
        T item;
        ArrayList<Node> children;

        public Node(T x) {
            item = x;
            children = new ArrayList<>();
        }
    }

    Node root;
}
