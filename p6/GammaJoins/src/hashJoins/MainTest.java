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
 * @author Jianyu, Xiaohui
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        printJoins("client","viewing",0,0);
        printJoins("orders","odetails",0,0);
        printJoins("parts","odetails",0,1);

    }
    
    public static void printJoins(String table1, String table2,int key1,int key2)
    {
        System.out.println(table1+"X"+table2+":");
        System.out.flush();
        Connector out = new Connector("output");
        Gamma h = new Gamma(key1, table1, key2, table2, out);
        h.start();
        Print p = new Print(out);
        p.start();
        while(p.isAlive())
        {
            //loop until p finishes
        }
        System.out.println("-------------------------");
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
