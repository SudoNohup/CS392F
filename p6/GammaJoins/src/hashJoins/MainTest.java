/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashJoins;


import basicConnector.*;
import gammaSupport.*;
import gamma.*;



/**
 *
 * @author Jianyu
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //jointest();
        System.out.println("Come into main()");
        //testBloomSimulator();

        gammaTest("client","viewing");
    }

 
    
    public static void testBloomSimulator() throws Exception {
        Connector bloom_print = new Connector("bloom_print");
        BloomSimulator bloomSimulator = new BloomSimulator(bloom_print, "client");
        ReadEnd readEnd = bloom_print.getReadEnd();
        bloomSimulator.start();
        System.out.println(readEnd.getNextString());
    }
    
    
    public static void gammaTest(String file1, String file2) {
        System.out.println("Starting Gamma Test...");
        Connector out = new Connector("output");
        Gamma h = new Gamma(0, file1, 0, file2, out);
        Print p = new Print(out.getReadEnd());
        h.start();
        p.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
       public static void join(String r1name, String r2name, int jk1, int jk2) throws Exception {
        System.out.println("Joining " + r1name + " with " + r2name);

        ThreadList.init();
        Connector c1 = new Connector("input1");
        //ReadRelation r1 = new ReadRelation(r1name, c1.getWriteEnd());
        
        ReadRelation r1 = new ReadRelation(r1name, c1);
        Connector c2 = new Connector("input2");
        //ReadRelation r2 = new ReadRelation(r2name, c2.getWriteEnd());
        ReadRelation r2 = new ReadRelation(r2name, c2);
        Connector o = new Connector("output");

        
        
        //db should has its correct name......
        /*
         String[] args = null;
         Connector read_A = new Connector("input1");
         ReadRelation r1 = new ReadRelation("input/"+input1, "clientDB", read_A.getWriteEnd());
         Connector read_B = new Connector("input2");
         ReadRelation r2 = new ReadRelation("input/odetails.txt", "clientDB", read_B.getWriteEnd());
         Connector out = new Connector("output");
         HJoin h = new HJoin(0, read_A.getReadEnd(), 1,read_B.getReadEnd(),out.getWriteEnd());
         */
        
        //HJoin hj = new HJoin(c1.getReadEnd(), c2.getReadEnd(), jk1, jk2, o.getWriteEnd());
        HJoin hj = new HJoin(c1, c2, jk1, jk2, o);
        Print p = new Print(o.getReadEnd());
        r1.start();
        r2.start();
        hj.start();
        p.start();
        //ThreadList.run(p);
    }

    public static void jointest() throws Exception {

        join("parts", "odetails", 0, 1);
        /*
         join("client", "viewing", 0, 0);
         join("orders", "odetails", 0, 0);
         */

    }
}
