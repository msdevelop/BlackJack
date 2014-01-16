package Controller;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
	private LinkedList <ListModel> loginList = new LinkedList<ListModel>();
	
	public XMLController()
	{
		this.testCreate();
		this.readFile();
	}
	
	public void testCreate()
	{	
		try
		{
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			
			doc = docBuilder.newDocument();
			
			Element root = doc.createElement("list");
			doc.appendChild(root);
			
			Element player = doc.createElement("player");
			root.appendChild(player);
			
			Element username = doc.createElement("username");
			username.appendChild(doc.createTextNode("Johann"));
			player.appendChild(username);
			
			Element password = doc.createElement("password");
			password.appendChild(doc.createTextNode("meier"));
			player.appendChild(password);
			
			Element chipCount = doc.createElement("chipcount");
			chipCount.appendChild(doc.createTextNode("700"));
			player.appendChild(chipCount);
			
			//----------------------------------------------
			
			Element player2 = doc.createElement("player");
			root.appendChild(player2);
			
			Element username2 = doc.createElement("username");
			username2.appendChild(doc.createTextNode("Klaus"));
			player2.appendChild(username2);
			
			Element password2 = doc.createElement("password");
			password2.appendChild(doc.createTextNode("dieter"));
			player2.appendChild(password2);
			
			Element chipCount2 = doc.createElement("chipcount");
			chipCount2.appendChild(doc.createTextNode("500"));
			player2.appendChild(chipCount2);
			
			
			
			
			//----------------------------------------------
			
			
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			source = new DOMSource(doc);
			result = new StreamResult(new File("E:\\Git_Workspace_Eclipse\\chipcount.xml"));
			
			transformer.transform(source, result);

		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		}
		catch (TransformerException tfe) 
		{
			tfe.printStackTrace();
		}
	}
	
	public void readFile()
	{
		try 
			{
			 
				File fXmlFile = new File("E:\\Git_Workspace_Eclipse\\chipcount.xml");
				doc = docBuilder.parse(fXmlFile);
				
				current = doc.getDocumentElement();
				
				if(current.hasChildNodes())
				{
					current = current.getFirstChild();
					
					while(current != null)
					{
						Node sibling = current.getFirstChild();
						
						username = sibling.getTextContent();
						sibling = sibling.getNextSibling();
						password = sibling.getTextContent();
						sibling = sibling.getNextSibling();
						chipcount = Integer.parseInt(sibling.getTextContent());
						
						ListModel tmpModel = new ListModel(username, password, chipcount);
						loginList.add(tmpModel);
						
						current = current.getNextSibling();
					}
				}
			}
		catch(Exception e)
		{
			
		}
	}
	
	public int verifyLogin(String user, String pw)
	{
		for(count = 0; count < loginList.size(); count++)
		{
			if(loginList.get(count).getUsername().equals(user));
			{
				if(loginList.get(count).getPassword().equals(pw))
				{
					int chipC = loginList.get(count).getChipcount();
					return chipC;
				}
			}
		}
		
		return -1;
	}
}