/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import basicConnector.*;
import gammaSupport.*;
import hashJoins.*;


import RegTest.*;

/**
 *
 * @author Jianyu
 */
public class UnitTest {

    public UnitTest() {
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
    public void join(String r1name, String r2name, int jk1, int jk2) throws Exception {
        System.out.println("Joining " + r1name + " with " + r2name);

        ThreadList.init();
        Connector c1 = new Connector("input1");
        ReadRelation r1 = new ReadRelation(r1name, c1.getWriteEnd());
        Connector c2 = new Connector("input2");
        ReadRelation r2 = new ReadRelation(r2name, c2.getWriteEnd());
        Connector o = new Connector("output");
        
        /*
        String[] args = null;
        Connector read_A = new Connector("input1");
        ReadRelation r1 = new ReadRelation("input/"+input1, "clientDB", read_A.getWriteEnd());
        Connector read_B = new Connector("input2");
        ReadRelation r2 = new ReadRelation("input/odetails.txt", "clientDB", read_B.getWriteEnd());
        Connector out = new Connector("output");
        HJoin h = new HJoin(0, read_A.getReadEnd(), 1,read_B.getReadEnd(),out.getWriteEnd());
        */
        
        HJoin hj = new HJoin(c1.getReadEnd(), c2.getReadEnd(), jk1, jk2, o.getWriteEnd());
        Print p = new Print(o.getReadEnd());
        //ThreadList.run(p);
    }

    @Test
    public void jointest() throws Exception {
        Utility.redirectStdOut("input/out.txt");
        join("input/parts.txt", "input/odetails.txt", 0, 1);
        /*
        join("client", "viewing", 0, 0);
        join("orders", "odetails", 0, 0);
                */
        Utility.validate("out.txt", "input/out.txt",true);
    }

}
