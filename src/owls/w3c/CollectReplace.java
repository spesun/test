package owls.w3c;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CollectReplace {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		String path = "D:/qspsetup/开发环境配置/testcollectdef.xml";
		
		int dbcount = 3;
		Map dbReplace = new HashMap();
		dbReplace.put(0, "192.168.100.62:1521:ora11g");
		dbReplace.put(1, "192.168.100.200:1521:ora11g");
		dbReplace.put(2, "192.168.100.201:1521:ora11g");
		
		
		String username = "";
		String password = "";
		
		File file = new File(path);
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document root = builder.parse(file);
	
		NodeList tasks = root.getElementsByTagName("task");
		int taskLength = tasks.getLength();
		for (int i = 0; i < taskLength; i++) {
			Element node = (Element)tasks.item(i);
			for (int dbindex = 0; dbindex < dbcount; dbindex++) {
				Element newNode = node;
				if (dbindex != 0 ) {
					newNode = (Element)root.importNode(node, true);
					
					String tmpName = "_" + dbindex;
					newNode.setAttribute("name", newNode.getAttribute("name") + tmpName);
					
				}
				
				Node urlNode = newNode.getElementsByTagName("url").item(0);
				urlNode.setTextContent("jdbc:oracle:thin:@" + dbReplace.get(dbindex));
				
				Node usernameNode = newNode.getElementsByTagName("username").item(0);
				usernameNode.setTextContent(username);
				
				Node passwordNode = newNode.getElementsByTagName("password").item(0);
			    passwordNode.setTextContent(password);
				

				 //新增task
			    if (dbindex != 0 ) {
			    	node.getParentNode().appendChild(newNode);
				}
			}
			
		}
		
		//File out = new File("d:/collect.xml");
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setParameter(OutputKeys.ENCODING, "utf-8");
		DOMSource source = new DOMSource(root);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
				
	}

}
