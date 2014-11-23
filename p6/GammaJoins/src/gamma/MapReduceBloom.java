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
public class MapReduceBloom extends ArrayConnectors {
    
    Connector c_in;
    Connector c_out, c_outM;
    int joinKey;
    
    public MapReduceBloom(Connector c_in, Connector c_out, Connector c_outM, int joinKey) {
        this.c_in = c_in;
        this.c_out = c_out;
        this.c_outM = c_outM;
        this.joinKey = joinKey;
    }
    
    public void start() {
       try {

           Connector[] arr1r = ArrayConnectors.initConnectorArray("read_Split");
           HSplit hs1 = new HSplit(joinKey, c_in, arr1r[0], arr1r[1], arr1r[2], arr1r[3]);
           hs1.start();

            Connector[] arr1write = ArrayConnectors.initConnectorArray("write_Split");
            Connector[] arr2bmap = ArrayConnectors.initConnectorArray("bmap_Split");

            for (int i = 0; i < GammaConstants.splitLen; i++) {
                Bloom hj = new Bloom(arr1r[i], arr1write[i], arr2bmap[i], joinKey);
                hj.start();
            }
            
            Merge m = new Merge(c_out, arr1write[0], arr1write[1], arr1write[2], arr1write[3]);
            m.start();

            MergeM m1 = new MergeM(c_outM, arr2bmap[0], arr2bmap[1], arr2bmap[2], arr2bmap[3]);
            m1.start();
        } catch (Exception e) {
            ReportError.msg(this.getClass().getName() + e);
        }
        
    }
    
}
