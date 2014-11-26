/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import gamma.MapReduceHJoin;
import hashJoins.Print;
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
public class TestMapReduceJoin {
    
    public TestMapReduceJoin() {
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
    public void testMapReduceJoin()
    {
        Connector out2 = new Connector("output2");
        MapReduceHJoin mapJ = new MapReduceHJoin(0, "client", 0, "viewing", out2);
        mapJ.start();
        //System.out.println(out2.getRelation());
        Print p = new Print(out2);
        RegTest.Utility.redirectStdOut("input/test/testMapReduceJoin.txt");
        p.start();
        while(p.isAlive())
        {
            
        }
        RegTest.Utility.validate("input/test/testMapReduceJoin.txt", "input/test/testMapReduceJoinResult.txt", true);
    }
    
}
