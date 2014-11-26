/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import gamma.Gamma;
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
public class TestGamma {
    
    public TestGamma() {
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
    public void testClientXViewing()
    {
        Connector out = new Connector("output");
        Gamma h = new Gamma(0, "client", 0, "viewing", out);
        h.start();
        Print p = new Print(out);
        
        RegTest.Utility.redirectStdOut("input/test/testGammaClientXViewing.txt");
        p.start();
        while(p.isAlive())
        {
            
        }
        RegTest.Utility.validate("input/test/testGammaClientXViewing.txt", "input/test/testGammaClientXViewingResult.txt", true);
    }
    
    @Test
    public void testOrdersXOdetails()
    {
        Connector out = new Connector("output");
        Gamma h = new Gamma(0, "orders", 0, "odetails", out);
        h.start();
        Print p = new Print(out);
        
        RegTest.Utility.redirectStdOut("input/test/testOrdersXOdetails.txt");
        p.start();
        while(p.isAlive())
        {
            
        }
        RegTest.Utility.validate("input/test/testOrdersXOdetails.txt", "input/test/testOrdersXOdetailsResult.txt", true);
    }
    
    @Test
    public void testPartsXOdetails()
    {
        Connector out = new Connector("output");
        Gamma h = new Gamma(0, "parts", 1, "odetails", out);
        h.start();
        Print p = new Print(out);
        
        RegTest.Utility.redirectStdOut("input/test/testPartsXOdetails.txt");
        p.start();
        while(p.isAlive())
        {
            
        }
        RegTest.Utility.validate("input/test/testPartsXOdetails.txt", "input/test/testPartsXOdetailsResult.txt", true);
    }
}
