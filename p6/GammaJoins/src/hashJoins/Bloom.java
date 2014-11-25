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
public class Bloom extends Thread {
    ReadEnd in;
    WriteEnd out, outM;
    int joinKey;
    
    public Bloom(Connector c_in, Connector c_out, Connector c_outM, int joinKey) {
        this.in = c_in.getReadEnd();
        this.out = c_out.getWriteEnd();
        this.outM = c_outM.getWriteEnd();
        this.joinKey = joinKey;
    }
    
    
    public void run() {
        Tuple tuple;
        BMap bmap = BMap.makeBMap();
        try {
            while (true) {
                tuple = in.getNextTuple();
                if (tuple == null || tuple.toString().equals("1#null#")) {
                    break;
                }
                out.putNextTuple(tuple);
                String key = tuple.get(joinKey);
                bmap.setValue(key, true);
            }
            outM.putNextString(bmap.getBloomFilter());
            out.setRelation(in.getRelation());
            out.close();

        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }

    }
    
}
