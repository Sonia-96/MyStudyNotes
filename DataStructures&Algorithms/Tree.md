# Tree

Before all we learn are **sequence containers**, which defines "comes before + comes after" relationship between elements. This is too limiting for some types of data. Today we'll learn **tree** -- a data structure representing **hierachical data**.

Trees have no loops (if there are loops, it's a graph), which means that one child can have only one parent. 

- ordered tree: there is a certain order between siblings, e.g., DOM
- unordered tree: e.g., company organization

## Terminology

- Depth: the number of ancestors of a node. The depth of root is 0.
- Height: The number of nodes from the node to its descendant leaf node. The height of all leaf nodes is 1.
  - the height of a tree: the height of its root node
- Level: level = depth + 1

## Implementation

### Linked List

```java
public class Tree<T> {
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
```

## Binary Tree

Binary tree is a type of tree that has at most 2 children.

- Full binary tree: every node except for leaves has 2 children

- Complete binary tree: every level is full except for the lowest level, and the lowest level should be filled in from the left 

### Implementation

#### Linked List

```java
public class BinaryTree<T> {
    private class Node {
        T item;
        Node left;
        Node right;
    }

    Node root;
}
```

### ArrayList

Use an arraylist to store all the nodes from left to right and from top to bottom. This is only suitable for cmoplete binary tree.

If the array is 0-based, then for Node `i`:

- Parent node: `i`
- left child: `2 * i + `
- right child: `2 * i + 2`

### Tree Traversal

#### Pre-order

root -> left -> right

1. recursion

   ```java
   private List<T> preTraverse(Node root) {
     List<T> result = new ArrayList<>();
     pretraverse(root, result);
     return result;
   }
   
   private void preTraverse(Node root, List<T> result) {
     if (root == null) return;
     List<T> result = new ArrayList<>();
     result.add(root.item);
     preTraverse(root.left, result);
     preTraverse(root.right, result);
   }
   ```

2. Iteration

   // TODO

#### Post-order

left -> right -> root

1. recursion

   ```java
   public List<T> postTraverse(Node root) {
     List<T> result = new ArrayList<>();
     postTraverse(node, result);
     return result;
   }
   
   private void postTraverse(Node node, List<T> result) {
     if (node == null) return;
     postTraverse(node.left);
     postTraverse(node.right);
     result.add(node.item);
   }
   ```

2. iteration

   // TODO 

##### Applications

1. compute the depth of a tree:

```java
public static int depth(Node root) {
  if (root == null) {
    return 0;
  }
  return Math.max(depth(root.left), depth(root.right)) + 1;
}
```

2. expression tree: 

   - leaf nodes: numbers
   - Internal nodes: operators

   <img src="./assets/expression-tree.png" alt="expression tree" style="zoom:50%;" />

   ```java
   int eval(Node root) {
     if (root.left == null && root.right == null) {
       return 
     }
     int left = eval(root.left);
     int right = eval(root.right);
     return 
   }
   ```

#### In-order

left -> root -> right

```java
public List<T> inTraverse(Node root) {
  List<T> result = new ArrayList<>();
  inTraverse(node, result);
  return result;
}

private void inTraverse(Node node, List<T> result) {
  if (node == null) return;
  inTraverse(node.left);
  result.add(node.item);
  inTraverse(node.right);
}
```

applicatioin: in-order traversal is used in binary search tree to get an ascending order list.

## Binary-Search Tree (BST)

