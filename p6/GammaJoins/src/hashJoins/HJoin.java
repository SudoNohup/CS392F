/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashJoins;

import basicConnector.Connector;
import basicConnector.ReadEnd;
import basicConnector.WriteEnd;
import gammaSupport.*;

import gammaSupport.Tuple;


import java.util.*;

/**
 *
 * @author Jianyu
 */
public class HJoin extends Thread {
    ReadEnd in1, in2;
    WriteEnd out;
    int joinKey1 = 0;
    int joinKey2 = 0;
    
    public HJoin(ReadEnd in1, ReadEnd in2, int joinKey1, int joinKey2, WriteEnd out)  {
        //System.out.println("Come into HJoin!");
        this.joinKey1 = joinKey1;
        this.joinKey2 = joinKey2;
        this.in1 = in1;
        this.in2 = in2;
        this.out = out;
        //ThreadList.add(this);
    }
    
    public HJoin(Connector c1, Connector c2, int joinKey1, int joinKey2, Connector c3)  {
        ReadEnd in1 = c1.getReadEnd();
        ReadEnd in2 = c2.getReadEnd();
        WriteEnd out = c3.getWriteEnd();
        //System.out.println("Come into HJoin!");
        this.joinKey1 = joinKey1;
        this.joinKey2 = joinKey2;
        this.in1 = in1;
        this.in2 = in2;
        this.out = out;
        //ThreadList.add(this);
    }

    /*
    public HJoin(Connector c1, Connector c2, int jk1, int jk2, Connector o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
        public void run() {
        //System.out.println("Come into run() of HJoin!");
        //try {
        Map<String, List<Tuple>> temp = new HashMap<String, List<Tuple>>();
        while (true) {
            Tuple tuple = null;

            try {
                tuple = in1.getNextTuple();
            } catch (Exception e) {

            }

            if (tuple == null || tuple.toString().equals("1#null#")) {
                break;
            }
            String key = tuple.get(joinKey1);
            if (key != null) {
                if (temp.containsKey(key)) {
                    //System.out.println("contain1: " + key + " " + tuple.toString());
                    List<Tuple> list = temp.get(key);
                    list.add(tuple);
                } else {
                    //System.out.println("notcontain1: " + key + " " + tuple.toString());
                    List<Tuple> list = new ArrayList<Tuple>();
                    list.add(tuple);
                    temp.put(key, list);
                }
            }
        }

            //for ()
        //System.out.println();
        //System.out.println("----------------------");
        while (true) {
            Tuple tuple2 = null;
            try {
                tuple2 = in2.getNextTuple();
            } catch (Exception e) {

            }

            if (tuple2 == null || tuple2.toString().equals("1#null#")) {
                break;
            }

            //System.out.println("++++++++++" + tuple2.toString() + "  joinKey2:" + joinKey2);
            String key2 = tuple2.get(joinKey2);
            if (temp.containsKey(key2)) {
                //System.out.println("contain2: " + key2 + " " + tuple2.toString());
                List<Tuple> list = temp.get(key2);
                for (Tuple tuple1 : list) {
                        //The parameter joinKey1 is not used in Tuple.join() function..
                    //System.out.println("tuple1: " + tuple1.toString());
                    Tuple outTuple = Tuple.join(tuple1, tuple2, joinKey1, joinKey2);
                    
                    
                    //System.out.println(this.getClass().getName() +" " + outTuple);

                    try {
                        out.putNextTuple(outTuple);
                    } catch (Exception e) {

                    }

                }
            }
        }

        Relation relation1 = in1.getRelation();
        Relation relation2 = in2.getRelation();
        //System.out.println("!!!!!!!!!!!!!" + relation1.getRelationName() + " " + relation2.getRelationName() + " ");
        Relation outRelation = Relation.join(relation1, relation2, joinKey1, joinKey2);
        /*
        if (outRelation == null) {
            System.out.println("null................\n");
        } else {
            System.out.println("~~~~~~~~~~~~~~" + outRelation.getRelationName());
        }
        */

        out.setRelation(outRelation);
        out.close();
        //} catch(Exception e) {
        //    ReportError.msg(this.getClass().getName() + e);
        // }
    }
}
