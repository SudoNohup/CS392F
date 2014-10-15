package JUnit; 
import org.junit.runner.*; 

import LApp.Main; 



public   class  TestWrapper {
	

	private static boolean remove  =true;

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegTest.Utility.redirectStdOut("out.txt");
		System.out.println("TestEntity:"+JUnitCore.runClasses(TestEntity.class).wasSuccessful());
		System.out.println("TestMain:"+JUnitCore.runClasses(TestMain.class).wasSuccessful());
		System.out.println("TestMyIterator:"+JUnitCore.runClasses(TestMyIterator.class).wasSuccessful());
		System.out.println("TestMyList:"+JUnitCore.runClasses(TestMyList.class).wasSuccessful());
		System.out.println("TestMyNode:"+JUnitCore.runClasses(TestMyNode.class).wasSuccessful());
		testMain();
	}

	


	
	private static void testMain  ()
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
