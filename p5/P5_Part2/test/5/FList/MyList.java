package FList; 

import java.io.PrintStream; 

import LApp.Entity; 

import java.util.Iterator; 

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

	
	
    void insert(MyNode n)
    {
    	insert__wrappee__base(n);
    	n.left = null;
        if (n.right != null) {
           n.right.left = n;
        }
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
    	//delete__wrappee__base(n);
    	n.delete_flag = true;
    	

    	//Update the head if n is the head
    	if (n == head) {
    		while (n.right != null) {
    			if (n.right.delete_flag == false) {
    				head = n.right;
    				return;
    			}
    			n = n.right;
    		}
    		
    		//n.right is null.
    		head = null;
    		return;
    	}
    	
    }

	
    
    public boolean isOrdered()
    {
    	return false;
    }


}
