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
public class BloomSimulator extends Thread {
    Connector c_outM;
    WriteEnd outM;
    String rname;
    int keyNumber;
    
    public BloomSimulator(Connector c_outM, String rname) {
        this.c_outM = c_outM;
        this.rname = rname;
        this.keyNumber = 0;
    }
    
    public void run() {
        Connector c_in = new Connector("r_split");
        ReadRelation r = new ReadRelation(rname, c_in);
        Connector hash1 = new Connector("p1");
        Connector hash2 = new Connector("p2");
        Connector hash3 = new Connector("p3");
        Connector hash4 = new Connector("p4");
        HSplit h = new HSplit(keyNumber, c_in, hash1, hash2, hash3, hash4);
        
        //Sink s2 = new Sink(hash2);
        //Sink s3 = new Sink(hash3);
        //Sink s4 = new Sink(hash4);

        Connector out_sink = new Connector("out_sink");
        Bloom bloom = new Bloom(hash1, out_sink, c_outM, keyNumber);
        r.start();
        h.start();
        //s2.start();
        //s3.start();
        //s4.start();
        bloom.start();
   
    }
 
    
}
