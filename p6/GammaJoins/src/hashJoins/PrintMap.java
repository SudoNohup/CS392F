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
 * @author Jianyu
 */
public class PrintMap extends Thread {
    
    ReadEnd in;
    
    public PrintMap(Connector c_in) {
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
            String input;
            input = in.getNextString();      
            System.out.println(input);

            System.out.flush();
        } catch(Exception e) {
            ReportError.msg(this.getClass().getName() + e.getMessage());
        }
    }
    
}
