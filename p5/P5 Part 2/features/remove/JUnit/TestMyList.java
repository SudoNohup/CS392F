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
