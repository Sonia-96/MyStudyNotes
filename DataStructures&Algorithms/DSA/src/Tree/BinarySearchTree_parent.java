package Tree;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree_parent<T extends Comparable<? super T>> implements SortedSet<T> {
    public class Node {
        T value;
        int size;
        Node left;
        Node right;
        Node parent;

        Node(T value){
            this.value = value;
            size = 1;
            parent = null;
        }

    }

    Node root;

    public BinarySearchTree_parent(){
        root = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<T> arr = toArrayList();
        for(T item : arr) {
            sb.append(item).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Search certain item in this set.
     *
     * @param n - the root node of the BST
     * @param target - the item to be searched
     * @return node containing value if it exists or node could be the value's parent.
     */

    public Node search(Node n, T target){
        if(n==null){
            return null;
        }

        if(n.value.compareTo(target)==0){
            return n;
        }
        else if((target.compareTo(n.value))<0){
            if(n.left == null){
                return n;
            }
            return search(n.left,target);
        }
        else{
            if(n.right == null){
                return n;
            }
            return search(n.right,target);
        }
    }


    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean add(T item) throws NullPointerException {
        if(item == null){
            throw new NullPointerException("The target item is null!");
        }

        if(contains(item)){ // contains = don't add the item, return false
            return false;
        }

        // create new child node and update size of ancestors
        Node new_parent = search(root,item);
        // if the set is empty
        if (new_parent == null) {
            root = new Node(item);
            return true;
        }

        Node new_child = new Node(item);
        new_child.parent = new_parent;

        if((new_parent.value.compareTo(item))>=0){
            new_parent.left = new_child;
        }
        else{
            new_parent.right = new_child;
        }
        Node size_node = new_child;
        // update the size of ancestors
        while(size_node.parent !=null) {
            size_node.parent.size++;
            size_node = size_node.parent;
        }
        return true;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) throws NullPointerException{
        for(T item: items){
            if(item==null){
                throw new NullPointerException("A target item is null");
            }
            if(!add(item)){ // if any of the items cannot be added
                return false;
            }
        }
        return true;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean contains(T item) throws NullPointerException {
        // returns if the BST contains the value
        if(item==null){
            throw new NullPointerException("The target item is null");
        }

        Node result = search(root,item);
        if(result!=null && result.value.compareTo(item)==0){
            return true;
        }
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends T> items) throws NullPointerException{
        for(T item: items){
            if(item == null){
                throw new NullPointerException("A target item is null");
            }
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        // return the leftmost node value

        if(root==null){
            throw new NoSuchElementException("The set is empty.");
        }

        Node result = root;

        while (result.left!=null){
            result = result.left;
        }
        return result.value;

//        recursion
//        BinarySearchTree sub = new BinarySearchTree(root.left);
//        return (T) sub.first();
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {

        return root==null;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        // return the rightmost node value
        if(root==null){
            throw new NoSuchElementException("The set is empty.");
        }

        Node result = root;

        while (result.right!=null){
            result = result.right;
        }
        return result.value;

    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean remove(T item) throws NullPointerException{

        if(item == null){
            throw new NullPointerException("The item is null.");
        }

        if(root == null){
            return false;
        }

        if(!contains(item)){ // if not contain, return false
            return false;
        }

        Node result = search(root,item);

        // remove the leaf node
        if(result.left == null && result.right == null){
            if(result!=root) {
                if(result.value.compareTo(result.parent.value)<=0) {
                    result.parent.left = null;
                }
                else{
                    result.parent.right = null;
                }
            }
            else{root = null;}
        }
        // remove internal node with 1 children
        else if(result.left ==null && result.right != null){
            if(result!=root) {
                if (result == result.parent.right) {
                    result.parent.right = result.right;
                } else {
                    result.parent.left = result.right;
                }
                result.right.parent = result.parent;
            }
            else{root = result.right;}
        }
        else if(result.left !=null && result.right == null){
            if(result!=root) {
                if (result == result.parent.right) {
                    result.parent.right = result.left;
                } else {
                    result.parent.left = result.left;
                }
                result.left.parent = result.parent;
            }
            else{root = result.left;}
        }
        // remove internal node with 2 children
        else{
            Node predecessor = result.left;
            int counter = 0;
            while(predecessor.right!=null){
                predecessor = predecessor.right;
                counter++;
            }
            result.value = predecessor.value;

            if(counter!=0) {
                predecessor.parent.right = predecessor.left;
            }
            else{
                predecessor.parent.left = predecessor.left;
            }
            if (predecessor.left != null) {
                predecessor.left.parent = predecessor.parent;
            }
        }
        // update the size of ancestors
        updateSize(root);
        return true;
    }

    public int updateSize(Node node) {
        if (node == null) return 0;
        node.size = updateSize(node.left) + updateSize(node.right) + 1;
        return node.size;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) throws NullPointerException {
        if(root == null){
            return false;
        }

        for(T item:items){
            if(item==null){
                throw new NullPointerException("A target item is null.");
            }
            if(!remove(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        if(root==null){
            return 0;
        }
        return root.size;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() { // recursion driver method
        ArrayList<T> result = new ArrayList<>();
        ALinOrderTraversal(root,result);
        return result;
    }

    /**
     * Recursive method to add items to the list
     *
     * @param n - the root of BST
     * @param list - the list to store items in sorted order
     */

    public void ALinOrderTraversal(Node n, ArrayList list){

        if(n == null){
            return;
        }
        ALinOrderTraversal(n.left,list);
        list.add(n.value);
        ALinOrderTraversal(n.right,list);
    }
}