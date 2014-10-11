package FList; 

import LApp.Entity; 

public   class  MyNode {
	

    Object elem;

	
    MyNode right;

	

    public MyNode(Object elem) {
        this.elem = elem;
        right = null;
    }

	

    public String toString() {
        return elem.toString();
    }

	

    MyNode left;

	

    public MyNode(Entity elem) {
        left=null;
    }


}
