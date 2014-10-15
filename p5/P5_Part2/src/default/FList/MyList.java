package FList; 

import java.io.PrintStream; 
import LApp.Entity;  

import java.util.Iterator; 

import FList.MyIterator; 
import LApp.EntityComparator; 

public   class  MyList  implements Iterable {
	

    MyNode head;

	
    MyNode tail;

	
    
    public MyList() {
        head = null;
        tail = null;
    }

	

    public void insert(Entity elem) {
        insert(new MyNode(elem));
    }

	

     private void  insert__wrappee__base  (MyNode n) {
        n.right = head;
        head = n;
    }

	

	
	/* We use iteration to ensure that the new node is inserted into the right position for both doubly-linked and singly-linked lists */
    void insert  (MyNode n) {
    	
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

	
    
    
    public void addLeftLink  (MyNode n, MyNode preNode) {
    	n.left = preNode;
    	if (n.right != null) {
    		n.right.left = n;
    	} else {
    		tail = n;
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
    
    public boolean isOrdered  ()
    {
    	return true;
    }


}
