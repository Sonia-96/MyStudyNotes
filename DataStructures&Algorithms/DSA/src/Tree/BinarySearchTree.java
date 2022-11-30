package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private class Node {
        T value;
        Node left, right;
        int size;

        Node(T v) {
            value = v;
            size = 1;
        }
    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean add(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        int oldSize = size();
        root = add(root, item);
        return size() > oldSize;
    }

    private Node add(Node node, T item) {
        if (node == null) {
            return new Node(item);
        }
        int cmp = node.value.compareTo(item);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            node.right = add(node.right, item);
        } else {
            node.left = add(node.left, item);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public boolean addIter(T item) {
        Node prev = null, curr = root;
        int cmp = 0;
        while (curr != null) {
            cmp = curr.value.compareTo(item);
            if (cmp == 0) {
                return false;
            }
            prev = curr;
            if (cmp < 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        if (prev == null) {
            root = new Node(item);
        } else {
            if (cmp < 0) {
                prev.right = new Node(item);
            } else {
                prev.left = new Node(item);
            }
        }
        updateSize(root); // TODO only update the size of the node's ancestors
        return true;
    }

    public boolean contains(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        return contains(root, item);
    }

    private boolean contains(Node node, T item) {
        if (node == null) return false;
        int cmp = node.value.compareTo(item);
        if (cmp == 0) {
            return true;
        }
        if (cmp < 0) {
            return contains(node.right, item);
        }
        return contains(node.left, item);
    }

    public boolean containsIter(T item) {
        Node curr = root;
        while (curr != null) {
            int cmp = curr.value.compareTo(item);
            if (cmp == 0) {
                return true;
            }
            if (cmp < 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return false;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item
     *          - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    boolean remove(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        int oldSize = size();
        root = remove(root, item);
        return oldSize > size();
    }

    /**
     * Remove an item from a specified node
     * @param node the node that the item to be removed from
     * @param item the item whose absence is ensured under this node
     * @return null if this node doesn't contain this item; otherwise, return the node
     */
    private Node remove(Node node, T item) {
        if (node == null) {
            return null;
        }
        int cmp = node.value.compareTo(item);
        if (cmp < 0) {
            node.right = remove(node.right, item);
        } else if (cmp > 0) {
            node.left = remove(node.left, item);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            node.value = getMin(node.right).value;
            node.right = remove(node.right, node.value);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     * Ensures that this set does not contain the specified item. (iteration)
     *
     * @param item
     *          - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    boolean removeIter(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node prev = null, curr = root;
        while (curr != null && curr.value.compareTo(item) != 0) {
            prev = curr;
            int cmp = item.compareTo(curr.value);
            if (cmp < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (curr == null) { // no such item
            return false;
        }
        if (curr.right != null && curr.left != null) {
            // find the smallest node in the right subtree (in-order successor)
            Node successor = curr.right, p = null;
            while (successor.left != null) {
                p = successor;
                successor = successor.left;
            }
            if (p == null) { // the smallest node is the root of the right subtree
                curr.right = successor.right;
            } else {
                p.left = successor.right;
            }
            curr.value = successor.value;
        } else {
            Node newCurr = null;
            if (curr.left != null) {
                newCurr = curr.left;
            } else if (curr.right != null) {
                newCurr = curr.right;
            }
            if (prev == null) { // the node to be deleted is the root
                root = newCurr;
            } else {
                if (prev.right == curr) {
                    prev.right = newCurr;
                } else {
                    prev.left = newCurr;
                }
            }
        }
        updateSize(root); // TODO only update the size of the node's ancestors
        return true;
    }

    Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Removes the smallest item under a specified node and return this node.
     * @param node the specified node
     * @return this node
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node removeMinIterative(Node node) {
        Node prev = null;
        Node curr = node;
        while (curr.left != null) {
            prev = curr;
            curr = curr.left;
        }
        if (prev != null) {
            prev.left = curr.right;
        } else { // the smallest node is the root
            node = node.right;
        }
        updateSize(node);
        return node;
    }

    public List<T> inOrderTraversal() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    public void inOrderTraversal(Node node, List<T> result) {
        if (node == null) return;
        inOrderTraversal(node.left, result);
        result.add(node.value);
        inOrderTraversal(node.right, result);
    }

    /**
     * Use iterative in-order traversal to traverse the node
     * @return the values in in-order
     */
    public ArrayList<T> inOrderTraversalIter() {
        ArrayList<T> result = new ArrayList<>();
        Node p = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.add(p);
                p = p.left;
            }
            p = stack.pop();
            result.add(p.value);
            p = p.right;
        }
        return result;
    }

    public List<T> preOrderTraversal() {
        List<T> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    private void preOrderTraversal(Node node, List<T> result) {
        if (node == null) return;
        result.add(node.value);
        preOrderTraversal(node.left, result);
        preOrderTraversal(node.right, result);
    }

    public List<T> preOrderTraversalIter() {
        List<T> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                result.add(p.value);
                stack.add(p);
                p = p.left;
            }
            p = stack.pop().right;
        }
        return result;
    }

    public List<T> postOrderTraversal() {
        List<T> result = new ArrayList<>();
        postOrderTraversal(root, result);
        return result;
    }

    private void postOrderTraversal(Node node, List<T> result) {
        if (node == null) return;
        postOrderTraversal(node.left, result);
        postOrderTraversal(node.right, result);
        result.add(node.value);
    }

    public List<T> postOrderTraversalIter() {
        List<T> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node curr = root, prev = null;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (curr.right == null || prev == curr.right) {
                result.add(curr.value);
                prev = curr;
                curr = null;
            } else {
                stack.add(curr);
                curr = curr.right;
            }
        }
        return result;
    }

    public int size() {
        return size(root);
    }

    public int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public int updateSize(Node node) {
        if (node == null) return 0;
        node.size = updateSize(node.left) + updateSize(node.right) + 1;
        return node.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<T> arr = toArrayList();
        for(T item : arr) {
            sb.append(item).append(" ");
        }
        return sb.toString();
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> res = new ArrayList<>(size());
        inOrderTraversal(root, res);
        return res;
    }
}
