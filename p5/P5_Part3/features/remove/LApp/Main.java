package LApp;

import java.util.Iterator;
import FList.*;

public class Main {

    private static void reviseList()
    {
    	 // Step 5: remove added nodes
        Entity[] ent = Entity.entArray2;
        for (Iterator<Entity> i = mylist.iterator(); 
	       i.hasNext();) {
            Entity x = (Entity) i.next();
            for (int j = 0; j < ent.length; j++) {
                if (ent[j] == x) {
                    i.remove();
                    break;
                }
            }
        }

        // Step 6: print remaining nodes
        System.out.println("revised list");
        mylist.print(System.out);
    }
}
