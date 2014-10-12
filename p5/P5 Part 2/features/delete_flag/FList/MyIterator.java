package FList;

import java.util.Iterator;

import LApp.Entity;

public class MyIterator implements Iterator {

    public boolean hasNext() {
    	
        if (alreadyAdvanced) {
        	
        	while(current != null) {
        		if (current.delete_flag == false) return true;
        		current = current.right;
        	}
        	
        	return false;
        	
            //return current != null && !(current.delete_flag);
            
        } else {
        	
        	while(current.right != null) {
        		if (current.right.delete_flag == false) return true;
        		current = current.right;
        	}
        	return false;
            //return current.right != null && !(current.right.delete_flag);
            
        }
    }

    public Object next() {
        if (alreadyAdvanced) {
            alreadyAdvanced = false;     
            while (current.delete_flag)
            	current = current.right;  
        } else {
        	current = current.right;
            while(current.delete_flag) {
            	current = current.right;
            }
        }
        return current.elem;
    }

    public void remove() {
        MyNode newCurrent;
        newCurrent = current.right;
        list.delete(current);
        current = newCurrent;
        alreadyAdvanced = true;
    }
}
