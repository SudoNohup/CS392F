/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import gamma.MapReduceBloom;
import hashJoins.PrintMap;
import hashJoins.ReadRelation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xiaohui
 */
public class TestMapReduceBloom {
    
    public TestMapReduceBloom() {
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
    public void testReduceBloom()
    {
         Connector print_bloom = new Connector("print_bloom");
        ReadRelation r = new ReadRelation("test/test_table", print_bloom);
        Connector bloom_join = new Connector("bloom_join");
        //Print p1 = new Print(bloom_join);
        Connector bloom_bmap = new Connector("bloom_bmap");

        MapReduceBloom mapReduceBloom = new MapReduceBloom(print_bloom, bloom_join, bloom_bmap, 0);
        r.start();
        mapReduceBloom.start();
        //p1.start();
        
        //We should use printM to print BMap....
        PrintMap pM = new PrintMap(bloom_bmap);
        
        RegTest.Utility.redirectStdOut("input/test/testReduceBloom.txt");
        pM.start();
        while(pM.isAlive())
        {
            
        }
        RegTest.Utility.validate("input/test/testReduceBloom.txt", "input/test/testReduceBloomResult.txt", false);
    }
    
}
