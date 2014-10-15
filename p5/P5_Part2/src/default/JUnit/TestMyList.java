package JUnit; 

import static org.junit.Assert.*; 

import org.junit.Test; 
import org.junit.Before; 

import FList.*; 
import LApp.Entity; 

public   class  TestMyList {
	
  

	MyList myList;

	
	
	@Before
	public void setUp() throws Exception {
		myList=new MyList();
	}

	
	
	
	@Test
	public void testCreation()
	{
		//myList=new MyList();
		assertNotNull(myList);
	}

	
	
	@Test
	public void testInsert()
	{		
		myList.insert(new Entity("Someone",50));
		MyNode head=myList.getHead();
		assertEquals("(Someone, 50)",head.toString());
		myList.insert(new Entity("Somebody",100));
		head=myList.getHead();
		assertEquals("(Somebody, 100)",head.toString());
	}

	
	
	@Test
	public void testIterator()
	{
		MyIterator it=(MyIterator)myList.iterator();
		assertNotNull(it);
	}

	
  

	@Test
	public void testDetele()
	{
		myList.insert(new Entity("Someone",50));
		myList.insert(new Entity("Somebody",100));
		myList.insert(new Entity("ThirdParty",80));
		myList.delete(myList.getHead().getRight());
		
		
		if (!myList.isOrdered()) {
			MyIterator it=(MyIterator)myList.iterator();
			assertEquals("(ThirdParty, 80)",it.next().toString());
			assertEquals("(Someone, 50)",it.next().toString());

			myList.delete(myList.getHead());
			assertEquals("(Someone, 50)",myList.getHead().toString());
		} else {
			MyIterator it=(MyIterator)myList.iterator();
			assertEquals("(Somebody, 100)",it.next().toString());
			assertEquals("(ThirdParty, 80)",it.next().toString());

			myList.delete(myList.getHead());
			assertEquals("(ThirdParty, 80)",myList.getHead().toString());
		}
		
		

	}


}
