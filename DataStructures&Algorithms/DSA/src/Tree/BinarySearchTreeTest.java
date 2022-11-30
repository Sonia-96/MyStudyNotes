package Tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BinarySearchTreeTest {
    BinarySearchTree<Integer> tree;
    int[] nums;
    ArrayList<Integer> nums1, nums2, nums3;
    static int size;
    static Random rand;

    @BeforeAll
    protected static void setUpBeforeClass() {
        size = 10000;
        rand = new Random();
    }

    @BeforeEach
    protected void setUp() {
        tree = new BinarySearchTree<Integer>();
        nums = new int[] {13, 3, 7, 28, 12, 16, 14, 19, 4, 15};
        for (int num : nums) {
            tree.add(num);
        }
        nums1 = new ArrayList<>(List.of(-1, 50, 20)); // all new elements
        nums2 = new ArrayList<>(List.of(3, 4, 5)); // partial new elements
        nums3 = new ArrayList<>(List.of(7, 28, 19)); // no new elements
    }

    @Test
    public void testAdd() {
        int[] nums = {13, 3, 7, 28, 12, 16, 14, 19, 4, 15};
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        int count = 0;
        for (int num : nums) {
            assertTrue(tree.add(num));
            assertEquals(++count, tree.size());
        }
        assertFalse(tree.add(15));
        assertEquals("3 4 7 12 13 14 15 16 19 28 ", tree.toString());
    }

    @Test
    public void testAddIterative() {
        int[] nums = {13, 3, 7, 28, 12, 16, 14, 19, 4, 15};
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        int count = 0;
        for (int num : nums) {
            assertTrue(tree.addIter(num));
            assertEquals(++count, tree.size());
        }
        assertFalse(tree.add(15));
        assertEquals("3 4 7 12 13 14 15 16 19 28 ", tree.toString());
    }

    @Test
    public void testContains() {
        for (int num : nums) {
            assertTrue(tree.contains(num));
        }
        assertFalse(tree.contains(-1));
    }

    @Test
    public void testContainsIter() {
        for (int num : nums) {
            assertTrue(tree.containsIter(num));
        }
        assertFalse(tree.contains(-1));
    }

    @Test
    public void testRemove() {
        assertTrue(tree.remove(15));
        assertEquals("3 4 7 12 13 14 16 19 28 ", tree.toString());
        assertEquals(9, tree.size());
        assertTrue(tree.remove(28));
        assertEquals("3 4 7 12 13 14 16 19 ", tree.toString());
        assertEquals(8, tree.size());
        assertTrue(tree.remove(14));
        assertEquals("3 4 7 12 13 16 19 ", tree.toString());
        assertEquals(7, tree.size());
        assertTrue(tree.remove(3));
        assertEquals("4 7 12 13 16 19 ", tree.toString());
        assertEquals(6, tree.size());
        assertFalse(tree.remove(-1));
        assertEquals(6, tree.size());
        // test empty set
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>();
        assertFalse(tree2.remove(1));
    }

    @Test
    public void testRemoveIter() {
        assertTrue(tree.removeIter(15));
        assertEquals("3 4 7 12 13 14 16 19 28 ", tree.toString());
        assertEquals(9, tree.size());
        assertTrue(tree.removeIter(28));
        assertEquals("3 4 7 12 13 14 16 19 ", tree.toString());
        assertEquals(8, tree.size());
        assertTrue(tree.removeIter(14));
        assertEquals("3 4 7 12 13 16 19 ", tree.toString());
        assertEquals(7, tree.size());
        assertTrue(tree.removeIter(3));
        assertEquals("4 7 12 13 16 19 ", tree.toString());
        assertEquals(6, tree.size());
        assertFalse(tree.removeIter(-1));
        assertEquals(6, tree.size());
    }

    @Test
    public void testInOrderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for (int i = 0; i < size; i++) {
            tree.add(rand.nextInt());
        }
        assertEquals(tree.inOrderTraversal(), tree.inOrderTraversalIter());
    }

    @Test
    public void testPreOrderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for (int i = 0; i < size; i++) {
            tree.add(rand.nextInt());
        }
        assertEquals(tree.preOrderTraversal(), tree.preOrderTraversalIter());
    }

    @Test
    public void testPostOrderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for (int i = 0; i < size; i++) {
            tree.add(rand.nextInt());
        }
        assertEquals(tree.postOrderTraversal(), tree.postOrderTraversalIter());
    }

    @Test
    public void stressTestRecur() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        SortedSet<Integer> expected = new TreeSet<>();
        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(rand.nextInt());
        }
        Function<Object, Integer> integerGenerator = o -> rand.nextInt();
        testAdd(items, tree, expected, false);
        testContains(tree, expected, integerGenerator, false);
        testRemove(tree, expected, integerGenerator, false);
    }

    @Test
    public void stressTestIter() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        SortedSet<Integer> expected = new TreeSet<>();
        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(rand.nextInt(10));
        }
        Function<Object, Integer> integerGenerator = o -> rand.nextInt();
        testAdd(items, tree, expected, true);
        testContains(tree, expected, integerGenerator, true);
        testRemove(tree, expected, integerGenerator, true);
    }

    private <T extends Comparable<? super T>> void testAdd(ArrayList<T> items, BinarySearchTree<T> tree, SortedSet<T> expected, boolean useIter) {
        for (T item : items) {
            if (expected.contains(item)) {
                if (useIter) {
                    assertFalse(tree.addIter(item));
                } else {
                    assertFalse(tree.add(item));
                }
            } else {
                if (useIter) {
                    assertTrue(tree.addIter(item));
                } else {
                    assertTrue(tree.add(item));
                }
                expected.add(item);
            }
            assertEquals(expected.size(), tree.size());
            assertArrayEquals(expected.toArray(), tree.toArrayList().toArray());
        }
        if (useIter) {
            assertThrows(NullPointerException.class, () -> {tree.addIter(null);});
        } else {
            assertThrows(NullPointerException.class, () -> {tree.add(null);});
        }
    }

    private <T extends Comparable<? super T>> void testContains(BinarySearchTree<T> tree, SortedSet<T> expected, Function<Object, T> generator, boolean useIter) {
        for (T item : expected) {
            if (useIter) {
                assertTrue(tree.containsIter(item));
            } else {
                assertTrue(tree.contains(item));
            }
        }
        for (int i = 0; i < 10000000; i++) {
            T item = generator.apply(null);
            if (tree.contains(item)) System.out.println(true);
            if (useIter) {
                assertEquals(expected.contains(item), tree.containsIter(item));
            } else {
                assertEquals(expected.contains(item), tree.contains(item));
            }
        }
        if (useIter) {
            assertThrows(NullPointerException.class, () -> {tree.containsIter(null);});
            assertFalse(tree.containsIter(generator.apply(null)));
        } else {
            assertThrows(NullPointerException.class, () -> {tree.contains(null);});
            assertFalse(tree.contains(generator.apply(null)));
        }
    }

    private <T extends Comparable<? super T>> void testRemove(BinarySearchTree<T> tree, SortedSet<T> expected, Function<Object, T> generator, boolean useIter) {
        List<T> items = tree.toArrayList();
        Collections.shuffle(items, rand);
        for (T item : items) {
            if (useIter) {
                assertTrue(tree.removeIter(item));
            } else {
                assertTrue(tree.remove(item));
            }
            expected.remove(item);
            assertEquals(expected.size(), tree.size());
            assertArrayEquals(expected.toArray(), tree.toArrayList().toArray());
        }
        if (useIter) {
            assertThrows(NullPointerException.class, () -> {tree.removeIter(null);});
            assertFalse(tree.removeIter(generator.apply(null)));
        } else {
            assertThrows(NullPointerException.class, () -> {tree.remove(null);});
            assertFalse(tree.remove(generator.apply(null)));
        }
    }
}
