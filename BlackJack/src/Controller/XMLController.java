package Controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Text;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Models.ListModel;

public class XMLController
{
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DOMSource source;
	private StreamResult result;
	private Node current;
	private String username, password;
	private int chipcount, count;
	private LinkedList<ListModel> loginList = new LinkedList<ListModel>();
	
	public XMLController()
	{
		this.initializeXMLFile();
		this.readFile();
	}
	
	public void initializeXMLFile() {
		
		File file = new File("GameData.xml");
		
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			
			if (!file.exists()) {
				doc = docBuilder.newDocument();
				
				Element root = doc.createElement("list");
				doc.appendChild(root);
				
				Element player = doc.createElement("player");
				root.appendChild(player);
				
				Element username = doc.createElement("username");
				username.appendChild(doc.createTextNode("Admin"));
				player.appendChild(username);
				
				Element password = doc.createElement("password");
				password.appendChild(doc.createTextNode("admin"));
				player.appendChild(password);
				
				Element chipCount = doc.createElement("chipcount");
				chipCount.appendChild(doc.createTextNode("0"));
				player.appendChild(chipCount);
								
				transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
				source = new DOMSource(doc);
				result = new StreamResult(new File("GameData.xml"));
				
				transformer.transform(source, result);
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
	}
	
	public void readFile() {
		try {
			
			File fXmlFile = new File("GameData.xml");
			doc = docBuilder.parse(fXmlFile);
			
			current = doc.getDocumentElement();
			
			loginList = new LinkedList<ListModel>();
			
			if (current.hasChildNodes()) {
				current = current.getFirstChild();
				
				while (current != null) {
					Node sibling = current.getFirstChild();
					
					username = sibling.getTextContent();
					sibling = sibling.getNextSibling();
					password = sibling.getTextContent();
					sibling = sibling.getNextSibling();
					chipcount = Integer.parseInt(sibling.getTextContent());
					
					ListModel tmpModel = new ListModel(username, password,
					        chipcount);
					loginList.add(tmpModel);
					
					current = current.getNextSibling();
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	public int verifyLogin(String user, String pw) 
	{
		for (count = 0; count < loginList.size(); count++) 
		{
			if (loginList.get(count).getUsername().equals(user)) 
			{
				if (loginList.get(count).getPassword().equals(pw)) 
				{
					int chipC = loginList.get(count).getChipcount();
					return chipC;
				}
			}
		}
		
		return -1;
	}
	
	public void addNodeToXML(String user, String pw) throws SAXException,
	        IOException, TransformerFactoryConfigurationError,
	        TransformerException {
		
		Boolean isInXML = false;
		
		NodeList playerList = doc.getElementsByTagName("player");
		Element playerItem = null;
		
		for (int i = 0; i < playerList.getLength(); i++) {
			
			playerItem = (Element) playerList.item(i);
			Node name = playerItem.getElementsByTagName("username").item(0)
			        .getFirstChild();
			
			String convertedUserName = convertNodeToString(name);
			
			if (convertedUserName.equals(user)) {
				isInXML = true;
			}
		}
		if (!isInXML) {
			try {
				
				NodeList rootList = doc.getElementsByTagName("list");
				Node root = rootList.item(0);
				
				Element player = doc.createElement("player");
				root.appendChild(player);
				
				Element username = doc.createElement("username");
				username.appendChild(doc.createTextNode(user));
				player.appendChild(username);
				
				Element password = doc.createElement("password");
				password.appendChild(doc.createTextNode(pw));
				player.appendChild(password);
				
				Element chipCount = doc.createElement("chipcount");
				chipCount.appendChild(doc.createTextNode("700"));
				player.appendChild(chipCount);
				
				transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
				source = new DOMSource(doc);
				result = new StreamResult(new File("GameData.xml"));
				
				transformer.transform(source, result);
				
				JOptionPane.showMessageDialog(null,
				        "Ihr Account wurde erfolgreich erstellt!.");
				
				readFile();
				
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null,
			        "Bitte wählen Sie einen anderen Benutzernamen.");
		}
	}
	
	public void saveChipcountToXML(String user, int count)
	        throws TransformerFactoryConfigurationError, TransformerException {
		
		Integer countTmp = new Integer(count);
		
		NodeList playerList = doc.getElementsByTagName("player");
		Element playerItem = null;
		
		for (int i = 0; i < playerList.getLength(); i++) {
			
			playerItem = (Element) playerList.item(i);
			Node name = playerItem.getElementsByTagName("username").item(0)
			        .getFirstChild();
			String convertedUserName = convertNodeToString(name);
			
			if (convertedUserName.equals(user)) {
				Node chipCount = playerItem.getElementsByTagName("chipcount")
				        .item(0).getFirstChild();
				chipCount.setNodeValue(countTmp.toString());
			}
		}
		
		transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		source = new DOMSource(doc);
		result = new StreamResult(new File("GameData.xml"));
		
		transformer.transform(source, result);
		
		readFile();
		
	}
	
	static String convertNodeToString(Node node)
	        throws TransformerFactoryConfigurationError, TransformerException {
		
		StreamResult xmlOutput = new StreamResult(new StringWriter());
		Transformer transformer = TransformerFactory.newInstance()
		        .newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(new DOMSource(node), xmlOutput);
		return xmlOutput.getWriter().toString();
	}
}