
import org.junit.runner.JUnitCore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xiaohui
 */
public class TestWrapper {
    public static void main(String[] args)
    {
        //RegTest.Utility.redirectStdOut("out.txt");
         
        System.out.println("TestBloomFilter:"+JUnitCore.runClasses(TestBloomFilter.class).wasSuccessful());
        
        System.out.println("TestGamma:"+JUnitCore.runClasses(TestGamma.class).wasSuccessful());
        System.out.println("TestHSplit:"+JUnitCore.runClasses(TestHSplit.class).wasSuccessful());
        System.out.println("TestJoinMerge:"+JUnitCore.runClasses(TestJoinMerge.class).wasSuccessful());
        System.out.println("TestJoinWBloom:"+JUnitCore.runClasses(TestJoinWBloom.class).wasSuccessful());
        System.out.println("TestMapReduceBloom:"+JUnitCore.runClasses(TestMapReduceBloom.class).wasSuccessful());
        System.out.println("TestMapReduceFilter:"+JUnitCore.runClasses(TestMapReduceFilter.class).wasSuccessful());
        System.out.println("TestMapReduceJoin:"+JUnitCore.runClasses(TestMapReduceJoin.class).wasSuccessful());
        System.out.println("TestReadRealtion:"+JUnitCore.runClasses(TestReadRelation.class).wasSuccessful());
        System.out.println("TestBloomSimulator:"+JUnitCore.runClasses(TestBloomSimulator.class).wasSuccessful());
    }
}
