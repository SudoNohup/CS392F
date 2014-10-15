package FList;


public class MyNode<T> {

    T elem;
    MyNode<T> right;

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
    
    public MyNode<T> getRight()
    {
    	return right;
    }
    
    /*
     * The following method is for testing
     */
    public T getElem()
    {
    	return elem;
    }
}
