package JUnit;
import org.junit.runner.*;

import LApp.Main;



public class TestWrapper {

	private static boolean remove=true;
	
	private static void testMain()
	{
		
		String args[]=null;
		Main.main(args);
		RegTest.Utility.validate("out.txt", "expected2.txt", false);
		
	}
}
