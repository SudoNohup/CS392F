package FList;


public class MyNode<T> {

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
}
