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
public class DoNothing extends Thread {
    Connector c_in, c_out;
    public DoNothing(Connector c_in, Connector c_out) {
        this.c_in = c_in;
        this.c_out = c_out;
    }
    
    public void run() {
        try {
            c_out = c_in;
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
    }    

}
