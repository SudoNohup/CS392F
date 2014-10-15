package JUnit; 

import static org.junit.Assert.*; 
import org.junit.Test; 
import org.junit.Before; 
import FList.*; 


public  class  TestMyIterator {
	

	MyList<Integer> myList;

	
	@Before
	public void setUp() throws Exception {
		myList=new MyList<Integer>();
	}

	
	

	
	@Test
	public void testHasNext() {
		MyIterator<Integer> it=(MyIterator<Integer>)myList.iterator();
		assertFalse(it.hasNext());
		myList.insert(new Integer(50));
		it=(MyIterator<Integer>)myList.iterator();
		assertTrue(it.hasNext());
	}

	
	
	@Test
	public void testNext()
	{
		myList.insert(new Integer(50));
		myList.insert(new Integer(100));
		MyIterator<Integer> it=(MyIterator<Integer>)myList.iterator();
		assertEquals(new Integer(100),it.next());
		assertEquals(new Integer(50),it.next());
		assertFalse(it.hasNext());
		
		
	}


}
