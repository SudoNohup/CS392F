package FList;


public class MyNode {

    Object elem;
    MyNode right;

    public MyNode(Object elem) {
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
    
}
