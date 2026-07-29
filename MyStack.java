/**
 * A generic stack (LIFO) built on top of MyArrayList.
 *
 * MyArrayList is the best choice here because push and pop both operate
 * on the end of the list, which is O(1) for an array-backed structure.
 *
 * Only stack-specific operations are exposed (push, pop, peek, isEmpty, size).
 *
 * @param <T> the type of elements stored in this stack
 */
public class MyStack<T> {

    /** Underlying storage. */
    private final MyArrayList<T> list;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        list = new MyArrayList<>();
    }

    /**
     * Pushes an item onto the top of the stack.
     *
     * @param item the element to push
     */
    public void push(T item) {
        list.addLast(item);
    }

    /**
     * Removes and returns the item at the top of the stack.
     *
     * @return the top element
     * @throws RuntimeException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T top = list.getLast();
        list.removeLast();
        return top;
    }

    /**
     * Returns the item at the top of the stack without removing it.
     *
     * @return the top element
     * @throws RuntimeException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return list.getLast();
    }

    /**
     * Returns true if the stack contains no elements.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a string representation of the stack (bottom → top).
     *
     * @return string of elements
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
