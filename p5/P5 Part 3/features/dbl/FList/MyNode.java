package FList;

import LApp.Entity;

public class MyNode<T> {

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
