package deque;

public class LinkedListDeque<T>{
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

    public void addFirst(T item){
        BasicNode temp = new BasicNode(sentinel, item, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    public T removeFirst() {
        if(this.isEmpty()) return null;

        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;

        return item;
    }

    public void addLast(T item){
        BasicNode temp = new BasicNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    public T removeLast() {
        if(this.isEmpty()) return null;

        T item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;

        return item;
    }

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

    public boolean isEmpty(){
        if(sentinel.prev == sentinel && sentinel.next == sentinel){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

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
}
