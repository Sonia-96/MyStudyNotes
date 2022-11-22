package Tree;

public class BinarySearchTree {
    private class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }

    Node root;

    Node search(int val) {
        return search(root, val);
    }

    Node search(Node node, int val) {
        if (node.value == val) {
            return node;
        }
        if (node.value < val) {
            if (node.right == null) {
                return node;
            }
            return search(node.right, val);
        }
        if (node.left == null) {
            return node;
        }
        return search(node.left, val);
    }

    boolean contains(int target) {
        Node node = search(target);
        return node.value == target;
    }

    boolean add(int val) {
        Node node = search(val);
        if (node.value == val) {
            return false;
        }
        if (node.value < val) {
            node.right = new Node(val);
        } else {
            node.left = new Node(val);
        }
        return true;
    }

    /**
     * @param target
     * @return
     */
    boolean remove(int target) {
        // one child: replace the node
        Node node = search(target);
        if (node.value != target) {
            return false;
        }
        if (node.right != null) {
            Node temp = node;
            node = getMin(node.right); // find the smallest node in the right subtree
            node.right = removeMin(temp.right); // remove the smallest node from the right subtree and return its root
            node.left = temp.left;
        } else {
            node = node.left;
        }
        return true;
    }

    Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // delete the smallest element and return the root of the tree
    Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }
}
