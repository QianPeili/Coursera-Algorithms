import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by qianpeili on 2017/8/16.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int nodeSize;

    private class Node {
        Item item;
        Node previous;
        Node next;

        Node(Item item) {
            this.item = item;
            this.previous = null;
            this.next = null;
        }
    }

    public Deque() {
        head = null;
        tail = null;
        nodeSize = 0;
    }

    public int size() {
        return nodeSize;
    }

    public boolean isEmpty() {
        return nodeSize == 0;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null!");
        }

        Node node = new Node(item);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }

        nodeSize++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null!");
        }

        Node node = new Node(item);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
        ++nodeSize;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is empty");
        }

        Node node = head;
        head = node.next;
        node.next = null;
        --nodeSize;

        if (nodeSize == 0) {
            tail = null;
        } else {
            head.previous = null;
        }

        return node.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is empty");
        }

        Node node = tail;
        tail = node.previous;
        node.previous = null;
        nodeSize--;
        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }
        return node.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item> {
        private Node current;

        private DequeIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (hasNext()) {
                Node node = current;
                current = current.next;
                return node.item;
            } else {
                throw new NoSuchElementException("no more item");
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("not support remove mthod.");
        }


    }

    public static void main(String args[]) {
        Deque deque = new Deque();
        deque.isEmpty();
        deque.addLast(1);
        deque.isEmpty();
        deque.removeLast();
    }

}


