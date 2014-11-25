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
import hashJoins.HJoin;
import hashJoins.HSplit;
import hashJoins.Merge;
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
public class TestJoinMerge {
    
    ReadRelation rr1,rr2;
    Connector relation_split1,relation_split2;
    Connector split_bloom10,split_bloom11,split_bloom12,split_bloom13;
    Connector bloom_filter0,bloom_filter1,bloom_filter2,bloom_filter3;
    Connector bloom_join0,bloom_join1,bloom_join2,bloom_join3;
    Connector split_filter0,split_filter1,split_filter2,split_filter3;
    Connector filter_join0,filter_join1,filter_join2,filter_join3;
    Connector join_merge0,join_merge1,join_merge2,join_merge3;
    Connector test_merge;
    HSplit split1,split2;
    Bloom bloom0,bloom1,bloom2,bloom3;
    BFilter filter0,filter1,filter2,filter3;
    HJoin join0,join1,join2,join3;
    Merge merge;
    
    public TestJoinMerge() {
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
        split2 = new HSplit(0, relation_split2, split_filter0, split_filter1, split_filter2, split_filter3);
        
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
        
        filter_join0=new Connector("filter_join0");
        filter_join1=new Connector("filter_join1");
        filter_join2=new Connector("filter_join2");
        filter_join3=new Connector("filter_join3");
        
        filter0=new BFilter(bloom_filter0,split_filter0,filter_join0,0);
        filter1=new BFilter(bloom_filter1,split_filter1,filter_join1,0);
        filter2=new BFilter(bloom_filter2,split_filter2,filter_join2,0);
        filter3=new BFilter(bloom_filter3,split_filter3,filter_join3,0);
        
        join_merge0 = new Connector("join_merge0");
        join_merge1 = new Connector("join_merge1");
        join_merge2 = new Connector("join_merge2");
        join_merge3 = new Connector("join_merge4");
        
        join0=new HJoin( bloom_join0,filter_join0,0,0,join_merge0);
        join1=new HJoin( bloom_join1,filter_join1,0,0,join_merge1);
        join2=new HJoin( bloom_join2,filter_join2,0,0,join_merge2);
        join3=new HJoin( bloom_join3,filter_join3,0,0,join_merge3);
        
        test_merge=new Connector("test_merge");
        merge=new Merge(test_merge,join_merge0,join_merge1,join_merge2,join_merge3);
        
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
        join0.start();
        join1.start();
        join2.start();
        join3.start();
        
        
        
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
    public void testMerge()
    {      
        merge.start();
        
        RegTest.Utility.redirectStdOut("input/test/testMerge.txt");

        Tuple tuple;
        ReadEnd in = test_merge.getReadEnd();
        try {
            while (true) {
                tuple = in.getNextTuple();
                if (tuple == null || tuple.toString().equals("1#null#")) {
                    break;
                }
                System.out.println(tuple);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        RegTest.Utility.validate("input/test/testMerge.txt", "input/test/testMergeResult.txt", true);
    }
}
