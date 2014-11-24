/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashJoins;

import java.io.*;
import basicConnector.*;
import gammaSupport.*;

/**
 *
 * @author Jianyu, Xiaohui
 */
public class ReadRelation extends Thread{
    String db;
    public BufferedReader in;
    public WriteEnd out;
    
    /*
    public ReadRelation (String fileName, WriteEnd out) {
        System.out.println("Come into ReadRelation!");
        try {
            this.db = fileName;
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
        this.out = out;
        ThreadList.add(this);
    }
    */
    
    public ReadRelation (String db, Connector c) {
        String fileName = "input/" + db + ".txt";
        WriteEnd out = c.getWriteEnd();
        //System.out.println("Come into ReadRelation!");
        try {
            this.db = db;
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
        this.out = out;
        //ThreadList.add(this);
    }
    
    /*
    public ReadRelation (String fileName, String dbName, WriteEnd out) {
        try {
            this.db = db;
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            
        } catch (Exception e) {
            ReportError.msg(e.getMessage());
        }
        this.out = out;
    }
    */
    
    public void run() {
        //System.out.println("Come into run() of ReadRelation!");
        try {
            String input;
            int line = 0;
            while (true) {
                input = in.readLine();
                line++;
                if (input == null) {
                    break;
                }
                String[] line_split = input.split("\\s+");
                if (line == 1) {
                    Relation relation = new Relation(db, line_split.length);
                    for (int i = 0; i < line_split.length; ++i) {
                        relation.addField(line_split[i]);
                    }
                    out.setRelation(relation);
                } else if (line == 2) {
                    //this line is "------------"
                    continue;
                } else {
                    Tuple tuple = new Tuple(line_split.length);
                    for (int i = 0; i < line_split.length; ++i) {
                        tuple.set(i, line_split[i]);
                    }
                    out.putNextTuple(tuple);
                }
            }
            out.close();
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
    }
    
    
    
}
