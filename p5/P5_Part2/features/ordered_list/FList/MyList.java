package FList;

import java.io.PrintStream;

import FList.MyIterator;
import LApp.Entity;
import LApp.EntityComparator; 

import java.util.Iterator;

public class MyList implements Iterable {

	
	/* We use iteration to ensure that the new node is inserted into the right position for both doubly-linked and singly-linked lists */
    void insert(MyNode n) {
    	
        MyIterator eIterator = iterator();
        
        if (!eIterator.hasNext()) {
            n.right = head;
            head = n;
            
            addLeftLink(n, null);
            
            return;
        }
        Entity e = (Entity)eIterator.next();
        
        EntityComparator Ecmp = new EntityComparator();
        
       
        if (Ecmp.compare(n.elem, e) < 0) {
            n.right = head;
            head = n;
            
            addLeftLink(n, null);
            
            return;
        }
        
        
        MyNode preNode = eIterator.currnode();
        for (; eIterator.hasNext();) {
        	e = (Entity)eIterator.next();
        	if (Ecmp.compare(n.elem, e) < 0) {
        		n.right = eIterator.currnode();
        		preNode.right = n;
		
        		addLeftLink(n, preNode);
        		return;
        	}
        	//preIterator = eIterator;
        	preNode = eIterator.currnode();
        }
        
        eIterator.currnode().right = n;
        //n.right = null;
        
        
        addLeftLink(n, preNode);
                
        return;
        
    }
    

    /*
     * Use for JUnit testing
     */
    
    public boolean isOrdered()
    {
    	return true;
    }

   
}
