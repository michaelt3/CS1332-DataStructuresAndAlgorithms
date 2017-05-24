/**
 * Your implementation of an ArrayList.
 *
 * @author YOUR NAME HERE
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("Index out of range");
        } else if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        } else {
            if (size == backingArray.length) {
                T[] newArray = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < size; i++) {
                    newArray[i] = backingArray[i];
                }
                backingArray = newArray;
            }
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        } else {
            if (size == backingArray.length) {
                T[] newArray = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < size; i++) {
                    newArray[i] = backingArray[i];
                }
                backingArray = newArray;
            }
            for (int i = size; i > 0; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[0] = data;
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        } else {
            if (size == backingArray.length) {
                T[] newArray = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < size; i++) {
                    newArray[i] = backingArray[i];
                }
                backingArray = newArray;
            }
            backingArray[size] = data;
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bound");
        } else {
            T num = backingArray[index];
            for (int i = index; i < size; i++) {
                if (i == size - 1) {
                    backingArray[size - 1] = null;
                } else {
                    backingArray[i] = backingArray[i + 1];
                }
            }
            size--;
            return num;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else {
            T num = backingArray[0];
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    backingArray[size - 1] = null;
                } else {
                    backingArray[i] = backingArray[i + 1];
                }
            }
            size--;
            return num;
        }
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        } else {
            T num = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
            return num;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("Index out of range");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
