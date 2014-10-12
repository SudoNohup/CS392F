package JUnit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import FList.*;
import LApp.Entity;

public class TestMyList{
  

	@Test
	public void testDetele()
	{
		myList.insert(new Entity("Someone",50));
		myList.insert(new Entity("Somebody",100));
		myList.insert(new Entity("ThirdParty",80));
		myList.delete(myList.getHead().getRight());
		
		assertEquals("(ThirdParty, 80)",myList.getHead().toString());
		assertEquals("(Someone, 50)",myList.getHead().getRight().toString());
		
		myList.delete(myList.getHead());
		assertEquals("(Someone, 50)",myList.getHead().toString());
	}

}
