package JUnit; 


import static org.junit.Assert.*; 

import org.junit.Before; 
import org.junit.Test; 
import FList.*; 
import LApp.Entity; 

public  class  TestMyNode {
	
	
	MyList myList;

	
	MyNode node;

	
	
	@Before
	public void setUp() throws Exception {
		initialization();		
	}

	
	
	private void initialization()
	{
		myList=new MyList();
		myList.insert(new Entity("Somebody",100));
		myList.insert(new Entity("Someone",50));
		node=myList.getHead();
	}

	

	@Test
	public void testCreation() { 		
		assertEquals("(Someone, 50)",node.toString());
	}

	
	
	@Test
	public void testRight()
	{
		assertEquals("(Somebody, 100)", node.getRight().toString());
	}


}
