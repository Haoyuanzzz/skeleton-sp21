package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int capacity;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        capacity = 8;
        items = (T []) new Object[capacity];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(T item){
        if(size == items.length){
            resize(capacity*2);
        }

        items[nextFirst] = item;
        nextFirst = (nextFirst + capacity - 1) % capacity;
        size += 1;
    }

    public T removeFirst() {
        if(this.isEmpty()) return null;

        if( (double)(size-1)/capacity < 0.25){
            resize(capacity/2);
        }

        int startPos = (nextFirst + 1) % capacity;
        T returnItem = items[startPos];
        items[startPos] = null;
        size -= 1;
        nextFirst = startPos;

        return returnItem;
    }

    public void addLast(T item){
        if(size == items.length){
            resize(capacity*2);
        }

        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size += 1;
    }

    public T removeLast() {
        if(this.isEmpty()) return null;

        if( (double)(size-1)/capacity < 0.25){
            resize(capacity/2);
        }

        int endPos = (nextLast - 1 + capacity) % capacity;
        T returnItem = items[endPos];
        items[endPos] = null;
        size -= 1;
        nextLast = endPos;

        return returnItem;
    }

    private void resize(int newCapacity){
        T[] a = (T []) new Object[newCapacity];

//        System.arraycopy(items, startPos, a, 0, capacity-startPos);
//        System.arraycopy(items, 0, a, capacity-startPos, (endPos+1)%capacity);

        for (int k = 0; k < size(); k++) {
            a[k] = get(k);
        }

        items = a;
        nextLast = size;
        nextFirst =newCapacity - 1;
        capacity = newCapacity;

    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
//        int startPos = (nextFirst + 1) % capacity;
//        int endPos = (nextLast + capacity - 1) % capacity;
        for (int k = 0; k < size; k++) {
            System.out.print(get(k)+" ");
        }
        System.out.println();
    }

    public T get(int index){
        if(this.isEmpty() || index>=this.size() || index<=-1 ) return null;

        int startPos = (nextFirst + 1) % capacity;
        return items[(startPos+index)%capacity];
    }
}
