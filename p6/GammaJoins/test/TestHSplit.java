/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import basicConnector.ReadEnd;
import gammaSupport.Tuple;
import hashJoins.HSplit;
import hashJoins.Print;
import hashJoins.ReadRelation;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Xiaohui
 */
public class TestHSplit {
    
    ReadRelation rr;
    Connector relation_split;
    Connector test_split0;
    Connector test_split1;
    Connector test_split2;
    Connector test_split3;
    HSplit split;
    
    public TestHSplit() {
    }
    
    @Before
    public void setUp() {
        relation_split=new Connector("relation_split");
        test_split0=new Connector("test_split0");
        test_split1=new Connector("test_split1");
        test_split2=new Connector("test_split2");
        test_split3=new Connector("test_split3");
        rr=new ReadRelation("test/test_table",relation_split);
        split=new HSplit(0,relation_split,test_split0,test_split1,test_split2,test_split3);
       
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testSplit()
    {   
        rr.start();
        split.start();
        RegTest.Utility.redirectStdOut("input/test/testSplit.txt");
        
        Tuple tuple1,tuple2,tuple3,tuple4;
        ReadEnd in1 = test_split0.getReadEnd();
            try {
                while (true) {
                    tuple1 = in1.getNextTuple();
                    if (tuple1 == null || tuple1.toString().equals("1#null#")) {
                        break;
                    }
                    System.out.println(tuple1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        ReadEnd in2 = test_split1.getReadEnd();
            try {
                while (true) {
                    tuple2 = in2.getNextTuple();
                    if (tuple2 == null || tuple2.toString().equals("1#null#")) {
                        break;
                    }
                    System.out.println(tuple2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        ReadEnd in3 = test_split2.getReadEnd();
            try {
                while (true) {
                    tuple3 = in3.getNextTuple();
                    if (tuple3 == null || tuple3.toString().equals("1#null#")) {
                        break;
                    }
                    System.out.println(tuple3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
         ReadEnd in4 = test_split3.getReadEnd();
            try {
                while (true) {
                    tuple4 = in4.getNextTuple();
                    if (tuple4 == null || tuple4.toString().equals("1#null#")) {
                        break;
                    }
                    System.out.println(tuple4);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        
        RegTest.Utility.validate("input/test/testSplit.txt", "input/test/testSplitResult.txt", false);
         
        
    }
    
}
