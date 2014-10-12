package JUnit; 


import static org.junit.Assert.*; 

import org.junit.Before; 
import org.junit.Test; 
import FList.*; 
import LApp.Entity; 

public   class  TestMyNode {
	
	
	MyList myList;

	
	MyNode node;

	
	
	@Before
	public void setUp() throws Exception {
		initialization();		
	}

	
	
	 private void  initialization__wrappee__base  ()
	{
		myList=new MyList();
		myList.insert(new Entity("Somebody",100));
		myList.insert(new Entity("Someone",50));
		node=myList.getHead();
	}

	
	

	private void initialization()
	{
		initialization__wrappee__base();
		myList.insert(new Entity("ThirdParty",80));
		middle=myList.getHead().getRight();
	}

	

	@Test
	public void testCreation() {
		if (!myList.isOrdered()) {
			assertEquals("(Someone, 50)",node.toString());
		} else {
			assertEquals("(Somebody, 100)",node.toString());
		}
	}

	
	
	@Test
	public void testRight()
	{
		if (!myList.isOrdered()) {
			assertEquals("(Somebody, 100)", node.getRight().toString());
		} else {
		assertEquals("(Someone, 50)", node.getRight().toString());
		}
	}

	
	
	MyNode middle;

	

	
	@Test
	public void testLeft()
	{
		if (!myList.isOrdered()) {
			assertEquals("(ThirdParty, 80)",middle.getLeft().toString());
			assertEquals("(Someone, 50)",middle.getRight().getLeft().toString());
		} else {
			assertEquals("(Somebody, 100)",middle.getLeft().toString());
			assertEquals("(Someone, 50)",middle.getRight().getLeft().toString());
		}
	}


}
