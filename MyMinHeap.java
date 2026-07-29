/**
 * A generic min-heap built on top of MyArrayList.
 *
 * MyArrayList is the natural choice for a heap because the standard
 * binary-heap array indexing (parent = (i-1)/2, left = 2i+1, right = 2i+2)
 * only works efficiently with random-access storage.
 *
 * The smallest element is always at the root (index 0).
 *
 * Only heap-specific operations are exposed (insert, extractMin, getMin, isEmpty, size).
 *
 * @param <T> the type of elements; must implement Comparable
 */
public class MyMinHeap<T extends Comparable<T>> {

    /** Underlying storage. */
    private final MyArrayList<T> list;

    /**
     * Constructs an empty min-heap.
     */
    public MyMinHeap() {
        list = new MyArrayList<>();
    }

    // ----------------------------------------------------------------
    // Private helpers
    // ----------------------------------------------------------------

    /** Returns the index of the parent of node at index i. */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /** Returns the index of the left child of node at index i. */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /** Returns the index of the right child of node at index i. */
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Swaps the elements at positions i and j in the underlying list.
     *
     * @param i first index
     * @param j second index
     */
    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Moves the element at index i upward until the heap property is restored.
     * Called after insertion.
     *
     * @param i the starting index
     */
    private void heapifyUp(int i) {
        while (i > 0) {
            int p = parent(i);
            if (list.get(i).compareTo(list.get(p)) < 0) {
                swap(i, p);
                i = p;
            } else {
                break;
            }
        }
    }

    /**
     * Moves the element at index i downward until the heap property is restored.
     * Called after extraction.
     *
     * @param i the starting index
     */
    private void heapifyDown(int i) {
        int n = list.size();
        while (true) {
            int smallest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < n && list.get(left).compareTo(list.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < n && list.get(right).compareTo(list.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    // ----------------------------------------------------------------
    // Public heap operations
    // ----------------------------------------------------------------

    /**
     * Inserts a new element into the heap.
     *
     * @param item the element to insert
     */
    public void insert(T item) {
        list.addLast(item);
        heapifyUp(list.size() - 1);
    }

    /**
     * Removes and returns the minimum element (root) from the heap.
     *
     * @return the smallest element
     * @throws RuntimeException if the heap is empty
     */
    public T extractMin() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        T min = list.getFirst();
        // Move last element to root and sift down
        list.set(0, list.getLast());
        list.removeLast();
        if (!isEmpty()) heapifyDown(0);
        return min;
    }

    /**
     * Returns the minimum element without removing it.
     *
     * @return the smallest element
     * @throws RuntimeException if the heap is empty
     */
    public T getMin() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        return list.getFirst();
    }

    /**
     * Returns true if the heap contains no elements.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the size
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a string representation of the heap's internal array.
     *
     * @return string of elements
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
