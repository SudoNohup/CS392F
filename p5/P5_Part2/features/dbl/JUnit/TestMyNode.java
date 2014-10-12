package JUnit;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import FList.*;
import LApp.Entity;

public class TestMyNode {
	
	MyNode middle;
	

	private void initialization()
	{
		original();
		myList.insert(new Entity("ThirdParty",80));
		middle=myList.getHead().getRight();
	}

	
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
