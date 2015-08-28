package xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class TransTest {

	public static Document xmlTrans(String xml) throws Exception {

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();

		URL resUrl = TransTest.class.getResource("transform.xsl");// 定义好XSL格式

		//File file = new File("d:/xsl/xsl.xsl");
		Source source = new StreamSource(resUrl.getPath());

		Templates templates = transformerFactory.newTemplates(source);

		Transformer transformer = templates.newTransformer();

		StringReader reader = new StringReader(xml);
		Source xmlSource = new StreamSource(reader);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Result outputTarget = new StreamResult(out);

		transformer.transform(xmlSource, outputTarget);

		String reslut = new String(out.toByteArray());

		Document doc = DocumentHelper.parseText(reslut);

		return doc;
	}

	public static void main(String[] args) throws Exception {
		File f = new File(TransTest.class.getResource("transform.xml").getPath());
		InputStreamReader in = new FileReader(f);
		StringBuffer sb = new StringBuffer();
		char[] char6 = new char[1024];
		while (true) {
			int i = in.read(char6);
			
			if (i == -1) {
				break;
			}
			
			sb.append(new String(char6, 0, i));
		}
		
		Document doc = TransTest.xmlTrans(sb.toString());
		
		System.out.println(doc.asXML());
	}
}
