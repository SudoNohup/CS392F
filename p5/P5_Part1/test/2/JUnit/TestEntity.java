package JUnit; 

import static org.junit.Assert.*; 

import org.junit.Before; 
import org.junit.Test; 
import LApp.Entity; 

public  class  TestEntity {
	
	
	Entity entity;

	

	@Before
	public void setUp() throws Exception {
		entity=new Entity("Someone",50);
	}

	

	@Test
	public void test() {
		assertEquals("(Someone, 50)",entity.toString());
	}


}
