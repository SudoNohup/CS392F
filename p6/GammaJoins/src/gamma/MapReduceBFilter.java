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
public class MapReduceBFilter extends ArrayConnectors {
    
    Connector c_inM;
    Connector c_in;
    Connector c_out;
    int keyNumber;
    
    public MapReduceBFilter(Connector c_inM, Connector c_in, Connector c_out, int keyNumber) {
        this.c_inM = c_inM;
        this.c_in = c_in;
        this.c_out = c_out; 
        this.keyNumber = keyNumber;
    }
    
    public void start() {
        Connector mb1 = new Connector("mb1");
        Connector mb2 = new Connector("mb2");
        Connector mb3 = new Connector("mb3");
        Connector mb4 = new Connector("mb4");
        SplitM m1 = new SplitM(c_inM, mb1, mb2, mb3, mb4);
        m1.start();
        
        Connector hb1 = new Connector("hb1");
        Connector hb2 = new Connector("hb2");
        Connector hb3 = new Connector("hb3");
        Connector hb4 = new Connector("hb4");
        HSplit h1 = new HSplit(keyNumber, c_in, hb1, hb2, hb3, hb4);
        h1.start();
        
        Connector bmerge1 = new Connector("bmerge1");
        Connector bmerge2 = new Connector("bmerge2");
        Connector bmerge3 = new Connector("bmerge3");
        Connector bmerge4 = new Connector("bmerge4");
        BFilter bf1 = new BFilter(mb1, hb1, bmerge1, keyNumber);
        BFilter bf2 = new BFilter(mb2, hb2, bmerge2, keyNumber);
        BFilter bf3 = new BFilter(mb3, hb3, bmerge3, keyNumber);
        BFilter bf4 = new BFilter(mb4, hb4, bmerge4, keyNumber);
        bf1.start();
        bf2.start();
        bf3.start();
        bf4.start();

        Merge m = new Merge(c_out, bmerge1, bmerge2, bmerge3, bmerge4);      
        m.start();   
    }
    
    
}
