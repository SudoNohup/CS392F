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
public class HSplit extends Thread {
    ReadEnd in;
    WriteEnd out[];
    int hashKey = 0;
    
    public HSplit (int hashKey, Connector c_in, Connector c_out0, Connector c_out1, Connector c_out2, Connector c_out3) {
        ReadEnd in = c_in.getReadEnd();
        WriteEnd out0 = c_out0.getWriteEnd();
        WriteEnd out1 = c_out1.getWriteEnd();
        WriteEnd out2 = c_out2.getWriteEnd();
        WriteEnd out3 = c_out3.getWriteEnd();
        
        this.hashKey = hashKey;
        this.in = in;
        this.out = new WriteEnd[]{out0, out1, out2, out3};
    }
    
    public void run() {
        try {
            Tuple tuple;
            while (true) {
                tuple = in.getNextTuple();
                if (tuple == null || tuple.toString().equals("1#null#")) {
                    break;
                }
                int hash = myhash(tuple);
                out[hash].putNextTuple(tuple);
                //System.out.println(this.getClass().getName() +" Hash " + hash + " " + tuple);
            }
            for (int i = 0; i < GammaConstants.splitLen; i++) {
                out[i].setRelation(in.getRelation());
                out[i].close();
            }
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
    }
    
    int myhash(Tuple tuple) {
        return (Math.abs(tuple.get(hashKey).hashCode()) % GammaConstants.splitLen);
        //return BMap.myhash(tuple.get(hashKey));
    }
    
}