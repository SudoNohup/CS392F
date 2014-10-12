package JUnit;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import FList.*;

public class TestMyNode {
	
	MyNode<Integer> middle;
	

	private void initialization()
	{
		original();
		myList.insert(new Integer(80));
		middle=myList.getHead().getRight();
	}

	
	
	@Test
	public void testLeft()
	{
		assertEquals(new Integer(80),middle.getLeft().getElem());
		assertEquals(new Integer(50),middle.getRight().getLeft().getElem());
	}

}
