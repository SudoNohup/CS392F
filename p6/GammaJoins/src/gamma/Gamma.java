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
public class Gamma extends ArrayConnectors {
    
    String fileName1;
    String fileName2;
    Connector c_out;
    int joinKey1 = 0;
    int joinKey2 = 0;

    public Gamma(int joinKey1, String in1, int joinKey2, String in2, Connector c_out) {
        this.joinKey1 = joinKey1;
        this.joinKey2 = joinKey2;
        this.fileName1 = in1;
        this.fileName2 = in2;
        this.c_out = c_out;
    }

    public void start() {
        try {
            System.out.println("Come into Gamma....");
            Connector c1 = new Connector("input1");
            ReadRelation r1 = new ReadRelation(fileName1, c1);
            r1.start();
 
            Connector[] split_bloom = ArrayConnectors.initConnectorArray("split_bloom");
            HSplit hs1 = new HSplit(joinKey1, c1, split_bloom[0], split_bloom[1], split_bloom[2], split_bloom[3]);
            hs1.start();

            Connector[] bloom_hjoin = ArrayConnectors.initConnectorArray("bloom_hjoin");
            Connector[] bloom_filter = ArrayConnectors.initConnectorArray("bloom_filter");

            for (int i = 0; i < GammaConstants.splitLen; i++) {
                Bloom hj = new Bloom(split_bloom[i], bloom_hjoin[i], bloom_filter[i], joinKey1);
                hj.start();
            }

            Connector c2 = new Connector("input2");
            ReadRelation r2 = new ReadRelation(fileName2, c2);
            r2.start();

            Connector[] split_filter = ArrayConnectors.initConnectorArray("split_filter");
            HSplit hs2 = new HSplit(joinKey2, c2, split_filter[0], split_filter[1], split_filter[2], split_filter[3]);
            hs2.start();
            
            Connector[] filter_hjoin = ArrayConnectors.initConnectorArray("filter_hjoin");
            for (int i = 0; i < GammaConstants.splitLen; i++) {
                BFilter bfilter = new BFilter(bloom_filter[i], split_filter[i], filter_hjoin[i], joinKey2);
                bfilter.start();
            }            

            Connector[] o = ArrayConnectors.initConnectorArray("output");
            for (int i = 0; i < GammaConstants.splitLen; i++) {
                HJoin hj = new HJoin(bloom_hjoin[i], filter_hjoin[i], joinKey1, joinKey2,  o[i]);
                hj.start();
            }
            
            Merge m = new Merge(c_out, o[0], o[1], o[2], o[3]);
            m.start();

        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
    }
    
}
