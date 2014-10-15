package FList;

import java.io.PrintStream;
import java.util.Iterator;

public class MyList<T> implements Iterable<T> {

    void insert(MyNode<T> n)
    {
    	original(n);
    	n.left = null;
        if (n.right != null) {
           n.right.left = n;
        }
    }
}
