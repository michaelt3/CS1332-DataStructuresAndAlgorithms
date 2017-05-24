/**
 * Your implementation of an array-backed queue.
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = -1;
        back = -1;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            String error = "The queue is empty. Cannot dequeue.";
            throw new java.util.NoSuchElementException(error);
        }
        T val = null;
        if (front == back) {
            val = backingArray[front];
            backingArray[front] = null;
            front = -1;
            back = -1;
        } else {
            val = backingArray[front];
            backingArray[front] = null;
            front = (front + 1) % backingArray.length;
        }
        size--;
        return val;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity. If a regrow is necessary, you should copy
     * elements to the front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            String error = "The data you are trying to add is null.Cannot add.";
            throw new IllegalArgumentException(error);
        }
        if ((back + 1) % backingArray.length == front) {
            T[] newArray = (T[]) new Object[backingArray.length * 2 + 1];
            for (int i = 0; i < size; i++) {
                newArray[i] = backingArray[(front + i) % backingArray.length];
            }
            backingArray = newArray;
            front = 0;
            back = size - 1;
        }

        if (isEmpty()) {
            front++;
            back++;
            backingArray[back] = data;
        } else {
            back = (back + 1) % backingArray.length;
            backingArray[back] = data;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (front == -1 && back == -1);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }
}