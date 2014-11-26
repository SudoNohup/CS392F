/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basicConnector.Connector;
import basicConnector.ReadEnd;
import gammaSupport.Relation;
import gammaSupport.Tuple;
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
public class TestReadRelation {
    
    Connector test_relation;
    ReadRelation rr;
    
    public TestReadRelation() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        test_relation=new Connector("test_relation");
        rr=new ReadRelation("test/test_table",test_relation);
        rr.start();
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
    public void testRead()
    {
        RegTest.Utility.redirectStdOut("input/test/testReadRelation.txt");
        
        
        
        while(test_relation.getReadEnd().getRelation()==null)
        {
            
        }
        Relation relation=test_relation.getReadEnd().getRelation();
        System.out.println(relation);
        
        Tuple tuple;
        ReadEnd in=test_relation.getReadEnd();
        try{
            while (true) {
                tuple = in.getNextTuple();
                if (tuple == null || tuple.toString().equals("1#null#")) {
                    break;
                }
                System.out.println(tuple);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        RegTest.Utility.validate("input/test/testReadRelation.txt", "input/test/testReadRelationResult.txt", false);
    }
   
    
}
