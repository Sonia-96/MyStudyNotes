package Tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTestGenerics {
    static int size;
    static Random rand;

    @BeforeAll
    protected static void setUpBeforeClass() {
        size = 10000;
        rand = new Random();
    }

    @Test
    public void testInteger() {
        BinarySearchTree_parent<Integer> tree = new BinarySearchTree_parent<Integer>();
        SortedSet<Integer> expected = new TreeSet<>();
        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(rand.nextInt());
        }
        Function<Object, Integer> integerGenerator = o -> rand.nextInt();
        testAdd(items, tree, expected);
//        testContains(tree, expected, integerGenerator);
        testRemove(tree, expected, integerGenerator);
    }

    @Test
    public void testString() {
        BinarySearchTree_parent<String> tree = new BinarySearchTree_parent<String>();
        SortedSet<String> expected = new TreeSet<>();
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(generateString());
        }
        Function<Object, String> stringGenerator = o -> generateString();
        testAdd(items, tree, expected);
        testContains(tree, expected, stringGenerator);
        testRemove(tree, expected, stringGenerator);
    }

    @Test
    public void testBook() {
        BinarySearchTree_parent<Book> tree = new BinarySearchTree_parent<Book>();
        SortedSet<Book> expected = new TreeSet<>();
        ArrayList<Book> items = new ArrayList<>();
        Function<Object, Book> bookGenerator = o -> new Book(rand.nextLong(), generateString(), generateString());
        for (int i = 0; i < size; i++) {
            items.add(bookGenerator.apply(null));
        }
        testAdd(items, tree, expected);
        testContains(tree, expected, bookGenerator);
        testRemove(tree, expected, bookGenerator);
    }

    private <T extends Comparable<? super T>> void testAdd(ArrayList<T> items, BinarySearchTree_parent<T> tree, SortedSet<T> expected) {
        for (T item : items) {
            if (expected.contains(item)) {
                assertFalse(tree.add(item));
            } else {
                assertTrue(tree.add(item));
                expected.add(item);
            }
            assertEquals(expected.size(), tree.size());
            assertArrayEquals(expected.toArray(), tree.toArrayList().toArray());
        }
        assertThrows(NullPointerException.class, () -> {tree.add(null);});
    }

    private <T extends Comparable<? super T>> void testContains(BinarySearchTree_parent<T> tree, SortedSet<T> expected, Function<Object, T> generator) {
        for (T item : expected) {
            assertTrue(tree.contains(item));
        }
        for (int i = 0; i < 10000000; i++) {
            T item = generator.apply(null);
            if (tree.contains(item)) {
                System.out.println(true);
            }
            assertEquals(expected.contains(item), tree.contains(item));
        }
        assertThrows(NullPointerException.class, () -> {tree.contains(null);});
        assertFalse(tree.contains(generator.apply(null)));
    }

    private <T extends Comparable<? super T>> void testRemove(BinarySearchTree_parent<T> tree, SortedSet<T> expected, Function<Object, T> generator) {
        List<T> items = tree.toArrayList();
        Collections.shuffle(items, rand);
        for (T item : items) {
            assertTrue(tree.remove(item));
            expected.remove(item);
            assertEquals(expected.size(), tree.size());
            assertArrayEquals(expected.toArray(), tree.toArrayList().toArray());
        }
        assertThrows(NullPointerException.class, () -> {tree.remove(null);});
        assertFalse(tree.remove(generator.apply(null)));
    }

    private String generateString() {
        int stringSize = rand.nextInt(20);
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < stringSize; j++) {
            sb.append((char) rand.nextInt(128));
        }
        return sb.toString();
    }
}
