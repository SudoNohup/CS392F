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
 * @author Jianyu, Xiaohui
 */
public class HJoinRefineWithBloomFilters extends ArrayConnectors {
    
    String fName1;
    String fName2;
    Connector c_out;
    int joinKey1 = 0;
    int joinKey2 = 0;

    public HJoinRefineWithBloomFilters(int joinKey1, String in1, int joinKey2, String in2, Connector c_out) {
        this.joinKey1 = joinKey1;
        this.joinKey2 = joinKey2;
        this.fName1 = in1;
        this.fName2 = in2;
        this.c_out = c_out;
    }

    public void start() {
        try {
            Connector c1 = new Connector("input1");
            ReadRelation r1 = new ReadRelation(fName1, c1);
            r1.start();
            
            Connector c2 = new Connector("input2");
            ReadRelation r2 = new ReadRelation(fName2, c2);
            r2.start();
            
            Connector bmap = new Connector("bloom");
            Connector input_1 = new Connector("input_1");
            Connector input_2 = new Connector("input_2");
            
            Bloom bloom = new Bloom(c1, input_1, bmap, joinKey1);
            bloom.start();
            
            BFilter bfliter = new BFilter(bmap,c2, input_2, joinKey2);
            bfliter.start();
            
            HJoin hj = new HJoin(input_1, input_2, joinKey1, joinKey2, c_out);
            hj.start();
            
        } catch ( Exception e )
        {
            ReportError.msg(this.getClass().getName() + e);
        }
    }
    
}
