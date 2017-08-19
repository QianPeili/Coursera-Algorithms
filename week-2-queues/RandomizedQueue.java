import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/**
 * Created by qianpeili on 2017/8/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int index;
    private int capacity;

    public RandomizedQueue() {
        index = 0;
        capacity = 4;
        queue = (Item[]) new Object[capacity];
    }

    public int size() {
        return index;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    private void increaseCapacity() {
        capacity *= 2;
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < index; i++) {
            newQueue[i] = queue[i];
        }
        this.queue = newQueue;
    }

    private void decreaseCapacity() {
        capacity /= 2;
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < index; i++) {
            newQueue[i] = queue[i];
        }
        this.queue = newQueue;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null!");
        }
        if (index >= capacity) {
            increaseCapacity();
        }
        queue[index++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is empty");
        }

        int i = StdRandom.uniform(index);
        Item item = queue[i];
        --index;
        queue[i] = queue[index];
        queue[index] = null;
        if (capacity >= 4 && index <= capacity / 4) {
            decreaseCapacity();
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is empty");
        }

        int i = StdRandom.uniform(index);
        return queue[i];
    }


    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        private int[] order;

        private RandomizedQueueIterator() {
            current = 0;
            order = new int[index];
            for (int i = 0; i < index; i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            while (current < order.length && order[current] >= index) {
                current++;
            }
            return current < order.length;
        }

        public Item next() {
            if (hasNext()) {
                return queue[current++];
            } else {
                throw new NoSuchElementException("no more item");
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("not support remove method.");
        }


    }

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("a");
        System.out.println(q.dequeue());
        q.enqueue("c");
        q.enqueue("b");
        q.enqueue("b");
        q.enqueue("b");
        System.out.println(q.dequeue());
        q.enqueue("b");
        q.enqueue("b");
        q.enqueue("b");
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }

}

