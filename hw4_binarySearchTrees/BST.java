import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of a binary search tree.
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;


    /**
     * I wanted to use compare() instead of compareTo()
     * @param x data being compared
     * @param y data being compared
     * @return Comparison between x and y data
     */
    private int compare(T x, T y) {
        return x.compareTo(y);
    }
    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        String error = "Non of the data elements can be null";
        if (data == null) {
            throw new IllegalArgumentException(error);
        }
        for (T x : data) {
            if (x == null) {
                throw new IllegalArgumentException(error);
            }
            // Edit this to go thru tree
            add(x);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            String error = "The data you are trying to remove is null.";
            throw new IllegalArgumentException(error);
        }
        root = add(root, data);
    }

    /**
     * Recursive helper to add
     * @param tree the tree being modified
     * @param data the value being added
     * @return the tree that was modified
     */
    private BSTNode<T> add(BSTNode<T> tree, T data) {
        if (tree == null) {
            size++;
            return new BSTNode(data);
        }
        if (compare(data, tree.getData()) == 0) {
            return tree;
        }
        if (compare(data, tree.getData()) < 0) {
            tree.setLeft(add(tree.getLeft(), data));
        } else {
            tree.setRight(add(tree.getRight(), data));
        }
        return tree;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            String error = "The data you are trying to remove is null.";
            throw new IllegalArgumentException(error);
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = remove(root, data, dummy);
        size--;
        return dummy.getData();
    }

    /**
     * Recursive helper to remove
     * @param tree the tree being modified
     * @param data the value being removed
     * @param dummy dummy node used to save value
     * @return tree that was modified
     */
    private BSTNode<T> remove(BSTNode<T> tree, T data, BSTNode<T> dummy) {
        if (tree == null) {
            throw new java.util.NoSuchElementException("Data is not in tree");
        } else if (compare(data, tree.getData()) < 0) {
            tree.setLeft(remove(tree.getLeft(), data, dummy));
        } else if (compare(data, tree.getData()) > 0) {
            tree.setRight(remove(tree.getRight(), data, dummy));
        } else { // FOUND DATA TO DELETE
            dummy.setData(tree.getData());
            if (tree.getLeft() == null) {
                return tree.getRight();
            } else if (tree.getRight() == null) {
                return tree.getLeft();
            } else { // CASE OF 2 CHILDREN NODE
                T successor = retrieveData(tree.getRight());
                tree.setData(successor); // Leftmost node in right
                BSTNode<T> useless = new BSTNode<T>(null);
                tree.setRight(remove(tree.getRight(), successor, useless));
            }
        }
        return tree;
    }

    /**
     * Iterative function to find successor
     * @param tree the tree being modified
     * @return the successor data
     */
    private T retrieveData(BSTNode<T> tree) {
        while (tree.getLeft() != null) {
            tree = tree.getLeft();
        }
        return tree.getData();
    }

    @Override
    public T get(T data) {
        T out;
        if (data == null) {
            throw new IllegalArgumentException("Not allowed to"
                + " pass in Null data to get");
        } else if (root == null) {
            throw new java.util.NoSuchElementException("BST is empty, "
                + "not element to get");
        } else {
            out = getHelper(data, root);
        }
        if (out != null){
            return out;
        } else {
            throw new java.util.NoSuchElementException("Data "
                + "passed in was not found in the BST");
        }
    }
    /**
    * Helper function to get data.
    * @param data the data to add to the tree
    * @param tree the current node of the tree
    */
    private T getHelper(T data, BSTNode<T> tree) {
        T out;
        if (data.compareTo(tree.getData()) < 0) {
            if (tree.getLeft() == null) {
                out = null;
            } else {
                out = getHelper(data, tree.getLeft());
            }
        } else if (data.compareTo(tree.getData()) > 0) {
            if (tree.getRight() == null) {
                out = null;
            } else {
                out = getHelper(data, tree.getRight());
            }
        } else {
            out = tree.getData();
        }
        return out;
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            String error = "The data you are trying to check is null.";
            throw new IllegalArgumentException(error);
        }
        return contains(root, data);
    }

    /**
     * Recursive helper for contains
     * @param tree the tree being modified
     * @param data the value you are checking if the tree contains
     * @return true or false depending if data was found
     */
    private boolean contains(BSTNode<T> tree, T data) {
        if (tree == null) {
            return false;
        } else {
            if (compare(data, tree.getData()) == 0) {
                return true;
            } else if (compare(data, tree.getData()) < 0) {
                return contains(tree.getLeft(), data);
            } else {
                return contains(tree.getRight(), data);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> list = new ArrayList();
        return preorder(root, list);
    }

    /**
     * Recursive helper for preorder
     * @param tree the tree being traversed
     * @param list the order in which the tree is being traversed
     * @return the order in which the tree was traversed
     */
    private List<T> preorder(BSTNode<T> tree, List<T> list) {
        if (tree != null) {
            list.add(tree.getData());
            preorder(tree.getLeft(), list);
            preorder(tree.getRight(), list);
        }
        return list;
    }

    @Override
    public List<T> postorder() {
        List<T> list = new ArrayList();
        return postorder(root, list);
    }

    /**
     * Recursive helper for postorder
     * @param tree the tree being traversed
     * @param list the order in which the tree is being traversed
     * @return the order in which the tree was traversed
     */
    private List<T> postorder(BSTNode<T> tree, List<T> list) {
        if (tree != null) {
            postorder(tree.getLeft(), list);
            postorder(tree.getRight(), list);
            list.add(tree.getData());
        }
        return list;
    }

    @Override
    public List<T> inorder() {
        List<T> list = new ArrayList();
        return inorder(root, list);
    }

    /**
     * Recursive helper for inorder traversal
     * @param tree the tree being traversed
     * @param list the order in which the tree will be traversed
     * @return the order in which the tree was traversed
     */
    private List<T> inorder(BSTNode<T> tree, List<T> list) {
        if (tree != null) {
            inorder(tree.getLeft(), list);
            list.add(tree.getData());
            inorder(tree.getRight(), list);
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        if (root == null) {
            return list;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> temp = queue.poll();
            list.add(temp.getData());
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
        return list;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Recursive helper for height of tree
     * @param t the tree that you are checking the height of
     * @return the height of the tree
     */
    private int height(BSTNode<T> t) {
        if (t == null) {
            return -1;
        } else {
            return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
        }
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }


}
