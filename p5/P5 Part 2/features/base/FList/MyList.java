package FList;

import java.io.PrintStream;
import LApp.Entity;
import java.util.Iterator;

public class MyList implements Iterable {

    MyNode head;
    MyNode tail;
    
    public MyList() {
        head = null;
        tail = null;
    }

    public void insert(Entity elem) {
        insert(new MyNode(elem));
    }

    void insert(MyNode n) {
        n.right = head;
        head = n;
    }

    public MyIterator iterator() {
        return new MyIterator(this);
    }

    public void print(PrintStream out) {
    	System.out.println("Enter print function!");
        for (Iterator eIterator = iterator(); 
		eIterator.hasNext();) {
            Entity e = (Entity) eIterator.next();
            out.println(e);
        }
    }
    
    /*
     * Use for JUnit testing
     */
    public MyNode getHead()
    {
    	return head;
    }
    
    public void addLeftLink(MyNode n, MyNode preNode) {     	
    }

    public void delete(MyNode n) {
    }
    
    public boolean isOrdered()
    {
    	return false;
    }
}
