/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import hashJoins.BloomSimulator;
import hashJoins.PrintMap;
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
public class TestBloomSimulator {
    
    public TestBloomSimulator() {
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
    public void testBloomSimulator()
    {
        Connector bloom_print = new Connector("bloom_print");
        BloomSimulator bloomSimulator = new BloomSimulator(bloom_print, "test/test_table");
        bloomSimulator.start();
        //ReadEnd readEnd = bloom_print.getReadEnd();
        //System.out.println(readEnd.getNextString());
        PrintMap pM = new PrintMap(bloom_print);
        RegTest.Utility.redirectStdOut("input/test/testBloomSimulator.txt");
        pM.start();
        while(pM.isAlive())
        {
            
        }
        RegTest.Utility.validate("input/test/testBloomSimulator.txt", "input/test/testBloomSimulatorResult.txt", true);
    }
}
