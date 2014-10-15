package JUnit;
import org.junit.runner.*;

import LApp.Main;



public class TestWrapper {


	
	private static void testMain()
	{
		
		String args[]=null;
		Main.main(args);
		if(remove)
		{
		    RegTest.Utility.validate("out.txt", "expected4.txt", false);
		}
		else
		{
			RegTest.Utility.validate("out.txt", "expected3.txt", false);
		}
		
	}
}
