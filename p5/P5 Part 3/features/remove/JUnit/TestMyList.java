package JUnit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import FList.*;

public class TestMyList{
  

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
