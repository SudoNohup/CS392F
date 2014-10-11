package FList; 

import java.io.PrintStream; 
import java.util.Iterator; 

public  class  MyList  implements Iterable {
	

    MyNode head;

	
    MyNode tail;

	

    public MyList() {
        head = null;
        tail = null;
    }

	

    public void insert(Object elem) {
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
            Object e = eIterator.next();
            out.println(e);
        }
    }

	

    public void delete(MyNode n) {
    }


}
