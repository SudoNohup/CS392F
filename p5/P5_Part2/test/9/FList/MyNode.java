package FList; 

import LApp.Entity; 

public   class  MyNode {
	

    Entity elem;

	
    MyNode right;

	
	
    public MyNode  (Entity elem) {
        this.elem = elem;
        right = null;
    
        this.delete_flag = false;
    }

	

    public String toString() {
        return elem.toString();
    }

	
    
    /*
     * The following method is for testing
     */
    
    public MyNode getRight()
    {
    	return right;
    }

	
	
	boolean delete_flag;


}
