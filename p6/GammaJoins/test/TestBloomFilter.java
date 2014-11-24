/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import basicConnector.ReadEnd;
import gammaSupport.Tuple;
import hashJoins.BFilter;
import hashJoins.Bloom;
import hashJoins.HSplit;
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
public class TestBloomFilter {
    
    ReadRelation rr1,rr2;
    Connector relation_split1,relation_split2;
    Connector split_bloom10,split_bloom11,split_bloom12,split_bloom13;
    Connector bloom_filter0,bloom_filter1,bloom_filter2,bloom_filter3;
    Connector bloom_join0,bloom_join1,bloom_join2,bloom_join3;
    Connector split_filter0,split_filter1,split_filter2,split_filter3;
    Connector test_filter0,test_filter1,test_filter2,test_filter3;
    HSplit split1,split2;
    Bloom bloom0,bloom1,bloom2,bloom3;
    BFilter filter0,filter1,filter2,filter3;
    
    
    public TestBloomFilter() {
        
    }
    
    
    @Before
    public void setUp() {
        relation_split1 = new Connector("relation_split1");
        split_bloom10 = new Connector("split_bloom10");
        split_bloom11 = new Connector("split_bloom11");
        split_bloom12 = new Connector("split_bloom12");
        split_bloom13 = new Connector("split_bloom13");
        
        rr1=new ReadRelation("test/test_table",relation_split1);
        split1 = new HSplit(0, relation_split1, split_bloom10, split_bloom11, split_bloom12, split_bloom13);
        
        relation_split2 = new Connector("relation_split2");
        split_filter0 = new Connector("split_gilter0");
        split_filter1 = new Connector("split_filter1");
        split_filter2 = new Connector("split_filter2");
        split_filter3 = new Connector("split_filter3");
        
        rr2=new ReadRelation("test/test_table1",relation_split2);
        split2 = new HSplit(1, relation_split2, split_filter0, split_filter1, split_filter2, split_filter3);
        
        bloom_filter0=new Connector("bloom_filter0");
        bloom_filter1=new Connector("bloom_filter1");
        bloom_filter2=new Connector("bloom_filter2");
        bloom_filter3=new Connector("bloom_filter3");
        bloom_join0=new Connector("bloom_join0");
        bloom_join1=new Connector("bloom_join1");
        bloom_join2=new Connector("bloom_join2");
        bloom_join3=new Connector("bloom_join3");
        
        bloom0=new Bloom(split_bloom10,bloom_join0,bloom_filter0,0);
        bloom1=new Bloom(split_bloom11,bloom_join1,bloom_filter1,0);
        bloom2=new Bloom(split_bloom12,bloom_join2,bloom_filter2,0);
        bloom3=new Bloom(split_bloom13,bloom_join3,bloom_filter3,0);
        
        test_filter0=new Connector("test_filter0");
        test_filter1=new Connector("test_filter1");
        test_filter2=new Connector("test_filter2");
        test_filter3=new Connector("test_filter3");
        
        filter0=new BFilter(bloom_filter0,split_filter0,test_filter0,1);
        filter1=new BFilter(bloom_filter1,split_filter1,test_filter1,1);
        filter2=new BFilter(bloom_filter2,split_filter2,test_filter2,1);
        filter3=new BFilter(bloom_filter3,split_filter3,test_filter3,1);
        
        
        
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
    public void testFilter()
    {
        rr1.start();
        rr2.start();
        split1.start();
        split2.start();
        bloom0.start();
        bloom1.start();
        bloom2.start();
        bloom3.start();
        filter0.start();
        filter1.start();
        filter2.start();
        filter3.start();
        
        
        RegTest.Utility.redirectStdOut("input/test/testFilter.txt");
        
        Tuple tuple1,tuple2,tuple3,tuple4;
        ReadEnd in1 = test_filter0.getReadEnd();
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
        ReadEnd in2 = test_filter1.getReadEnd();
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
        
        ReadEnd in3 = test_filter2.getReadEnd();
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
            
         ReadEnd in4 = test_filter3.getReadEnd();
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
        
        
        RegTest.Utility.validate("input/test/testFilter.txt", "input/test/testFilterResult.txt", false);
        
        
    }
}
