# Iterator & Iterable

## Iterator Interface

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

