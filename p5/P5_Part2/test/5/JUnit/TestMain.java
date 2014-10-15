package JUnit; 

import static org.junit.Assert.*; 

import org.junit.Before; 
import org.junit.Test; 

import FList.*; 
import LApp.*; 


public  class  TestMain {
	

	MyList mylist;

	
    public static void addArray(MyList l, Entity[] arr) {
        for (int j = 0; j < arr.length; j++) {
            l.insert(arr[j]);
        }
    }

	
		
	@Before
	public void setUp() throws Exception {
		mylist=new MyList();
		
	}

	
	
	@Test
	public void testMain() {
		
		addArray(mylist, Entity.entArray1);
		
		MyIterator it=(MyIterator)mylist.iterator();
		assertEquals("(Chili, 20)",it.next().toString());
		assertEquals("(Beth, 22)",it.next().toString());
		assertEquals("(Scarlett, 7)",it.next().toString());
		assertEquals("(Chief, 3)",it.next().toString());
		assertEquals("(Steve, 90)",it.next().toString());
		assertEquals("(Don, 60)",it.next().toString());
		//assertNull(it.next());
		

		// add more entities
		addArray(mylist, Entity.entArray2);
		it=(MyIterator)mylist.iterator();
		assertEquals("(Kelsey, 25)",it.next().toString());
		assertEquals("(Haggis, 1)",it.next().toString());
		assertEquals("(Chili, 20)",it.next().toString());
		assertEquals("(Beth, 22)",it.next().toString());
		assertEquals("(Scarlett, 7)",it.next().toString());
		assertEquals("(Chief, 3)",it.next().toString());
		assertEquals("(Steve, 90)",it.next().toString());
		assertEquals("(Don, 60)",it.next().toString());
		

	}


}
