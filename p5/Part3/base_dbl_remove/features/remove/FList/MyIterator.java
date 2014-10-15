package FList;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {

    public void remove() {
        MyNode<T> newCurrent;
        newCurrent = current.right;
        list.delete(current);
        current = newCurrent;
        alreadyAdvanced = true;
    }
}
