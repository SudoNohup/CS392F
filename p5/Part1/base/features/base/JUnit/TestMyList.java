package JUnit;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import FList.*;
import LApp.Entity;

public class TestMyList{
  

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

}
