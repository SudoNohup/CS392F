package JUnit;
import org.junit.runner.*;

import LApp.Main;



public class TestWrapper {


	
	private static void testMain()
	{
		
		String args[]=null;
		Main.main(args);
		RegTest.Utility.validate("out.txt", "expected3.txt", false);
		
	}
}
