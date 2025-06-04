package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> madComparator;

    public MaxArrayDeque() {
        super();
    }


    public MaxArrayDeque(Comparator<T> c) {
        super();
        madComparator = c;
    }

    public T max() {
        // If the MaxArrayDeque is empty, simply return null
        if (this.isEmpty()) return null;

        T maxItem = this.get(0);
        for (T x : this) {
            if (madComparator.compare(x, maxItem) > 0) {
                maxItem = x;
            }
        }

        return maxItem;
    }

    public T max(Comparator<T> c) {
        // If the MaxArrayDeque is empty, simply return null
        if (this.isEmpty()) return null;

        T maxItem = this.get(0);
        for (T x : this) {
            if (c.compare(x, maxItem) > 0) {
                maxItem = x;
            }
        }

        return maxItem;
    }
}
