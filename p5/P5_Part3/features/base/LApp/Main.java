package LApp;

import java.util.Iterator;
import FList.*;

public class Main {

    static MyList<Entity> mylist;

    public static void main(String[] args) {
        // Step 1: initialize list
        mylist = new MyList<Entity>();

        addArray(mylist, Entity.entArray1);

        // Step 2: print list out
        System.out.println("original list");
        mylist.print(System.out);

        // Step 3: add more entities
        addArray(mylist, Entity.entArray2);

        // Step 4: print list out
        System.out.println("augmented list");
        mylist.print(System.out);
        
        reviseList();

    }
    
    private static void reviseList()
    {
    }

    public static void addArray(MyList<Entity> l, Entity[] arr) {
        for (int j = 0; j < arr.length; j++) {
            l.insert(arr[j]);
        }
    }
}
