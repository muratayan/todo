package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.TaskItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileWrite {

	public ArrayList<TaskItem> readXmlFile(){
		ArrayList<TaskItem> returnList = new ArrayList<TaskItem>();

		try {
			File fXmlFile = new File("database.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Entry");

			System.out.println("----------------------------");


			//Lets start a Arraylist to put everything in

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					String name=eElement.getElementsByTagName("Name").item(0).getTextContent();
					String date=eElement.getElementsByTagName("Date").item(0).getTextContent();
					String cat=eElement.getElementsByTagName("Cat").item(0).getTextContent();
					String prio=eElement.getElementsByTagName("Prio").item(0).getTextContent();
					//shows how to deal with null values when reading XMLrow that otherwise causes nullpointerexception
					String prog=eElement.getElementsByTagName("Progress").item(0)==null?"0":eElement.getElementsByTagName("Progress").item(0).getTextContent();
					String done=eElement.getElementsByTagName("Done").item(0)==null?"false":eElement.getElementsByTagName("Done").item(0).getTextContent();
					
					
					int progress = Integer.parseInt(prog);
					boolean isDone = Boolean.parseBoolean(done);
					
					System.out.print("FileWriter: Name " + name);
					System.out.print("  Date " + date);
					System.out.print("  Cat " + cat);
					System.out.print("  Prio " + prio);
					System.out.println("  Progress " + prog);


					returnList.add( new TaskItem(name,prio,cat,date,progress,isDone));
				}
			}
		//return returnList;

		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileWrite: File not found. Load empty ArrayList");
		} catch (ParserConfigurationException|IOException|SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FileWrite: Something wrong with the parsing of XML file");
		}

		return returnList;
	}

	public void writeXmlFile(ArrayList<TaskItem> list) {

		try {

			DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dFact.newDocumentBuilder();
			Document doc = build.newDocument();

			Element root = doc.createElement("Root");
			doc.appendChild(root);




			for(int i=0; i<list.size(); i++ ) {
				Element entry = doc.createElement("Entry");
				root.appendChild(entry);

				Element name = doc.createElement("Name");
				name.appendChild(doc.createTextNode(list.get(i).getDescription()));
				entry.appendChild(name);
				//Save date
				Element id = doc.createElement("Date");
				id.appendChild(doc.createTextNode(list.get(i).getDate()));
				entry.appendChild(id);
				//Save category
				Element cat = doc.createElement("Cat");
				cat.appendChild(doc.createTextNode(list.get(i).getCategory()));
				entry.appendChild(cat);
				
				//Save priority
				Element prio = doc.createElement("Prio");
				prio.appendChild(doc.createTextNode(list.get(i).getPriority()));
				entry.appendChild(prio);
				
				//save progressSlider Value. convert from int to string
				Element prog = doc.createElement("Progress");
				prog.appendChild(doc.createTextNode(""+list.get(i).getProgress()));
				entry.appendChild(prog);
				
				Element done = doc.createElement("Done");
				done.appendChild(doc.createTextNode(""+list.get(i).getDone()));
				entry.appendChild(done);
			}


			// Save the document to the disk file
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();

			// format the XML nicely
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

			aTransformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");



			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fos = new FileWriter("database.xml");
				StreamResult result = new StreamResult(fos);
				aTransformer.transform(source, result);

			} catch (IOException e) {

				e.printStackTrace();
			}



		} catch (TransformerException ex) {
			System.out.println("Error outputting document");

		} catch (ParserConfigurationException ex) {
			System.out.println("Error building document");
		}
	}
}