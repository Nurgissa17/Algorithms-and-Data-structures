import java.util.Iterator;

/**
 * Generic interface for list data structures.
 * Both MyArrayList and MyLinkedList implement this interface.
 *
 * @param <T> the type of elements in this list
 */
public interface MyList<T> extends Iterable<T> {
    /** Adds an item to the end of the list. */
    void add(T item);

    /** Replaces the element at the given index with the given item. */
    void set(int index, T item);

    /** Inserts an item at the given index, shifting elements to the right. */
    void add(int index, T item);

    /** Adds an item to the beginning of the list. */
    void addFirst(T item);

    /** Adds an item to the end of the list. */
    void addLast(T item);

    /** Returns the element at the given index. */
    T get(int index);

    /** Returns the first element of the list. */
    T getFirst();

    /** Returns the last element of the list. */
    T getLast();

    /** Removes the element at the given index. */
    void remove(int index);

    /** Removes the first element of the list. */
    void removeFirst();

    /** Removes the last element of the list. */
    void removeLast();

    /** Sorts the list in natural ascending order. */
    void sort();

    /** Returns the first index of the given object, or -1 if not found. */
    int indexOf(Object object);

    /** Returns the last index of the given object, or -1 if not found. */
    int lastIndexOf(Object object);

    /** Returns true if the given object exists in the list. */
    boolean exists(Object object);

    /** Returns an array containing all elements of the list. */
    Object[] toArray();

    /** Removes all elements from the list. */
    void clear();

    /** Returns the number of elements in the list. */
    int size();
}
