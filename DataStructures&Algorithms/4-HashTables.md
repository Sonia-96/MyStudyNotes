# Hash Tables

1. Terminologies
   - hash function: a method that generates an integer index given any object, and this integer will be the index of the object in the hash table
   - hash code: the integer that an element get through the hash function
   - hash table: the data structure to store elements based on their hash codes
1. Operations and ideal time complexity of hash tables
   - `insert(T E)`: O(1)
   - `search(T E)`: O(1)
   - `delete(T E)`: O(1)

## Hash Function

1. Hash function

   (In Java we just use `<Object>.hashCode()` as their hash code. The default of this method is to return the memory address of the object. )

2. Good hash function

   - same hash code for equal objects
   - has code should be evenly distributed

3. load factor (λ) 

   - λ = n / m

     - n: the number of elements
     - m: the length of the hash table

   - average number of cells examined on an `insert` operation is `1/(1-λ)`

   - Usually, we should ensure λ < 0.5. If λ >= 0.5, we should increase the size of the hash table (the new size should be the next prime number after `size * 2`) and rehash all elements.

     ```java
     int newSize = nextPrime(size * 2);
     ```

     Say, if size = 10, newSize = nextPrime(20) = 23.

## Collision

collision: non-equal objects map to the same index

### Open Addressing

#### Linear Probing

if the spot is already taken, step forward one index at a time until an empty space is found. 

cause **primary clustering**

#### Quadradic Probing

If `hash(item) = H` and the cell H is occupied, try H+1<sup>2</sup>, H+2<sup>2</sup>, H+3<sup>2</sup>, etc, until find an empty spot. 

cause **second clustering**

### Seperate Chaining

Use an array of linked lists (`LinkedList<T>[]`) to store elements. The elements with the same hash code will be stored in the same linked list.

<img src="./assets/chaining.png" alt="seperate chaining" style="zoom:40%;" />

Time complexity:

- Insert: O(1)
- Search: O(n/m) - O(1) if average list length is small  constant
- Delete: O(n/m) - O(1) if average list length is small  constant

To make sure the list elength is small,  `n` should be close to `m`. To do this, we usaually set the threshold of the load factor as 1.5.

## Hash Tables in Java

- `HashMap`
- `HashSet`

### HashMap vs. TreeMap

- hashmap - hash tables: unordered 
- treemap - bianry search tree: ordered