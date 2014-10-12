package LApp;

import java.util.Iterator;
import FList.*;

public class Main {

    static MyList mylist;

    public static void main(String[] args) {
        // Step 1: initialize list
        mylist = new MyList();

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

    public static void addArray(MyList l, Entity[] arr) {
        for (int j = 0; j < arr.length; j++) {
            l.insert(arr[j]);
        }
    }
}
