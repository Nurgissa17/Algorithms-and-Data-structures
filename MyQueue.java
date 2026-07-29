/**
 * A generic queue (FIFO) built on top of MyLinkedList.
 *
 * MyLinkedList is the best choice here because:
 *   - enqueue adds to the tail: O(1)
 *   - dequeue removes from the head: O(1)
 * An array-based structure would require shifting on every dequeue.
 *
 * Only queue-specific operations are exposed (enqueue, dequeue, peek, isEmpty, size).
 *
 * @param <T> the type of elements stored in this queue
 */
public class MyQueue<T> {

    /** Underlying storage. */
    private final MyLinkedList<T> list;

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        list = new MyLinkedList<>();
    }

    /**
     * Adds an item to the back of the queue.
     *
     * @param item the element to enqueue
     */
    public void enqueue(T item) {
        list.addLast(item);
    }

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return the front element
     * @throws RuntimeException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        T front = list.getFirst();
        list.removeFirst();
        return front;
    }

    /**
     * Returns the item at the front of the queue without removing it.
     *
     * @return the front element
     * @throws RuntimeException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return list.getFirst();
    }

    /**
     * Returns true if the queue contains no elements.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the size
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a string representation of the queue (front → back).
     *
     * @return string of elements
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
