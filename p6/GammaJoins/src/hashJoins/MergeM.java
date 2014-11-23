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
public class MergeM extends Thread {
    ReadEnd in[];
    WriteEnd out;
    
    public MergeM(Connector c_out, Connector c_in0, Connector c_in1, Connector c_in2, Connector c_in3) {
        WriteEnd out = c_out.getWriteEnd();
        ReadEnd in0 = c_in0.getReadEnd();
        ReadEnd in1 = c_in1.getReadEnd();
        ReadEnd in2 = c_in2.getReadEnd();
        ReadEnd in3 = c_in3.getReadEnd();
        this.out = out;
        this.in = new ReadEnd[]{in0, in1, in2, in3};
    }

    public void run() {
        try {
            String input;
            BMap bmap = BMap.makeBMap();
            for (int i = 0; i < GammaConstants.splitLen; i++) {
                input = in[i].getNextString();
                if (input == null) {
                    break;
                }
                bmap = BMap.or(bmap, BMap.makeBMap(input));
            }
            out.putNextString(bmap.getBloomFilter());
            out.close();
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
    }
    
}
