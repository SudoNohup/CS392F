package JUnit; 

import static org.junit.Assert.*; 
import org.junit.Test; 
import org.junit.Before; 
import FList.*; 
import LApp.Entity; 


public   class  TestMyIterator {
	

	MyList myList;

	
	@Before
	public void setUp() throws Exception {
		myList=new MyList();
	}

	
	

	
	@Test
	public void testHasNext() {
		MyIterator it=(MyIterator)myList.iterator();
		assertFalse(it.hasNext());
		myList.insert(new Entity("Someone",50));
		it=(MyIterator)myList.iterator();
		assertTrue(it.hasNext());
	}

	
	
	@Test
	public void testNext()
	{
		myList.insert(new Entity("Someone",50));
		myList.insert(new Entity("Somebody",100));
		MyIterator it=(MyIterator)myList.iterator();
		assertEquals("(Somebody, 100)",it.next().toString());
		assertEquals("(Someone, 50)",it.next().toString());
		assertFalse(it.hasNext());
		
		
	}

	

	
	@Test
	public void testRemove()
	{

		myList.insert(new Entity("Someone",50));
		myList.insert(new Entity("Somebody",100));
		myList.insert(new Entity("ThirdParty",80));
		if (!myList.isOrdered()) {
			MyIterator it=(MyIterator)myList.iterator();
			it.next();
			assertEquals("(Somebody, 100)",it.next().toString());
			it.remove();
			it=(MyIterator)myList.iterator();
			assertEquals("(ThirdParty, 80)",it.next().toString());
			assertEquals("(Someone, 50)",it.next().toString());
		} else {
			MyIterator it=(MyIterator)myList.iterator();
			it.next();
			assertEquals("(Someone, 50)",it.next().toString());
			it.remove();
			it=(MyIterator)myList.iterator();
			assertEquals("(Somebody, 100)",it.next().toString());
			assertEquals("(ThirdParty, 80)",it.next().toString());
		}
			
	}


}
