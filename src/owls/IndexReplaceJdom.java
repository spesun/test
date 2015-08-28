package owls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class IndexReplaceJdom {

	
	
	static int dbcount = 3;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		String path;
		String type;
		path = CollectReplaceJdom.dir + "testindexdef.xml";
		type = "index";
		replace(path, type);
		
		path = CollectReplaceJdom.dir + "testsearchdef.xml";
		type = "search";
		replace(path, type);
	}
	
	public static void replace(String path, String type) throws Exception {
		File file = new File(path);
		
		SAXBuilder builder=new SAXBuilder(false);
		Document doc= builder.build(file);
		Element root=doc.getRootElement();
		List<Element> tasks = root.getChildren("index");
		long taskSize = tasks.size();
		for (int i = 0; i < taskSize; i++) {
			Element task = tasks.get(i);
			
			for (int dbindex = 1; dbindex < dbcount; dbindex++) {
				Element newNode = task.clone();
				
				String tmpName = "_" + dbindex;
				if (type.equals("index")) {
	
					newNode.getChild("name").setText(newNode.getChildText("name") + tmpName);
				} else {
					newNode.setAttribute("name", newNode.getAttributeValue("name") + tmpName);
				}
				
				root.addContent("\n");
				root.addContent(newNode);
			}
		}
		
		
		XMLOutputter outputter=new XMLOutputter();
		
		Format format = outputter.getFormat();
		format.setEncoding("utf-8");
		format.setExpandEmptyElements(true);
		outputter.output(doc, new FileOutputStream(file));
	}


}
