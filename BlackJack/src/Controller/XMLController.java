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
				
				// Element player = doc.createElement("player");
				// root.appendChild(player);
				
				// Element username = doc.createElement("username");
				// username.appendChild(doc.createTextNode("Admin"));
				// player.appendChild(username);
				//
				// Element password = doc.createElement("password");
				// password.appendChild(doc.createTextNode("admin"));
				// player.appendChild(password);
				//
				// Element chipCount = doc.createElement("chipcount");
				// chipCount.appendChild(doc.createTextNode("0"));
				// player.appendChild(chipCount);
				
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
	
	public int verifyLogin(String user, String pw) {
		
		NodeList playerList = doc.getElementsByTagName("player");
		Element playerItem = null;
		
		for (int i = 0; i < playerList.getLength(); i++) {
			
			playerItem = (Element) playerList.item(i);
			String message = playerItem.getElementsByTagName("message").item(0)
			        .getFirstChild().getNodeValue();
			String name = playerItem.getElementsByTagName("username").item(0)
			        .getFirstChild().getNodeValue();
			
			if (name.equals(user) && !message.equals(" ")) {
				JOptionPane.showMessageDialog(null, message);
				
				Node oldMessage = playerItem.getElementsByTagName("message")
				        .item(0).getFirstChild();
				oldMessage.setNodeValue(" ");
			}
		}
		
		for (count = 0; count < loginList.size(); count++) {
			if (loginList.get(count).getUsername().equals(user)) {
				if (loginList.get(count).getPassword().equals(pw)) {
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
			String name = playerItem.getElementsByTagName("username").item(0)
			        .getFirstChild().getNodeValue();
			
			if (name.equals(user)) {
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
				
				Element message = doc.createElement("message");
				message.appendChild(doc.createTextNode(" "));
				player.appendChild(message);
				
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
			String name = playerItem.getElementsByTagName("username").item(0)
			        .getFirstChild().getNodeValue();
			
			if (name.equals(user)) {
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
	
	public void deletePlayer(String user)
	        throws TransformerFactoryConfigurationError, TransformerException {
		
		NodeList playerList = doc.getElementsByTagName("player");
		Element playerItem = null;
		
		for (int i = 0; i < playerList.getLength(); i++) {
			
			playerItem = (Element) playerList.item(i);
			String playerName = playerItem.getElementsByTagName("username")
			        .item(0).getFirstChild().getNodeValue();
			
			if (playerName.equals(user)) {
				
				playerList.item(i).getParentNode()
				        .removeChild(playerList.item(i));
				
				transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
				source = new DOMSource(doc);
				result = new StreamResult(new File("GameData.xml"));
				
				transformer.transform(source, result);
			}
		}
		readFile();
		
	}
	
	public void savePasswordToXML(String user, String newPassword, int flag)
	        throws TransformerException {
		
		NodeList playerList = doc.getElementsByTagName("player");
		Element playerItem = null;
		
		for (int i = 0; i < playerList.getLength(); i++) {
			
			playerItem = (Element) playerList.item(i);
			String name = playerItem.getElementsByTagName("username").item(0)
			        .getFirstChild().getNodeValue();
			
			if (name.equals(user)) {
				Node password = playerItem.getElementsByTagName("password")
				        .item(0).getFirstChild();
				password.setNodeValue(newPassword);
				
				if (flag == 1) {
					Node message = playerItem.getElementsByTagName("message")
					        .item(0).getFirstChild();
					message.setNodeValue("Ihr neues Passwort ist "
					        + newPassword);
				}
			}
		}
		
		transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		source = new DOMSource(doc);
		result = new StreamResult(new File("GameData.xml"));
		
		transformer.transform(source, result);
		
		readFile();
	}
	
	public Boolean getChipCount(String user) {
		
		NodeList playerList = doc.getElementsByTagName("player");
		Element playerItem = null;
		
		for (int i = 0; i < playerList.getLength(); i++) {
			
			playerItem = (Element) playerList.item(i);
			String username = playerItem.getElementsByTagName("username")
			        .item(0).getFirstChild().getNodeValue();
			String chipCount = playerItem.getElementsByTagName("chipcount")
			        .item(0).getFirstChild().getNodeValue();
			if (username.equals(user) && chipCount.equals("0"))
				return true;
		}
		return false;
	}
	
	public LinkedList<ListModel> getHighScoreList() {
		LinkedList<ListModel> highScoreList = new LinkedList<ListModel>();
		
		highScoreList.add(0, loginList.get(0));
		int tmp = 0;
		
		for (int i = 1; i < loginList.size(); i++) {
			Boolean breaking = false;
			for (int j = 0; j < highScoreList.size(); j++) {
				
				if (highScoreList.get(j).getChipcount() < loginList.get(i)
				        .getChipcount()) {
					highScoreList.add(j, loginList.get(i));
					breaking = true;
					break;
				}
				tmp = j;
			}
			if (!breaking)
				highScoreList.add(tmp + 1, loginList.get(i));
			
		}
		return highScoreList;
	}
	
	// static String convertNodeToString(Node node)
	// throws TransformerFactoryConfigurationError, TransformerException {
	//
	// StreamResult xmlOutput = new StreamResult(new StringWriter());
	// Transformer transformer = TransformerFactory.newInstance()
	// .newTransformer();
	// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	// transformer.transform(new DOMSource(node), xmlOutput);
	// return xmlOutput.getWriter().toString();
	// }
	
	public LinkedList<ListModel> getLoginList() {
		return this.loginList;
	}
}