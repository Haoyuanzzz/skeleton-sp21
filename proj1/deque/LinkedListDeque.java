package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private BasicNode sentinel;
    private int size;

    private class BasicNode{
        public BasicNode prev;
        public T item;
        public BasicNode next;

        public BasicNode(BasicNode p, T i, BasicNode n){
            prev = p;
            item = i;
            next = n;
        }

        public BasicNode(BasicNode p, BasicNode n){
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque(){
        sentinel = new BasicNode(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item){
        BasicNode temp = new BasicNode(sentinel, item, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if(this.isEmpty()) return null;

        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;

        return item;
    }

    @Override
    public void addLast(T item){
        BasicNode temp = new BasicNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    @Override
    public T removeLast() {
        if(this.isEmpty()) return null;

        T item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;

        return item;
    }

    @Override
    public T get(int index){
        if(this.isEmpty() || index>=this.size() || index<=-1 ) return null;

        int i = 0;
        BasicNode p = sentinel.next;
        while(i < index){
            i +=1;
            p = p.next;
        }

        return p.item;
    }

    public T getRecursive(int index){
        if(this.isEmpty() || index>=this.size() || index<=-1 ) return null;

        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int i, BasicNode p){
        if(i == 0) return p.item;

        return getRecursiveHelper(i-1, p.next); //p = p.next
    }



    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        printDequeHelper(sentinel.next);
    }

    private void printDequeHelper(BasicNode start){
        if(start.next == sentinel){
            System.out.println(start.item);
            return;
        }

        System.out.print(start.item + " ");
        printDequeHelper(start.next);

    }

    @Override
    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int wizPos;

        private BasicNode curr;

        public LinkedListDequeIterator(){
            wizPos = 0;
            curr = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = curr.item;
            curr = curr.next;
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        if (this == o) return true;

        if (this.getClass() != o.getClass()) return false;

        LinkedListDeque<T> olld = (LinkedListDeque<T>) o;

        if (olld.size() != this.size){
            return false;
        }

        // check that all of my items are in the other  deque in the same order
        BasicNode mycurr = this.sentinel.next;
        BasicNode ocurr = olld.sentinel.next;
        for (int i=0; i<this.size; i++){
            if ( ! mycurr.item.equals(ocurr.item) ){
                return false;
            }
            mycurr = mycurr.next;
            ocurr = ocurr.next;
        }
        return true;
    }
}
