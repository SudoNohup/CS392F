package FList; 

import java.io.PrintStream; 

import FList.MyNode; 
import java.util.Iterator; 

public   class  MyList <T>  implements Iterable<T> {
	

    MyNode<T> head;

	
    MyNode<T> tail;

	

    public MyList() {
        head = null;
        tail = null;
    }

	

    public void insert(T elem) {
        insert(new MyNode<T>(elem));
    }

	

     private void  insert__wrappee__base  (MyNode n) {
        n.right = head;
        head = n;
    }

	

    void insert(MyNode n)
    {
    	insert__wrappee__base(n);
    	n.left = null;
        if (n.right != null) {
           n.right.left = n;
        }
    }

	

    public Iterator<T> iterator() {
        return new MyIterator<T>(this);
    }

	

    public void print(PrintStream out) {
        for (Iterator<T> eIterator = iterator(); 
		eIterator.hasNext();) {
            T e = eIterator.next();
            out.println(e);
        }
    }

	

     private void  delete__wrappee__base  (MyNode n) {
    }

	

    public void delete(MyNode n) {
    	delete__wrappee__base(n);
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

	
    
    /*
     * Use for JUnit testing
     */
    public MyNode<T> getHead()
    {
    	return head;
    }


}
