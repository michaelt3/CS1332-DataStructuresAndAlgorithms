/**
 * Your implementation of a max heap.
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial length of {@code INITIAL_CAPACITY} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        backingArray[0] = null;
        size = 0;
    }

    /**
     * Helper function to find parent position
     * @param pos The position you are trying to get the parent of
     * @return the pos of the parent
     */
    private int parent(int pos) {
        int out = pos / 2;
        if (out == 0) {
            return 1;
        }
        return out;
    }

    /**
     * Helper function to swap values
     * @param pos1 position of first value to be swapped
     * @param pos2 position of second value to be swapped
     */
    private void swap(int pos1, int pos2) {
        T temp;
        temp = backingArray[pos1];
        backingArray[pos1] = backingArray[pos2];
        backingArray[pos2] = temp;
    }

    /**
     *
     * @param pos the position you are checking if its a leaf
     * @return true or false depending on leaf condition
     */
    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    /**
     * Helper function to heapify
     * @param pos the position you are trying to heapify
     */
    private void heapify(int pos) {
        if (!isLeaf(pos)) {
            if (backingArray[pos].compareTo(backingArray[pos * 2]) < 0
                    || backingArray[pos]
                    .compareTo(backingArray[pos * 2 + 1]) < 0) {
                if (backingArray[pos * 2]
                        .compareTo(backingArray[pos * 2 + 1]) > 0) {
                    swap(pos, pos * 2);
                    heapify(pos * 2);
                } else {
                    swap(pos, pos * 2 + 1);
                    heapify(pos * 2 + 1);
                }
            }
        }
    }

    @Override
    public T remove() {
        T out = null;
        if (!isEmpty()) {
            out = backingArray[1];
            backingArray[1] = backingArray[size];
            heapify(1);
            backingArray[size] = null;
            size--;
        } else {
            String error = "The heap is currently empty. Cannot remove";
            throw new java.util.NoSuchElementException(error);
        }
        return out;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            String error = "The data you are trying to add is null. Cannot add";
            throw new IllegalArgumentException(error);
        }
        if (size == backingArray.length - 1) {
            T[] newArray = (T[]) new Comparable[backingArray.length * 2 + 1];
            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
        size++;
        int index = size;
        backingArray[size] = item;
        while (backingArray[index].compareTo(backingArray[parent(index)]) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        backingArray[0] = null;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
