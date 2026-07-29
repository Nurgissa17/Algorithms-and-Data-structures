import java.util.Iterator;

/**
 * A generic dynamic array implementation of the MyList interface.
 * Internally backed by an Object array that doubles in capacity when full.
 *
 * @param <T> the type of elements stored in this list
 */
public class MyArrayList<T> implements MyList<T> {

    /** Internal array holding the list elements. */
    private Object[] data;

    /** Number of elements currently in the list. */
    private int size;

    /** Default starting capacity for the internal array. */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty MyArrayList with the default initial capacity.
     */
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // ----------------------------------------------------------------
    // Private helpers
    // ----------------------------------------------------------------

    /**
     * Doubles the capacity of the internal array when it is full.
     */
    private void grow() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Throws IndexOutOfBoundsException if the index is out of range.
     *
     * @param index the index to check
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for size " + size);
        }
    }

    // ----------------------------------------------------------------
    // MyList interface methods
    // ----------------------------------------------------------------

    /**
     * Adds an item to the end of the list.
     *
     * @param item the element to add
     */
    @Override
    public void add(T item) {
        if (size == data.length) grow();
        data[size++] = item;
    }

    /**
     * Replaces the element at the given index.
     *
     * @param index the position to update
     * @param item  the new value
     */
    @Override
    public void set(int index, T item) {
        checkIndex(index);
        data[index] = item;
    }

    /**
     * Inserts an item at the given index, shifting subsequent elements right.
     *
     * @param index the position at which to insert
     * @param item  the element to insert
     */
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for size " + size);
        }
        if (size == data.length) grow();
        // Shift elements to the right
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = item;
        size++;
    }

    /**
     * Adds an item to the beginning of the list.
     *
     * @param item the element to add
     */
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    /**
     * Adds an item to the end of the list.
     *
     * @param item the element to add
     */
    @Override
    public void addLast(T item) {
        add(item);
    }

    /**
     * Returns the element at the given index.
     *
     * @param index the position to retrieve
     * @return the element at that position
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    /**
     * Returns the first element of the list.
     *
     * @return the first element
     */
    @Override
    public T getFirst() {
        return get(0);
    }

    /**
     * Returns the last element of the list.
     *
     * @return the last element
     */
    @Override
    public T getLast() {
        return get(size - 1);
    }

    /**
     * Removes the element at the given index, shifting subsequent elements left.
     *
     * @param index the position to remove
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null; // help GC
    }

    /**
     * Removes the first element of the list.
     */
    @Override
    public void removeFirst() {
        remove(0);
    }

    /**
     * Removes the last element of the list.
     */
    @Override
    public void removeLast() {
        remove(size - 1);
    }

    /**
     * Sorts the list in natural ascending order using insertion sort.
     * Elements must implement Comparable.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        // Insertion sort
        for (int i = 1; i < size; i++) {
            T key = (T) data[i];
            int j = i - 1;
            while (j >= 0 && ((Comparable<T>) data[j]).compareTo(key) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    /**
     * Returns the first index of the given object, or -1 if not found.
     *
     * @param object the element to search for
     * @return the index, or -1
     */
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(object)) return i;
        }
        return -1;
    }

    /**
     * Returns the last index of the given object, or -1 if not found.
     *
     * @param object the element to search for
     * @return the last index, or -1
     */
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(object)) return i;
        }
        return -1;
    }

    /**
     * Returns true if the given object exists in the list.
     *
     * @param object the element to check
     * @return true if found
     */
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * Returns an array containing all elements in the list.
     *
     * @return Object array of elements
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }

    /**
     * Removes all elements from the list.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                return (T) data[cursor++];
            }
        };
    }

    /**
     * Returns a string representation of the list.
     *
     * @return bracketed, comma-separated elements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
