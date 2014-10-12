package FList; 

import LApp.Entity; 

public   class  MyNode <T> {
	

    T elem;

	
    MyNode right;

	

    public MyNode(T elem) {
        this.elem = elem;
        right = null;
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

	
    
    /*
     * The following method is for testing
     */
    public Object getElem()
    {
    	return elem;
    }

	

    MyNode<T> left;

	

    public MyNode(Entity elem) {
        left=null;
    }

	
    
    /*
     * This method is for JUnit testing
     */
    public MyNode getLeft()
    {
    	return left;
    }


}
