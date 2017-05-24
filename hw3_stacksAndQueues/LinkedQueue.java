/**
 * Your implementation of a linked queue.
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (isEmpty()) {
            String error = "The queue is empty. Cannot remove.";
            throw new java.util.NoSuchElementException(error);
        } else {
            T val = head.getData();
            if (head == tail) {
                tail = null;
            }
            head = head.getNext();
            size--;
            return val;
        }
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            String error = "The value being added is null.";
            throw new IllegalArgumentException(error);
        } else if (isEmpty()) {
            head = new LinkedNode<T>(data);
            tail = head;
        } else {
            tail.setNext(new LinkedNode<T>(data));
            tail = tail.getNext();
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
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

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}