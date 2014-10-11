package FList;

import java.util.Iterator;
import LApp.Entity;

public class MyIterator implements Iterator {

    public void remove() {
        MyNode newCurrent;
        newCurrent = current.right;
        list.delete(current);
        current = newCurrent;
        alreadyAdvanced = true;
    }
}
