package FList;

import java.io.PrintStream;
import java.util.Iterator;

public class MyList<T> implements Iterable<T> {

    public void delete(MyNode<T> n) {
    	original(n);
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
