package xml1;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 主要根据xsl.xsl的定义，将xml中的值进行替换
 * 
 * @author zter
 *
 */
public class Train {
    public void test() throws IOException {
        String xmlFileName = "d:/xsl/xml.xml";
        String xslFileName = "d:/xsl/xsl.xsl";
        String htmlFileName = "d:/xsl/html.html";
        Train.Transform(xmlFileName, xslFileName, htmlFileName);
    }

    public static void Transform(String xmlFileName, String xslFileName,
            String htmlFileName) {
        try {
            TransformerFactory tFac = TransformerFactory.newInstance();
            Source xslSource = new StreamSource(xslFileName);
            Transformer t = tFac.newTransformer(xslSource);
            File xmlFile = new File(xmlFileName);
            File htmlFile = new File(htmlFileName);
            
            Source source = new StreamSource(xmlFile);
            
            Result result = new StreamResult(htmlFile);
            System.out.println(result.toString());
            t.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws IOException {
		new Train().test();
	}
}