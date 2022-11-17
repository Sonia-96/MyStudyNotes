# Collection

`Collection` is an interface in Java that supports various operations:

- add, remove, contains, ...

Hierarchy of Collection Framework:

<img src="./assets/java-collection-hierarchy.png" alt="Hierarchy of Java Collection framework" style="zoom:80%;" />

# Iteration

## Iterator Interface

1. `Iterator` & `Iterable`接口

   - `Iterator`: objects that can be iterated through in Java

     - 得到的迭代器不能使用for-each循环

     - `Iterator`
       
       中要求的方法：

       - `boolean hasNext()`
       - `T next()`
     
   - `Iterable`: objectes that can produce an iterator

     - `implements Iterable<> `得到的迭代器可以使用**for-each**循环

     - `Iterable`中要求的方法：
       - `iterator()`
     
     ```java
     public class ArraySet<T> implements Iterable<T> {
         ...
     
         @Override
      	public Iterator<T> iterator() {
             return new ArraySetIterator();
         }
     
         private class ArraySetIterator implements Iterator<T> {
             private int pos;
     
             public ArraySetIterator() {
                 pos = 0;
             }
     
             @Override
             public boolean hasNext() {
                 return pos < size;
             }
     
             @Override
             public T next() {
                 pos += 1;
                 return items[pos - 1];
             }
         }
     }
     ```
   
   
## List

### ArrayList

Time complexity for `ArrayList` on different methods:

- `get(i)`: O(1)
  - to get i-th element, we compute the address of the element: `first_element + (i - 1) * size_of_each_element`
- `add(i)`: O(N)
- `remove(i)`: O(N)

### LinkedList

Time complexity for `LinkedList` on different methods:

- `get(i)`: O(N)
- `add(i)`: O(1)
- `remove(i)`: O(1)
  - for `add()` and `remove()`, the elements will never move; they will always stay in the same address.



Situations that don't use a linked list!! -> Bad cache performance (**TODO**: review this part in video)

Because the memory of LinkedList is not consequtive, so it's more costly than an array to find the next node. ()