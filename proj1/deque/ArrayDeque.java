package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int capacity;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(capacity * 2);
        }

        items[nextFirst] = item;
        nextFirst = (nextFirst + capacity - 1) % capacity;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        if (capacity >= 16 && (double) (size - 1) / capacity < 0.25) {
            resize(capacity / 2);
        }

        int startPos = (nextFirst + 1) % capacity;
        T returnItem = items[startPos];
        items[startPos] = null;
        size -= 1;
        nextFirst = startPos;

        return returnItem;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(capacity * 2);
        }

        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size += 1;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        if (capacity >= 16 && (double) (size - 1) / capacity < 0.25) {
            resize(capacity / 2);
        }

        int endPos = (nextLast - 1 + capacity) % capacity;
        T returnItem = items[endPos];
        items[endPos] = null;
        size -= 1;
        nextLast = endPos;

        return returnItem;
    }

    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];

        for (int k = 0; k < size(); k++) {
            a[k] = get(k);
        }

        items = a;
        nextLast = size;
        nextFirst = newCapacity - 1;
        capacity = newCapacity;

    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int k = 0; k < size; k++) {
            System.out.print(get(k) + " ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (this.isEmpty() || index >= this.size() || index <= -1) {
            return null;
        }

        int startPos = (nextFirst + 1) % capacity;
        return items[(startPos + index) % capacity];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            int startPos = (nextFirst + 1) % capacity;
            T returnItem = items[(startPos + wizPos) % capacity];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Deque)) {
            return false;
        }

        Deque<T> oad = (Deque<T>) o;

        if (oad.size() != this.size) {
            return false;
        }

        for (int i = 0; i < this.size; i++) {
            if (!oad.get(i).equals(this.get(i))) {
                return false;
            }
        }

        return true;
    }
}
