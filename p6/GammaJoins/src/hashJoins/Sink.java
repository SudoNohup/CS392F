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
public class Sink extends Thread {
    
    ReadEnd in;
    
    public Sink(Connector c_in) {
        this.in = c_in.getReadEnd();
    }
    
    public void run() {
        try {
            Tuple tuple;
            while (true) {
                tuple = in.getNextTuple();
                if (tuple == null || tuple.toString().equals("1#null#")) {
                    break;
                }
            //DO NOTHING...
            }
            System.out.println();
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }

    }    
}
