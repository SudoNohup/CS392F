package JUnit;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import FList.*;

public class TestMyNode {
	
	MyList<Integer> myList;
	MyNode<Integer> node;
	
	@Before
	public void setUp() throws Exception {
		initialization();		
	}
	
	private void initialization()
	{
		myList=new MyList<Integer>();
		myList.insert(new Integer(100));
		myList.insert(new Integer(50));
		node=myList.getHead();
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

}
