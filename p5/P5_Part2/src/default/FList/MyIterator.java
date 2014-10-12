package FList; 

import java.util.Iterator; 
import LApp.Entity; 

public   class  MyIterator  implements Iterator {
	

    MyNode current;

	
    MyList list;

	
    boolean alreadyAdvanced;

	

    MyIterator(MyList list) {
        current = list.head;
        this.list = list;
        alreadyAdvanced = true;
    }

	

    public boolean hasNext() {
        if (alreadyAdvanced) {
            return current != null;
        } else {
            return current.right != null;
        }
    }

	

    public Object next() {
        if (alreadyAdvanced) {
            alreadyAdvanced = false;
        } else {
            current = current.right;
        }
        return current.elem;
    }

	

    public void remove() {

    }

	

    public MyNode currnode() {
    	return this.current;
    }


}
