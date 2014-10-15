package FList; 

import java.io.PrintStream; 
import LApp.Entity; 
import java.util.Iterator; 

public  class  MyList  implements Iterable {
	

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

	

    public Iterator iterator() {
        return new MyIterator(this);
    }

	

    public void print(PrintStream out) {
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


}
