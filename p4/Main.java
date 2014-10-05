/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package CatLite.categoryVioletParser;
*/

import CatCore.Domains.Domain;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author dsb
 */
public class Main {

    private static PrintStream ps;
    private static PrintStream eps;

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {

        // Step 1: incorrect arguments prints the marquee.
        if (args.length != 2) {
            System.out.println("Usage: categoryVioletParser.Main <in>.violet <out>.pl");
            return;
        }

        // Step 2: create the name of the output prolog file and create its printstream
        String outputPrologFileName = args[1];
        try {
            ps = new PrintStream(new File(outputPrologFileName));
			eps = new PrintStream(new File("error.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Step 3: read violet's xml file and produce the prolog file
        try {
            File fXmlFile = new File(args[0]);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            NodeList voidList = doc.getElementsByTagName("void");
			ps.format("/* the discontiguous declaration says rows of \"table\" are not consecutive */\n");
			ps.format(":- discontiguous table/2.\n\n");
			ps.format("%cdbase(fsm,[node,transition]).\n\n", '%');

            extractNodes(voidList);
            extractTransitions(voidList);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            eps.print(e.getMessage());
            e.printStackTrace();
        }
        eps.close();
        ps.close();
    }

    public static void extractTransitions(NodeList voidList) {
        int counter = 0;
        // Step 1: print out arrow table definition
		ps.format("%ctable(transition,[transid,startsAt,endsAt]).\n", '%');

        // Step 2: let's plow through all of the <void> nodes to find <void method="connect">
        //         instances
        for (int i = 0; i < voidList.getLength(); i++) {
            Node voidNode = voidList.item(i);
            Element evoidNode = (Element) voidNode;
            String cls = evoidNode.getAttribute("method");
            if (!cls.equals("connect")) {
                continue;
            }

            // Step 2.1: now lets get the starting and ending domain names.  these are found in 
            //           <object idref=""> nodes -- there are only 3 of them, and they are the last 2.
            //           start and end are the domain identifiers
            NodeList objects = evoidNode.getElementsByTagName("object");
            if (objects.getLength() != 3) {
                eps.format("Internal Error: can't determine domain %s\n", "names");
                continue;
            }
            String start = ((Element) objects.item(1)).getAttribute("idref");
            String end = ((Element) objects.item(2)).getAttribute("idref");

            // Step 2.2: output an transition tuple -- unravel arrowName if it is compound
            counter++;
			ps.format("transition(t%d, %s, %s).\n", counter, start, end);

        }
        if (counter == 0) {
            ps.format(":- dynamic transition/3.\n");
        }
        ps.format("\n");
    }

    public static void extractNodes(NodeList voidList) {
        boolean noNodes = true;
        // Step 1:  printout table declaration	
		ps.format("%ctable(node,[nodeid,name,type].\n", '%');
		
        // Step 2: let's plow through all of the <void/> nodes with attribute method="addNode"
        for (int i = 0; i < voidList.getLength(); i++) {
            Node voidNode = voidList.item(i);
            Element evoidNode = (Element) voidNode;
            String cls = evoidNode.getAttribute("method");
            if (!cls.equals("addNode")) {
                continue;
            }

            // Step 2.1: further qualification.  inside the <void method="addNode">
            //           we have to figure out what node is being added.  So we have
            //           to find all <object/> nodes -- really just the first --
            //           to determine if the object to be added is a StateNode or not.
            //           we are only interested in StateNodes. The result of this
            //           code is obtaining the identifier of the domain (in variable id).
            NodeList objects = evoidNode.getElementsByTagName("object");
            Element eobject0 = (Element) objects.item(0);
            String className = eobject0.getAttribute("class");
            if (!className.endsWith("StateNode")) // could be another kind of node, as in NoteNode
            {
                continue;
            }
            String id = eobject0.getAttribute("id");	
			
            // Step 2.2: Now that we know we are adding a domain, we have to get its listed name
            //           we find the first <string> node and extract its value.  That's the user-
            //           given name for the domain (in string nodeName).
			String nodeName;
			String[] parts;
			String state;
			if (id.contains("InitialStateNode")) {
			    nodeName = "start";
			    state = "start";
			} else if (id.contains("FinalStateNode")) {
			    nodeName = "stop";
			    state = "stop";
			} else {
                NodeList strings = evoidNode.getElementsByTagName("string");
                Element estrings0 = (Element) strings.item(0);
                nodeName = estrings0.getFirstChild().getNodeValue();
			    state = "state";
			}
            nodeName = nodeName.replaceAll("\\r|\\n", "");
            
            // Step 2.3: now print out the result
            noNodes = false;
            ps.format("node(%s, %s, %s).\n", id, nodeName, state);
        }
        if (noNodes) {
            ps.format(":- dynamic node/3.\n");
        }
        ps.format("\n");
    }
}