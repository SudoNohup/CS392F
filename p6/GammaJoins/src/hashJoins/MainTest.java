/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashJoins;


import basicConnector.*;
import gammaSupport.*;
import gamma.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;



/**
 *
 * @author Jianyu
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        /*
        // TODO code application logic here
       jointest();
        //System.out.println("Come into main()");
        testBloomSimulator();

        gammaTest("client","viewing");
        
        testJoinMapReduce_ClientXViewing();
        */
        
        //testMapReduceBloom();
        testJoinMapReduce_ClientXViewing();
    }

    public static void testMapReduceBloom() throws Exception {
        System.out.println("Starting Map Reduce Bloom Test");
        Connector print_bloom = new Connector("print_bloom");
        ReadRelation r = new ReadRelation("client", print_bloom);
        Connector bloom_join = new Connector("bloom_join");
        //Print p1 = new Print(bloom_join);
        Connector bloom_bmap = new Connector("bloom_bmap");

        MapReduceBloom mapReduceBloom = new MapReduceBloom(print_bloom, bloom_join, bloom_bmap, 0);
        r.start();
        mapReduceBloom.start();
        //p1.start();
        
        //We should use printM to print BMap....
        PrintMap pM = new PrintMap(bloom_bmap);
        pM.start();
    }

    public static void testBloomSimulator() throws Exception {
        Connector bloom_print = new Connector("bloom_print");
        BloomSimulator bloomSimulator = new BloomSimulator(bloom_print, "client");
        ReadEnd readEnd = bloom_print.getReadEnd();
        bloomSimulator.start();
        System.out.println(readEnd.getNextString());
    }
    
    
    public static void gammaTest(String file1, String file2) {
        //System.out.println("Starting Gamma Test...");
        Connector out = new Connector("output");
        System.out.println("invoke Gamma....");
        Gamma h = new Gamma(0, file1, 0, file2, out);
        h.start();

        //System.out.println(out.getRelation());
        Print p = new Print(out);
        
        p.start();

    }

    public static void testJoinMapReduce_ClientXViewing() {
        //System.out.println("Starting test MapReduce hJoin..._ClientXViewing");

        Connector out2 = new Connector("output2");
        MapReduceHJoin mapJ = new MapReduceHJoin(0, "client", 0, "viewing", out2);
        mapJ.start();
        //System.out.println(out2.getRelation());
        Print p2 = new Print(out2);
        p2.start();

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
        Print p = new Print(o);
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
    
      public static void FileSort(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            ArrayList<String> list = new ArrayList<String>();
            String line = "";
            while((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
            FileWriter writer = new FileWriter(fileName);
            Collections.sort(list);
            for(String val : list){
                writer.write(val);	
                writer.write('\n');
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
