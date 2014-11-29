/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import gamma.MapReduceBFilter;
import gamma.MapReduceBloom;
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
public class TestMapReduceFilter {
    
    public TestMapReduceFilter() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
    public void testMapReduceFilter()
    {
        Connector relation_bloom=new Connector("relation_bloom");
        Connector bloom_filter=new Connector("bloom_filter");
        Connector bloom_join=new Connector("bloom_join");
        Connector relation_filter=new Connector("relation_bloom");
        Connector test_filter=new Connector("test_filter");
        
        ReadRelation rr1=new ReadRelation("test/test_table",relation_bloom);
        ReadRelation rr2=new ReadRelation("test/test_table1",relation_filter);
        
        MapReduceBloom bloom=new MapReduceBloom(relation_bloom,bloom_join,bloom_filter,0);
        
        MapReduceBFilter filter=new MapReduceBFilter(bloom_filter,relation_filter,test_filter,0);
        
        Print p=new Print(test_filter);
        
        rr1.start();
        rr2.start();
        
        bloom.start();
        filter.start();
        
        RegTest.Utility.redirectStdOut("input/test/testMapReduceFilter.txt");
        p.start();
        while(p.isAlive())
        {
            
        }
        RegTest.Utility.validate("input/test/testMapReduceFilter.txt", "input/test/testMapReduceFilterResult.txt", true);
        
    }
}
