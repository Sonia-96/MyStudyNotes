# Collection

`Collection` is an interface in Java that supports various operations:

- add, remove, contains, ...

Hierarchy of Collection Framework:

<img src="./assets/java-collection-hierarchy.png" alt="Hierarchy of Java Collection framework" style="zoom:80%;" />

Ended data structures: the data structures that can only be accessed from its ends

- Stack: one ended
- Queue: two ended
- Deque: two ended

Sequence Containers:

- array
- linked list

# List

## ArrayList

Time complexity for `ArrayList` on different methods:

- `get(i)`: O(1)
  - to get the i-th element, we compute the address of the element: `first_element + (i - 1) * size_of_each_element`. There's a special hardware to do this, so it's really fast
- `add(i)`: O(N)
- `remove(i)`: O(N)

## LinkedList

Time complexity for `LinkedList` on different methods:

- `T get(int i)`: O(N)
- `voi add(Node n)`: O(1)
- `void remove(Node n)`: O(1)
  - for `add()` and `remove()`, the elements will never move; they will always stay in the same address.

### Singly Linked List (Forward List)

```java
public class SinglyLinkedList<T> {

    /**
     * usually an inner class needs a pointer to the outer class,
     * but a static inner class doesn't need that
     */
    private static class Node<T> {
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

    public void print() {
        Node<T> n = dummy.next;
        while (n != null) {
            System.out.print(n.data);
            System.out.print(" ");
            n = n.next;
        }
        System.out.println();
    }
}
```

### Doubly Linked List

Doubly Linked List use two pointers to maintain the order -- `prev` and `next`. In the following implementation, I use a dummy head to better handle the cases when the list is empty. In addition,  `dummy.next` is the head, `dummy.prev` is the tail.

```java
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

    public void prepend(T x) {
        Node<T> node = new Node<>(x, dummy, dummy.next);
        dummy.next = node;
        node.next.prev = node;
    }

  	// insert a new node after the Node n
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
}
```

### ArrayList vs. LinkedList

| Operations                 | ArrayList | LinkedList |
| -------------------------- | --------- | ---------- |
| random access              | O(1)      | O(N)       |
| Add an element to the head | O(N)      | O(1)       |
| Add an element to the end  | O(1)      | O(1)       |
| Remove the first element   | O(N)      | O(1)       |
| Remove the last element    | O(1)      | O(1)       |

**CPU caches** are designed to read data from the memory **in order**. To be specific, following a pointer can take 100s of CPU cycles. Therefore, although `arr.get(i)` and `node.next` both has the time complexity O(1), the former one is 100 times faster than the latter! So, if we don't need to add or remove an element to or from an array, it is always faster to use array-based data structures.

# Stack & Queue

ADT - Abstract Data Type. An ADT is defined only by its operations, not implementations.

## Stack

Stack: Last in first out (LIFO)

### Operations

All the following operations should have O(1) time complexity:

- `void push(T x)`: add an element to the top
- `T pop()`: remove the element at the top
- `T peek()`: return the element at the top

### Possible implementations

1. `Stack` class which extends `Vector`

   ```java
   Stack<Integer> stack = new Stack<>();
   stack.push(1);
   stack.push(2);
   stack.peek(); // return the top element 2
   stack.pop(); // remove the top element 2
   ```

2. `ArrayList`: store the top element at the end of the array

   - `ArrayList<T> stack = new ArrayList<>()`;

   - `void push(T x)`: `stack.add(x)`

   - `T pop()`: 

     ```java
     T res = stack.get(stack.size() - 1);
     stack.remove(stack.size() - 1);
     return res;
     ```

   - ` T peek()`: `return stack.get(stack.size() - 1)`

3. `LinkedList`: store the top element as the head

   ```java
   public class StackSLL<T> {
       private class Node {
           T item;
           Node next;
   
           public Node(T x, Node n) {
               item = x;
               next = n;
           }
       }
   
       Node head;
   
       void push(T x) {
           head = new Node(x, head);
       }
   
       public T pop() {
           if (head == null) {
               throw new NoSuchElementException("The stack is empty!");
           }
           T x = head.item;
           head = head.next;
           return x;
       }
   
       public T peek() {
           if (head == null) {
               throw new NoSuchElementException("The stack is empty!");
           }
           return head.item;
       }
   }
   ```

### Applications

#### Parenthesis Validation

[LeetCode 20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

```java
		public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ']' && top != '[' )|| (c == '}' && top != '{') || (c == ')' && top != '(')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
```

#### Reverse Polish Notation 

- infix expressions: an operator is written between operands. This form is what we generally use in daily life. e.g. 1 + 2 * 3 = 7
- prefix expression (polish notation): an operator is written before its operands
- post expression (reverse polish notation): an operator is written after its operands. e.g., 123*+ = 1 + 2 * 3 = 7

[LeetCode 150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)

```java
		public int evalRPN(String[] tokens) {
        Set<String> operators = new HashSet<>(List.of("+", "-", "*", "/"));

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (!operators.contains(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                int right = stack.pop(), left = stack.pop();
                int res = switch(s) {
                    case "+" -> left + right;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    case "/" -> left / right;
                    default -> throw new UnsupportedOperationException();
                };
                stack.push(res);
            }
        }
        return stack.pop();
    }
```

## Queue

Queue: First in first out (FIFO)

### Operations

All the following operations should have O(1) time complexity:

- `void enqueue(T x)`: add an element to to the end of the queue
- `T dequeue()`: remove and return the element at the head of the queue
- `T peek()`: return the head of the queue

### Possible implementations

1. Circular array

     - Enqueue: `back = (back + 1) % capacity`

     - Dequeue: `front = (front + 1) % capacity`

     - Print:
     
       ```java
       for (int i = 0; i < size; i++) {
         System.out.println(nums[(front + i) % capacity]);
       }
       ```


2. LinkedList

     - enqueue: `addLast(T x)`

     - Dequeue: `removeFirst()`
     - peek: `getFirst()`


# Set & Map

## Set

Set - no duplicate elements

- `bool add(E element)`: 
- `bool remove(E element)`
- `bool contains(E element)`: 
- `iterator()`: usually iterable

Set implementations:

- sorted array
- unsorted array
  - remove: search the element - O(N); put the last element to the position where the element should be removed - O(1)
- binary search tree

|                    | add  | remove | Contains |
| ------------------ | ---- | ------ | -------- |
| sorted array       | O(N) | O(N)   | O(NlogN) |
| unsorted array     | O(1) | O(N)   | O(N)     |
| binary search tree |      |        |          |
|                    |      |        |          |

## Map

- `V get(K key)`
- `void set(K key, V value)`
- `void remove(K key)`

Note: 

- `K` must be `Comparable<K>` or we provide a `comparator<K>`
