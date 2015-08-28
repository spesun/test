package owls.w3c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

public class IndexReplace {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws Exception {
		String path = "D:/qspsetup/开发环境配置/testindexdef.xml";
		int dbcount = 3;
		
		File file = new File(path);

		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document root = builder.parse(file);
	
		NodeList tasks = root.getElementsByTagName("index");
		int taskLength = tasks.getLength();
		for (int i = 0; i < taskLength; i++) {
			Element node = (Element)tasks.item(i);
			
			for (int j = 1; j < dbcount; j++) {
				Element newNode = node;
				//if (j != 0 ) {
					newNode = (Element)root.importNode(node, true);
					Node nameNode = newNode.getElementsByTagName("name").item(0);
					String tmpName = "_" + j;
					nameNode.setTextContent(nameNode.getTextContent() + tmpName);

					//新增task
			    	node.getParentNode().appendChild(newNode);
				//}
			}
			
		}
		
		//File out = new File("d:/index.xml");
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setParameter(OutputKeys.ENCODING, "utf-8");
		DOMSource source = new DOMSource(root);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);

	}

}
