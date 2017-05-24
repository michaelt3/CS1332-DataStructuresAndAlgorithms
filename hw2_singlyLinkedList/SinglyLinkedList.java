/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("Out of bounds.");
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> temp = new LinkedListNode<T>(data);
            LinkedListNode<T> curr = head;
            for (int i = 1; i < index; i++) {
                curr = curr.getNext();
            }
            temp.setNext(curr.getNext());
            curr.setNext(temp);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null.");
        }
        head = new LinkedListNode<T>(data, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null.");
        }
        if (head == null) {
            addToFront(data);
        } else {
            tail.setNext(new LinkedListNode<T>(data));
            tail = tail.getNext();
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("Out of bounds.");
        }
        T val = null;
        if (index == 0) {
            val = removeFromFront();
        } else if (index == size - 1) {
            val = removeFromBack();
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 1; i < index; i++) {
                curr = curr.getNext();
            }
            val = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
        }
        return val;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        T val = head.getData();
        if (head == tail) {
            tail = null;
        }
        head = head.getNext();
        size--;
        return val;
    }

    @Override
    public T removeFromBack() {
        T val = null;
        if (isEmpty()) {
            return null;
        }
        if (head == tail) {
            val = tail.getData();
            head = null;
            tail = null;
        } else {
            LinkedListNode<T> curr = head;
            while (curr.getNext() != tail) {
                curr = curr.getNext();
            }
            tail = curr;
            val = curr.getNext().getData();
            curr.setNext(null);
        }
        size--;
        return val;
    }

    @Override
    public T removeFirstOccurrence(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null.");
        }
        T val = null;
        if (head.getData() == data) {
            val = removeFromFront();
            return val;
        }
        LinkedListNode<T> curr = head;
        for (int i = 1; i < size; i++) {
            if (curr.getNext().getData() == data) {
                if (i == size - 1) {
                    tail = curr;
                }
                i = size;
                curr.setNext(curr.getNext().getNext());
                val = data;
            } else {
                curr = curr.getNext();
            }
        }
        size--;
        if (val == null) {
            throw new java.util.NoSuchElementException("No such element.");
        }
        return val;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("Out of bounds.");
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == size) {
            return tail.getData();
        }
        LinkedListNode<T> curr = head;
        for (int i = 1; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getNext().getData();
    }

    @Override
    public Object[] toArray() {
        LinkedListNode<T> curr = head;
        Object[] out = (T[]) new Object[size];
        out[0] = curr.getData();
        for (int i = 1; i < size; i++) {
            curr = curr.getNext();
            out[i] = curr.getData();
        }
        return out;
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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
