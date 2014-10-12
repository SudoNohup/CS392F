package JUnit; 

import static org.junit.Assert.*; 

import org.junit.Test; 
import org.junit.Before; 
import FList.*; 

public   class  TestMyList {
	
  

	MyList<Integer> myList;

	
	
	@Before
	public void setUp() throws Exception {
		myList=new MyList<Integer>();
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
		myList.insert(new Integer(50));
		MyNode<Integer> head=myList.getHead();
		assertEquals(new Integer(50),head.getElem());
		myList.insert(new Integer(100));
		head=myList.getHead();
		assertEquals(new Integer(100),head.getElem());
	}

	
	
	@Test
	public void testIterator()
	{
		MyIterator<Integer> it=(MyIterator<Integer>)myList.iterator();
		assertNotNull(it);
	}

	
  

	@Test
	public void testDetele()
	{
		myList.insert(new Integer(50));
		myList.insert(new Integer(100));
		myList.insert(new Integer(80));
		myList.delete(myList.getHead().getRight());
		
		assertEquals(new Integer(80),myList.getHead().getElem());
		assertEquals(new Integer(50),myList.getHead().getRight().getElem());
		
		myList.delete(myList.getHead());
		assertEquals(new Integer(50),myList.getHead().getElem());
	}


}
