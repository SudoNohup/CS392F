/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashJoins;

import basicConnector.ReadEnd;
import gammaSupport.Tuple;
import gammaSupport.ReportError;
import gammaSupport.ThreadList;

/**
 *
 * @author Jianyu, Xiaohui
 */
public class Print extends Thread{
    ReadEnd in;
    
    public Print(ReadEnd in) {
        this.in = in;
        ThreadList.add(this);
    }
    
    public void run() {
        try {
            Tuple inputTuple;
            inputTuple = in.getNextTuple();
            while (inputTuple != null) {
                System.out.println(inputTuple);
                inputTuple = in.getNextTuple();
            }
            System.out.println(in.getRelation());
            System.out.flush();
        } catch(Exception e) {
            ReportError.msg(this.getClass().getName() + e.getMessage());
        }
    }
    
    
}
