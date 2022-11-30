package Tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class BinarySearchTreeUnitTest {
    BinarySearchTree_parent<Integer> tree;
    int[] nums;
    ArrayList<Integer> nums1, nums2, nums3;

    @BeforeEach
    protected void setUp() {
        tree = new BinarySearchTree_parent<Integer>();
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
        BinarySearchTree_parent<Integer> tree = new BinarySearchTree_parent<Integer>();
        int count = 0;
        for (int num : nums) {
            assertTrue(tree.add(num));
            assertEquals(++count, tree.size());
        }
        assertFalse(tree.add(15));
        assertEquals("3 4 7 12 13 14 15 16 19 28", tree.toString());
    }

    @Test
    public void testAddAll() {
        assertTrue(tree.addAll(nums1));
        assertTrue(tree.addAll(nums2));
        assertFalse(tree.addAll(nums2));
    }

    @Test
    public void testClear() {
        tree.clear();
        assertEquals(0, tree.size());
    }

    @Test
    public void testContains() {
        for (int num : nums) {
            assertTrue(tree.contains(num));
        }
        assertFalse(tree.contains(-1));
    }

    @Test
    public void testContainsAll() {
        assertFalse(tree.containsAll(nums1));
        assertFalse(tree.containsAll(nums2));
        assertTrue(tree.containsAll(nums3));
    }

    @Test
    public void testFirst() {
        assertEquals(3, tree.first());
        // test empty tree
        BinarySearchTree_parent<Integer> emptyTree = new BinarySearchTree_parent<Integer>();
        assertThrows(NoSuchElementException.class, emptyTree::first);
    }

    @Test
    public void testLast() {
        assertEquals(28, tree.last());
        // test empty tree
        BinarySearchTree_parent<Integer> emptyTree = new BinarySearchTree_parent<Integer>();
        assertThrows(NoSuchElementException.class, emptyTree::last);
    }

    @Test
    public void testRemove() {
        assertTrue(tree.remove(15));
        assertEquals("3 4 7 12 13 14 16 19 28", tree.toString());
        assertEquals(9, tree.size());
        assertTrue(tree.remove(28));
        assertEquals("3 4 7 12 13 14 16 19", tree.toString());
        assertEquals(8, tree.size());
        assertTrue(tree.remove(14));
        assertEquals("3 4 7 12 13 16 19", tree.toString());
        assertEquals(7, tree.size());
        assertTrue(tree.remove(3));
        assertEquals("4 7 12 13 16 19", tree.toString());
        assertEquals(6, tree.size());
        assertFalse(tree.remove(-1));
        assertEquals(6, tree.size());
        // test empty set
        BinarySearchTree_parent<Integer> tree2 = new BinarySearchTree_parent<Integer>();
        assertFalse(tree2.remove(1));
    }

    @Test
    public void testRemoveAll() {
        assertFalse(tree.removeAll(nums1));
        assertTrue(tree.removeAll(nums2));
        assertTrue(tree.removeAll(nums3));
    }
}
