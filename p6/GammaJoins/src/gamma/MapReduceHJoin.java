/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamma;

import basicConnector.*;
import gammaSupport.*;
import hashJoins.*;

/**
 *
 * @author Jianyu
 */
public class MapReduceHJoin extends ArrayConnectors {
    
    String fileName1;
    String fileName2;
    Connector c_out;
    int joinKey1 = 0;
    int joinKey2 = 0;

    public MapReduceHJoin(int joinKey1, String in1, int joinKey2, String in2, Connector c_out) {
        this.joinKey1 = joinKey1;
        this.joinKey2 = joinKey2;
        this.fileName1 = in1;
        this.fileName2 = in2;
        this.c_out = c_out;
    }

    public void start() {
        try {
            Connector c1 = new Connector("input1");
            ReadRelation r1 = new ReadRelation(fileName1, c1);
            r1.start();
            Connector c2 = new Connector("input2");
            ReadRelation r2 = new ReadRelation(fileName2, c2);
            r2.start();
            Connector[] arr1c = ArrayConnectors.initConnectorArray("input1_Split");
            HSplit hs1 = new HSplit(joinKey1, c1, arr1c[0], arr1c[1], arr1c[2], arr1c[3]);
            hs1.start();
            
            Connector[] arr2c = ArrayConnectors.initConnectorArray("input2_Split");
            HSplit hs2 = new HSplit(joinKey2, c2, arr2c[0], arr2c[1], arr2c[2], arr2c[3]);
            hs2.start();
            
            Connector[] o = ArrayConnectors.initConnectorArray("output");

            
            for (int i = 0; i < GammaConstants.splitLen; i++) {
            //for (int i = 0; i < 1; i++) {
                HJoin hj = new HJoin(arr1c[i], arr2c[i], joinKey1, joinKey2, o[i]);
                hj.start();
            }
            
            
            
            Merge m = new Merge(c_out, o[0], o[1], o[2], o[3]);
            m.start();
                 

        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
    }
    
}
