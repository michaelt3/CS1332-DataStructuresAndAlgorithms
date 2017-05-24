/**
 * Your implementation of a linked stack.
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            String error = "The stack is currently empty. Cannot pop.";
            throw new java.util.NoSuchElementException(error);
        }
        T val = head.getData();
        head = head.getNext();
        size--;
        return val;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            String error = "The data trying to be added is null.";
            throw new java.lang.IllegalArgumentException(error);
        } else if (isEmpty()) {
            head = new LinkedNode<T>(data);
        } else {
            LinkedNode<T> next = head;
            head = new LinkedNode<T>(data);
            head.setNext(next);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}