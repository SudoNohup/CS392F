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
            //File epsf = new File(CatCore.Main.errMsgFile);
            //epsf.delete();
            //eps = new PrintStream(epsf);
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

            ps.format("%cdbase(catdb,[arrowtype,path,domain,arrow,atp]).\n\n", '%');
            extractNotes(voidList);
            extractDomains(voidList);
            extractArrows(voidList);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            eps.print(e.getMessage());
            e.printStackTrace();
        }
        eps.close();
        ps.close();
    }

    public static void extractArrows(NodeList voidList) {
        int counter = 0;
        LinkedList<String> atl = new LinkedList<>();   // arrow type list -- is a list of (arrowid,domain) pairs
        String atp;
		
		System.out.println("Come into extractArrows\n");
        // Step 1: print out arrow table definition
        ps.format("%ctable(arrow,[id,\"name\",\"startId\",\"endId\"]).\n", '%');

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
			
			System.out.println("Connect:" + start + " " + end + "\n");

            // Step 2.2: we found a <void method="connect"> node.  Now, we need to find the
            //           user given name of that node.  we need to find the <string> children
            //           there should only be one and extract the name in variable arrowName
            //           note: arrowName can be compound a1,a2,a3 -- we will unravel this later
            NodeList strings = evoidNode.getElementsByTagName("string");
            if (strings.getLength() != 1) {
                eps.format("arrow from id %s to id %s should have a name -- see .lite.pl output\n", start, end);
                continue;
            }
            Element estrings0 = (Element) strings.item(0);
            String arrowName = estrings0.getFirstChild().getNodeValue();
            arrowName = arrowName.replaceAll("\\r|\\n", "");

            // Step 2.3: output an arrow tuple -- unravel arrowName if it is compound
            String[] pieces = arrowName.split(",");
            for (String an : pieces) {
                counter++;
                ps.format("arrow(id%d,'%s','%s','%s').\n", counter, an, start, end);
                // now add entries to atp (arrowid, domain).
                atp = "atp(id" + counter + ",o,'" + end + "').";
                atl.add(atp);
                String[] morePieces = start.split("\\*");
                for (String jj : morePieces) {
                    atp = "atp(id" + counter + ",i,'" + Domain.getBaseName(jj) + "').";
                    atl.add(atp);
                }
            }
        }
        if (counter == 0) {
            ps.format(":- dynamic arrow/4.\n");
        }
        ps.format("\n");

        ps.format("%ctable(atp,[aid,io,\"domain\"]).\n", '%');
        for (String k : atl) {
            ps.format("%s\n", k);
        }
        if (counter == 0) {
            ps.format(":- dynamic atp/3.\n");
        }
        ps.format("\n");

    }

    public static void extractDomains(NodeList voidList) {
        String domainName, extName, x, y;
        boolean noDomains = true;

        // Step 1:  printout table declaration
        ps.format("%ctable(domain,[\"id\",\"name\",\"ext\",x,y]).\n", '%');

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
            if (!className.endsWith(".StateNode")) // could be another kind of node, as in NoteNode
            {
                continue;
            }
            String id = eobject0.getAttribute("id");

            // Step 2.1: Now that we know we are adding a domain, we have to get its listed name
            //           we find the first <string> node and extract its value.  That's the user-
            //           given name for the domain (in string arrowName).
            NodeList strings = evoidNode.getElementsByTagName("string");
            if (strings.getLength() != 3) {
                eps.format("Internal Error: incorrect number of <string> elements %s\n", strings.getLength()); // if this fails, the xml format has changed
                continue;
            }

            String[] parts;
            Element estrings0 = (Element) strings.item(0);
            String nodeName = estrings0.getFirstChild().getNodeValue();
            nodeName = nodeName.replaceAll("\\r|\\n", "");
            if (nodeName.contains("*") && nodeName.contains(".")) {
                eps.format("product domain name not in correct format 'd1*d2*...*dn': %s\n", nodeName);
                continue;
            }
            if (nodeName.contains("*")) {
                domainName = nodeName.trim();
                extName = null;
            } else {
                parts = nodeName.split("\\.");
                if (parts.length != 2) {
                    eps.format("domain name not in correct format 'name.ext' :%s\n", nodeName);
                    continue;
                }
                domainName = parts[0].trim();
                extName = parts[1].trim();
            }

            // Step 2.3: last, we need to extract the violet (x,y) position of the domain.
            //           we find all <void method="setLocation">  nodes -- this should be
            //           the last node #7
            //           
            NodeList voids = evoidNode.getElementsByTagName("void");
            if (voids.getLength() != 7) {
                eps.format("Internal Error: incorrect number of <void> elements %d\n", voids.getLength()); // if this fails, the xml format has changed
                continue;
            }
            Element eee = (Element) voids.item(6);
            cls = eee.getAttribute("method");
            if (!cls.equals("setLocation")) {
                eps.format("Internal Error: expected setLocation -- but found: %s\n", cls);
                continue;
            }

            // Step 2.4: now get the nested <double> nodes -- there are 2.  The first
            //           exposes the x value, the second exposes the y value
            NodeList doubles = eee.getElementsByTagName("double");
            if (doubles.getLength() != 2) {
                eps.format("Internal Error: incorrect number of <double> elements %d\n", doubles.getLength());
                continue;
            }
            x = ((Element) doubles.item(0)).getFirstChild().getNodeValue();
            y = ((Element) doubles.item(1)).getFirstChild().getNodeValue();

            // Step 2.5: now print out the extracted domain tuple
            noDomains = false;
            ps.format("domain('%s','%s','%s',%s,%s).\n", id, domainName, extName, x, y);
        }
        if (noDomains) {
            ps.format(":- dynamic domain/5.\n");
        }
        ps.format("\n");
    }

    public static void extractNotes(NodeList voidList) {
        boolean printedArrowType = false;
        boolean printedPath = false;

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
            //           to determine if the object to be added is a NoteNode or not.
            NodeList objects = evoidNode.getElementsByTagName("object");
            Element eobject0 = (Element) objects.item(0);
            String className = eobject0.getAttribute("class");
            if (!className.endsWith(".NoteNode")) // could be another kind of node, as in NoteNode
            {
                continue;
            }

            // Step 2.2: Now that we know we are adding a note, we have to get its string
            //           we do this by finding the first <string> node and extracting its
            //           value, and then into parts[]
            NodeList strings = evoidNode.getElementsByTagName("string");

            Element estrings0 = (Element) strings.item(0);
            String nodestring = estrings0.getFirstChild().getNodeValue();
            String[] parts = nodestring.split("\\r|\\n");

            // Step 2.3: now decide what kind of note this is: either it is Arrows or Paths note
            String arrowName, arrowType, arrowExe;
            int counter = 0;

            if (parts[0].startsWith("Arrows")) {
                printedArrowType = true;
                // Step 2.3.1:  printout table declaration
                ps.format("%ctable(arrowtype,[id,\"name\",type,\"exe\"]).\n", '%');

                // Step 2.3.2: for each line parse it into 2 or 3 subparts (separated by :)
                for (int j = 1; j < parts.length; j++) {
                    parts[j] = parts[j].trim();
                    if (parts[j].equals("")) {
                        continue;
                    }
                    String[] pieces = parts[j].split(":");
                    arrowName = pieces[0].trim();
                    arrowType = pieces[1].trim();
                    if (pieces.length == 2) {
                        arrowExe = "";
                    } else {
                        arrowExe = pieces[2].replace(";", "");
                    }
                    // add default executables
                    if (arrowExe.equals("")) {
                        switch (arrowType) {
                            case "pl":
                                arrowExe = arrowName + ".pl";
                                break;
                            case "vm":
                                arrowExe = arrowName + ".vm";
                                break;
                            case "java":
                                eps.format("no java executable specified for arrow %s\n", arrowName);
                            case "path":
                                eps.format("no path specified for arrow %s\n", arrowName);
                        }
                    }

                    // Step 2.3.3: print out the tuple
                    ps.format("arrowtype(at%d,'%s',%s,'%s').\n", counter++, arrowName, arrowType, arrowExe);
                }
                if (counter == 0) {
                    ps.format(":- dynamic arrowtype/3.\n");
                }
                ps.format("\n");
            }
            if (parts[0].startsWith("Paths")) {
                printedPath = true;
                // Step 2.3.1:  printout table declaration
                ps.format("%ctable(path,[\"path\"]).\n", '%');

                // Step 2.3.2: for each line print it out as a tuple
                for (int j = 1; j < parts.length; j++) {
                    parts[j] = parts[j].trim();
                    if (parts[j].equals("")) {
                        continue;
                    }
                    if (!parts[j].endsWith(";")) {
                        eps.format("path %s does not end in ';'\n", parts[j]);
                    }
                    ps.format("path('%s').\n", parts[j]);
                }
                if (parts.length == 1) {
                    ps.format(":- dynamic path/1.\n");
                }
                ps.format("\n");
            }
        }

        // Step 3 : cleanup.  print dummy headers if necessary
        if (!printedArrowType) {
            ps.format("%ctable(arrowtype,[id,\"name\",type,\"exe\"]).\n", '%');
            ps.format(":- dynamic arrowtype/4.\n", (Object) null);

        }
        if (!printedPath) {
            ps.format("%ctable(path,[\"path\"]).\n", '%');
            ps.format(":- dynamic path/1.", (Object) null);
        }
        ps.format("\n\n", (Object) null);
    }

}