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
    
    public Print(ReadEnd in) {
        //System.out.println("Come into Print!");
        this.in = in;
        //ThreadList.add(this);
    }
    
    public void run() {
        //System.out.println("run() of Print!");
        try {
            Tuple inputTuple;
            inputTuple = in.getNextTuple();
            while (inputTuple != null) {
                System.out.println(inputTuple);
                inputTuple = in.getNextTuple();
            }
            //There is no in.close()....
            
            //Do we need to print the Relation?
            System.out.println(in.getRelation());
            System.out.flush();
        } catch(Exception e) {
            ReportError.msg(this.getClass().getName() + e.getMessage());
        }
    }
    
    
}
