package JUnit;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import FList.*;


public class TestMyIterator {

	
	@Test
	public void testRemove()
	{
		myList.insert(new Integer(50));
		myList.insert(new Integer(100));
		myList.insert(new Integer(80));
		MyIterator<Integer> it=(MyIterator<Integer>)myList.iterator();
		it.next();
		assertEquals(new Integer(100),it.next());
		it.remove();
		it=(MyIterator<Integer>)myList.iterator();
		assertEquals(new Integer(80),it.next());
		assertEquals(new Integer(50),it.next());
	}
	
	

}
