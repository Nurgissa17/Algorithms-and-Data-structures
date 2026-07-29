import java.util.Iterator;

/**
 * A generic doubly-linked list implementation of the MyList interface.
 * Each node holds a reference to both the next and previous node.
 *
 * @param <T> the type of elements stored in this list
 */
public class MyLinkedList<T> implements MyList<T> {

    // ----------------------------------------------------------------
    // Inner node class
    // ----------------------------------------------------------------

    /**
     * A private inner class representing a single node in the doubly-linked list.
     *
     * @param <E> the type of element held by this node
     */
    private static class MyNode<E> {
        E element;
        MyNode<E> next;
        MyNode<E> prev;

        MyNode(E element, MyNode<E> prev, MyNode<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    // ----------------------------------------------------------------
    // Instance variables
    // ----------------------------------------------------------------

    /** Reference to the first node. */
    private MyNode<T> head;

    /** Reference to the last node. */
    private MyNode<T> tail;

    /** Number of elements in the list. */
    private int size;

    /**
     * Constructs an empty MyLinkedList.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ----------------------------------------------------------------
    // Private helpers
    // ----------------------------------------------------------------

    /**
     * Returns the node at the given index by traversing from head or tail,
     * whichever is closer, to avoid unnecessary traversal.
     *
     * @param index the position of the desired node
     * @return the node at that position
     */
    private MyNode<T> nodeAt(int index) {
        MyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
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
        MyNode<T> newNode = new MyNode<>(item, tail, null);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode; // list was empty
        }
        tail = newNode;
        size++;
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
        nodeAt(index).element = item;
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
        if (index == size) {
            add(item);
            return;
        }
        MyNode<T> successor = nodeAt(index);
        MyNode<T> predecessor = successor.prev;
        MyNode<T> newNode = new MyNode<>(item, predecessor, successor);
        successor.prev = newNode;
        if (predecessor != null) {
            predecessor.next = newNode;
        } else {
            head = newNode; // inserting at head
        }
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
    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).element;
    }

    /**
     * Returns the first element of the list.
     *
     * @return the first element
     */
    @Override
    public T getFirst() {
        if (head == null) throw new IndexOutOfBoundsException("List is empty");
        return head.element;
    }

    /**
     * Returns the last element of the list.
     *
     * @return the last element
     */
    @Override
    public T getLast() {
        if (tail == null) throw new IndexOutOfBoundsException("List is empty");
        return tail.element;
    }

    /**
     * Removes the element at the given index by re-linking its neighbours.
     * Nulls out the removed node's pointers to prevent memory loops.
     *
     * @param index the position to remove
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        MyNode<T> toRemove = nodeAt(index);

        MyNode<T> prev = toRemove.prev;
        MyNode<T> next = toRemove.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next; // removed head
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev; // removed tail
        }

        // Prevent memory loops by nulling the removed node's links
        toRemove.next = null;
        toRemove.prev = null;

        size--;
    }

    /**
     * Removes the first element of the list.
     */
    @Override
    public void removeFirst() {
        if (head == null) throw new IndexOutOfBoundsException("List is empty");
        remove(0);
    }

    /**
     * Removes the last element of the list.
     */
    @Override
    public void removeLast() {
        if (tail == null) throw new IndexOutOfBoundsException("List is empty");
        remove(size - 1);
    }

    /**
     * Sorts the list in natural ascending order using insertion sort.
     * Elements must implement Comparable.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size <= 1) return;

        // Insertion sort by swapping elements (not nodes)
        MyNode<T> sorted = head.next;
        while (sorted != null) {
            T key = sorted.element;
            MyNode<T> current = sorted.prev;
            while (current != null && ((Comparable<T>) current.element).compareTo(key) > 0) {
                current.next.element = current.element;
                current = current.prev;
            }
            if (current == null) {
                head.element = key;
            } else {
                current.next.element = key;
            }
            sorted = sorted.next;
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
        MyNode<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(object)) return index;
            current = current.next;
            index++;
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
        MyNode<T> current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.element.equals(object)) return index;
            current = current.prev;
            index--;
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
        MyNode<T> current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.element;
            current = current.next;
        }
        return result;
    }

    /**
     * Removes all elements from the list.
     * Nulls out all node links to prevent memory loops.
     */
    @Override
    public void clear() {
        MyNode<T> current = head;
        while (current != null) {
            MyNode<T> next = current.next;
            current.prev = null;
            current.next = null;
            current = next;
        }
        head = null;
        tail = null;
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
     * Returns an iterator that traverses the list from head to tail.
     *
     * @return an Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T element = current.element;
                current = current.next;
                return element;
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
        MyNode<T> current = head;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
