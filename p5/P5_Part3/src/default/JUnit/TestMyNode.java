package JUnit; 


import static org.junit.Assert.*; 

import org.junit.Before; 
import org.junit.Test; 
import FList.*; 

public   class  TestMyNode {
	
	
	MyList<Integer> myList;

	
	MyNode<Integer> node;

	
	
	@Before
	public void setUp() throws Exception {
		initialization();		
	}

	
	
	 private void  initialization__wrappee__base  ()
	{
		myList=new MyList<Integer>();
		myList.insert(new Integer(100));
		myList.insert(new Integer(50));
		node=myList.getHead();
	}

	
	

	private void initialization()
	{
		initialization__wrappee__base();
		myList.insert(new Integer(80));
		middle=myList.getHead().getRight();
	}

	

	@Test
	public void testCreation() { 		
		assertEquals(new Integer(50),node.getElem());
	}

	
	
	@Test
	public void testRight()
	{
		assertEquals(new Integer(100), node.getRight().getElem());
	}

	
	
	MyNode<Integer> middle;

	

	
	
	@Test
	public void testLeft()
	{
		assertEquals(new Integer(80),middle.getLeft().getElem());
		assertEquals(new Integer(50),middle.getRight().getLeft().getElem());
	}


}
