# Generics

To make a class to be compatible with different data types, we can use generics.

## Syntax

1. In declaration: `className<T>`

   ```java
   class MyContainer<T> {
     T data[];
     int size, capacity;
     
     public void add(T item) {...};
     public void remove(T item) {...};
   }
   ```

   Note, you can only use reference types for generics, e.g.,  `Integer`, `Double`, `Character`, `Boolean`, `Short`, `Long`, `Byte`, `Float`.

2. In instantiation: `MyContainer<T> container = new MyContainer<>()`

3. comman type parameters:

   - T - Type
   - E - Element
   - K - Key
   - N - Number
   - V - Value

## Auto boxing/unboxing

1. auto boxing: during compiling, the primitive types will be converted to corresponding reference types automatically. e.g., 

   ```java
   ArrayList<Integer> list = new ArrayList<>();
   list.add(5);
   ```

2. auto unboxing: reference types - > primitive types

   ```java
   int num = list.get(0);
   ```

## Wildcard

The **question mark (?)** is known as the **wildcard** in generic programming. It represents an unknown type. 

- upper bounded wildcard: `<? extends E>` -- `E` or anyclass extends the class `E`
- lower bounded wildcard: `<? super E>` -- `E` or anyclass that is a super-class of `E`

## static generic method

`static` methods can have their own generic types in their signatures.

```java
public static <T> boolean contains(T[] array, T item) {
  for (T t : array) {
    if (t.equals(item)) {
      return true;
    }
  }
  return false;
}
```

# Iterator & Iterable

## Iterator

`Iterator`: objects that can be iterated through

- 得到的迭代器不能使用for-each循环

- `Iterator`中要求的方法：
  - `boolean hasNext()`
  - `T next()`

## Iterable

`Iterable`: objectes that can produce an iterator

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

# Comparable & Comparator

## Interface Comparable

`Comparable<T>` is a built-in interface in Java. Many built-in classes in Java have implemented `Comparable<T>`, e.g., `String`, `Integer`. To implements Comparable Interface, the class must implements the method `int compareTo(T o)`.

```java
public class Dog implements Comparable<Dog>{
    private String name;
    private int size;

    @Override
    public int compareTo(Dog o) {
      return size < o.size ? -1 : (size == o.size ? 0 : 1);
    }
}
```

After implementing the `Comparable<T>` interface, the objects of this class can be sorted by methods like `Collections.sort()`, `Arrays.sort()`.

## Interface Comparator

`Comparator<T>` is also a built-in interface in Java. We need to import this interface before implementing it (`Comparable` doesn't need this).  To implement `Comparator`, we need to implement the method `public int compare(Object o1, Object o2)`.

```java
public class DogComparator implements Comparator<Dog> {
  	@Overrie
  	public int compare(Dog d1, Dog d2) {
      return d1.size < d2.size ? -1 : (d1.size == d2.size ? 0 : 1);
    }
}
```

When sorting Dog objects, we need to create a new DogComparator:

```java
Dog[] dogs = {dog1, dog2, ...};
Arrays.sort(dogs, new DogComparator());
```

We can use lamda function to create a comparator directly, instead of first defining a new class :

```java
Arrays.sort(dogs, (c1, c2) -> (c1.size - c2.size)); // might encounter integer overflow
```
