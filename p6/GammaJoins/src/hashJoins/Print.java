/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashJoins;

import basicConnector.*;
import gammaSupport.*;
/**
 *
 * @author Jianyu, Xiaohui
 */
public class Print extends Thread{
    ReadEnd in;
    
    public Print(Connector c_in) {
        ReadEnd in = c_in.getReadEnd();
        //System.out.println("Come into Print!");
        this.in = in;
        //ThreadList.add(this);
    }
    
    /*
    public Print(ReadEnd in) {
        //System.out.println("Come into Print!");
        this.in = in;
        //ThreadList.add(this);
    }
    */
    
    public void run() {
        //System.out.println("run() of Print!");
        try {
            while(in.getRelation()==null)
            {
                
            }
            System.out.println(in.getRelation());
            Tuple tuple;
            while (true) {
                tuple = in.getNextTuple();
                if (tuple == null || tuple.toString().equals("1#null#")) {
                    break;
                }
                System.out.println(tuple);
            }

            

            System.out.flush();
        } catch(Exception e) {
            ReportError.msg(this.getClass().getName() + e.getMessage());
        }
    }
    
    
}
