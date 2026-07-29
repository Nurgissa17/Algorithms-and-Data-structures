/**
 * Tests all methods of MyArrayList, MyLinkedList, MyStack, MyQueue, and MyMinHeap.
 * Each section prints results so you can verify correct behaviour at a glance.
 */
public class Main {

    public static void main(String[] args) {
        testMyArrayList();
        testMyLinkedList();
        testMyStack();
        testMyQueue();
        testMyMinHeap();
    }

    // ----------------------------------------------------------------
    // MyArrayList tests
    // ----------------------------------------------------------------

    private static void testMyArrayList() {
        System.out.println("===== MyArrayList =====");

        MyArrayList<Integer> list = new MyArrayList<>();

        // add / addFirst / addLast
        list.add(3);
        list.add(5);
        list.add(7);
        list.addFirst(1);   // [1, 3, 5, 7]
        list.addLast(9);    // [1, 3, 5, 7, 9]
        System.out.println("After add/addFirst/addLast: " + list); // [1, 3, 5, 7, 9]

        // add at index
        list.add(2, 4);     // [1, 3, 4, 5, 7, 9]
        System.out.println("After add(2, 4): " + list);

        // set
        list.set(0, 10);    // [10, 3, 4, 5, 7, 9]
        System.out.println("After set(0, 10): " + list);

        // get / getFirst / getLast
        System.out.println("get(2): " + list.get(2));       // 4
        System.out.println("getFirst(): " + list.getFirst()); // 10
        System.out.println("getLast(): " + list.getLast());   // 9

        // remove / removeFirst / removeLast
        list.remove(0);     // [3, 4, 5, 7, 9]
        System.out.println("After remove(0): " + list);
        list.removeFirst(); // [4, 5, 7, 9]
        System.out.println("After removeFirst(): " + list);
        list.removeLast();  // [4, 5, 7]
        System.out.println("After removeLast(): " + list);

        // sort
        MyArrayList<Integer> unsorted = new MyArrayList<>();
        unsorted.add(5); unsorted.add(2); unsorted.add(8); unsorted.add(1);
        System.out.println("Before sort: " + unsorted);
        unsorted.sort();
        System.out.println("After sort: " + unsorted); // [1, 2, 5, 8]

        // indexOf / lastIndexOf / exists
        MyArrayList<String> strList = new MyArrayList<>();
        strList.add("a"); strList.add("b"); strList.add("a");
        System.out.println("indexOf('a'): " + strList.indexOf("a"));       // 0
        System.out.println("lastIndexOf('a'): " + strList.lastIndexOf("a")); // 2
        System.out.println("exists('b'): " + strList.exists("b"));           // true
        System.out.println("exists('z'): " + strList.exists("z"));           // false

        // toArray
        Object[] arr = strList.toArray();
        System.out.print("toArray: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        // size
        System.out.println("size: " + strList.size()); // 3

        // iterator
        System.out.print("iterator: ");
        for (String s : strList) System.out.print(s + " ");
        System.out.println();

        // clear
        strList.clear();
        System.out.println("After clear, size: " + strList.size()); // 0

        System.out.println();
    }

    // ----------------------------------------------------------------
    // MyLinkedList tests
    // ----------------------------------------------------------------

    private static void testMyLinkedList() {
        System.out.println("===== MyLinkedList =====");

        MyLinkedList<Integer> list = new MyLinkedList<>();

        // add / addFirst / addLast
        list.add(3);
        list.add(5);
        list.add(7);
        list.addFirst(1);   // [1, 3, 5, 7]
        list.addLast(9);    // [1, 3, 5, 7, 9]
        System.out.println("After add/addFirst/addLast: " + list);

        // add at index
        list.add(2, 4);     // [1, 3, 4, 5, 7, 9]
        System.out.println("After add(2, 4): " + list);

        // set
        list.set(0, 10);    // [10, 3, 4, 5, 7, 9]
        System.out.println("After set(0, 10): " + list);

        // get / getFirst / getLast
        System.out.println("get(2): " + list.get(2));        // 4
        System.out.println("getFirst(): " + list.getFirst()); // 10
        System.out.println("getLast(): " + list.getLast());   // 9

        // remove / removeFirst / removeLast
        list.remove(0);     // [3, 4, 5, 7, 9]
        System.out.println("After remove(0): " + list);
        list.removeFirst(); // [4, 5, 7, 9]
        System.out.println("After removeFirst(): " + list);
        list.removeLast();  // [4, 5, 7]
        System.out.println("After removeLast(): " + list);

        // sort
        MyLinkedList<Integer> unsorted = new MyLinkedList<>();
        unsorted.add(5); unsorted.add(2); unsorted.add(8); unsorted.add(1);
        System.out.println("Before sort: " + unsorted);
        unsorted.sort();
        System.out.println("After sort: " + unsorted); // [1, 2, 5, 8]

        // indexOf / lastIndexOf / exists
        MyLinkedList<String> strList = new MyLinkedList<>();
        strList.add("a"); strList.add("b"); strList.add("a");
        System.out.println("indexOf('a'): " + strList.indexOf("a"));        // 0
        System.out.println("lastIndexOf('a'): " + strList.lastIndexOf("a")); // 2
        System.out.println("exists('b'): " + strList.exists("b"));           // true
        System.out.println("exists('z'): " + strList.exists("z"));           // false

        // toArray
        Object[] arr = strList.toArray();
        System.out.print("toArray: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        // size
        System.out.println("size: " + strList.size()); // 3

        // iterator
        System.out.print("iterator: ");
        for (String s : strList) System.out.print(s + " ");
        System.out.println();

        // clear
        strList.clear();
        System.out.println("After clear, size: " + strList.size()); // 0

        System.out.println();
    }

    // ----------------------------------------------------------------
    // MyStack tests
    // ----------------------------------------------------------------

    private static void testMyStack() {
        System.out.println("===== MyStack =====");

        MyStack<Integer> stack = new MyStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("After push 10, 20, 30: " + stack); // [10, 20, 30]
        System.out.println("peek: " + stack.peek());           // 30
        System.out.println("pop: " + stack.pop());             // 30
        System.out.println("After pop: " + stack);             // [10, 20]
        System.out.println("size: " + stack.size());           // 2
        System.out.println("isEmpty: " + stack.isEmpty());     // false

        System.out.println();
    }

    // ----------------------------------------------------------------
    // MyQueue tests
    // ----------------------------------------------------------------

    private static void testMyQueue() {
        System.out.println("===== MyQueue =====");

        MyQueue<String> queue = new MyQueue<>();

        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        System.out.println("After enqueue first, second, third: " + queue);
        System.out.println("peek: " + queue.peek());             // first
        System.out.println("dequeue: " + queue.dequeue());       // first
        System.out.println("After dequeue: " + queue);           // [second, third]
        System.out.println("size: " + queue.size());             // 2
        System.out.println("isEmpty: " + queue.isEmpty());       // false

        System.out.println();
    }

    // ----------------------------------------------------------------
    // MyMinHeap tests
    // ----------------------------------------------------------------

    private static void testMyMinHeap() {
        System.out.println("===== MyMinHeap =====");

        MyMinHeap<Integer> heap = new MyMinHeap<>();

        heap.insert(10);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(5);
        System.out.println("Heap after inserts: " + heap);  // min at root
        System.out.println("getMin: " + heap.getMin());      // 1
        System.out.println("extractMin: " + heap.extractMin()); // 1
        System.out.println("extractMin: " + heap.extractMin()); // 3
        System.out.println("extractMin: " + heap.extractMin()); // 5
        System.out.println("Remaining heap: " + heap);
        System.out.println("size: " + heap.size());          // 2
        System.out.println("isEmpty: " + heap.isEmpty());    // false

        System.out.println();
    }
}
