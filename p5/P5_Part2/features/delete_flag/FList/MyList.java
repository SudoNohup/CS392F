package FList;

import java.io.PrintStream;

import LApp.Entity;

import java.util.Iterator;

public class MyList implements Iterable {


    public void delete(MyNode n) {
    	//original(n);
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
    

}
