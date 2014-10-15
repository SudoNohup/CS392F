package FList;

import java.io.PrintStream;
import LApp.Entity;
import java.util.Iterator;

public class MyList implements Iterable {

    public void delete(MyNode n) {
        if (n.left != null) {
            n.left.right = n.right;
        } else {
            head = n.right;
        }
        if (n.right != null) {
            n.right.left = n.left;
        } else {
            tail = n.left;
        }
    }
}
