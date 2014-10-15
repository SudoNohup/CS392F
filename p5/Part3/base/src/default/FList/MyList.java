package FList; 

import java.io.PrintStream; 

import FList.MyNode; 
import java.util.Iterator; 

public  class  MyList <T>  implements Iterable<T> {
	

    MyNode<T> head;

	
    MyNode<T> tail;

	

    public MyList() {
        head = null;
        tail = null;
    }

	

    public void insert(T elem) {
        insert(new MyNode<T>(elem));
    }

	

    void insert(MyNode<T> n) {
        n.right = head;
        head = n;
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

	

    public void delete(MyNode<T> n) {
    }

	
    
    /*
     * Use for JUnit testing
     */
    public MyNode<T> getHead()
    {
    	return head;
    }


}
