package owls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class CollectReplaceJdom {

	public static String dir = "D:/项目svn/qsp/owlsconf/qsp/apps/app1/conf/";
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		String path = dir + "testcollectdef.xml";
		
		int dbcount = 3;
		Map dbReplace = new HashMap();
		dbReplace.put("0", "192.168.100.62:1521:ora11g");
		dbReplace.put("1", "192.168.100.200:1521:ora11g");
		dbReplace.put("2", "192.168.100.201:1521:ora11g");
		
		dbReplace.put("0username", "pfdb");
		dbReplace.put("1username", "pfdb");
		dbReplace.put("2username", "pfdb");
		
		dbReplace.put("0password", "!@#$%^KUmIaPsalsr5f7hmPZuQrQ==");
		dbReplace.put("1password", "!@#$%^7H2vbqVM08M/mlf9Kln02g==");
		dbReplace.put("2password", "!@#$%^7H2vbqVM08M/mlf9Kln02g==");
		
		File file = new File(path);
		
		SAXBuilder builder=new SAXBuilder(false);
		Document doc= builder.build(file);
		Element root=doc.getRootElement();
		List<Element> tasks = root.getChildren("task");
		long taskSize = tasks.size();
		for (int i = 0; i < taskSize; i++) {
			Element task = tasks.get(i);
			
			for (int dbindex = 0; dbindex < dbcount; dbindex++) {
				Element newNode = task;
				if (dbindex != 0 ) {
					newNode = task.clone();
					
					String tmpName = "_" + dbindex;
					newNode.setAttribute("name", newNode.getAttribute("name").getValue() + tmpName);
					
				}
				
				
				Element urlNode = newNode.getChild("url");
				urlNode.setText("jdbc:oracle:thin:@" + dbReplace.get(Integer.toString(dbindex)));
				
				Element usernameNode = newNode.getChild("username");
				usernameNode.setText((String)dbReplace.get(dbindex + "username"));
				
				Element passwordNode = newNode.getChild("password");
			    passwordNode.setText((String)dbReplace.get(dbindex + "password"));
				

				 //新增task
			    if (dbindex != 0 ) {
			    	root.addContent("\n");
			    	root.addContent(newNode);
				}
			    			    
			}
		}
		
		
		XMLOutputter outputter=new XMLOutputter();
		
		Format format = outputter.getFormat();
		format.setEncoding("utf-8");
		format.setExpandEmptyElements(true);
		outputter.output(doc, new FileOutputStream(file));
				
	}

}
