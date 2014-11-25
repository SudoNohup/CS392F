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
public class BFilter extends Thread {
    ReadEnd inM;
    ReadEnd in;
    WriteEnd out;
    int keyNumber;
    
    public BFilter(Connector c_inM, Connector c_in, Connector c_out, int keyNumber) {
        ReadEnd inM = c_inM.getReadEnd();
        ReadEnd in = c_in.getReadEnd();
        WriteEnd out = c_out.getWriteEnd();
        
        this.inM = inM;
        this.in = in;
        this.out = out;
        this.keyNumber = keyNumber;
    }
    
    public void run() {
        try {
            BMap bmap = BMap.makeBMap(inM.getNextString());
            while (true) {
                Tuple tuple = in.getNextTuple();
                if (tuple == null || tuple.toString().equals("1#null#")) {
                    break;
                }
                String key = tuple.get(keyNumber);
                if (bmap.getValue(key)) {
                    out.putNextTuple(tuple);
                }
                
            }
            out.setRelation(in.getRelation());
            out.close();
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
    }
    
}
