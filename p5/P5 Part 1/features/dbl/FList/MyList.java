package FList;

import java.io.PrintStream;
import LApp.Entity;
import java.util.Iterator;

public class MyList implements Iterable {

    void insert(MyNode n)
    {
    	original(n);
    	n.left = null;
        if (n.right != null) {
           n.right.left = n;
        }
    }
}
