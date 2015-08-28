package xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.sun.xml.internal.ws.util.xml.StAXResult;

/**
 * 使用JAXP接口处理XML的transform。
 * 
 * 还是老话：好记性不如烂笔头啊。记得以前做过调查的额。补上吧。
 * 
 * @author btpka3@163.com
 */
public class JAXPTest {

    // http://www.ling.helsinki.fi/kit/2004k/ctl257/JavaXSLT/Ch05.html
    // http://xml.apache.org/xalan-j/features.html
    public static void main(String[] args)
            throws TransformerFactoryConfigurationError, TransformerException,
            XMLStreamException {
    	
        TransformerFactory tranFac = TransformerFactory.newInstance();

        String[] feathers = new String[]{
                // DOM
                DOMSource.FEATURE,
                DOMResult.FEATURE,
                // SAX
                SAXTransformerFactory.FEATURE,
                SAXTransformerFactory.FEATURE_XMLFILTER, SAXSource.FEATURE,
                SAXResult.FEATURE,

                // StAX
                // JDK 1.6 所有的feather中仅仅不支持SAXSource
                StAXSource.FEATURE, StAXResult.FEATURE,

                // Stream
                StreamSource.FEATURE, StreamResult.FEATURE};

        for (String feather : feathers) {
            boolean supported = tranFac.getFeature(feather);
            System.out.println(feather + " = " + supported);
        }

        // 如果使用XLST，则请使用以下几句。
        //System.out.println();
        //Source xlstSource = new StreamSource(new StringReader(""));
        //Templates template = tranFac.newTemplates(xlstSource);
        //Transformer transformer = template.newTransformer();


        Transformer transformer = tranFac.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
        StreamResult result = new StreamResult(new StringWriter());

        String xmlStr = "<?xml version='1.0' encoding='GBK'?>"
                + "<exam xmlns=\"http://test.me/\">"
                + "<person birthday=\"1985-07-31+08:00\" sex=\"男\" name=\"Name1\" id=\"041110101\"><score subject=\"s1\">85.0</score><score subject=\"s2\">86.0</score><score subject=\"s3\">87.0</score></person>"
                + "<person birthday=\"1985-08-01+08:00\" sex=\"男\" name=\"Name2\" id=\"041110102\"><score subject=\"s1\">85.0</score><score subject=\"s2\">86.0</score><score subject=\"s3\">87.0</score></person>"
                + "<person birthday=\"1985-08-02+08:00\" sex=\"女\" name=\"Name3\" id=\"041110103\"><score subject=\"s1\">85.0</score><score subject=\"s2\">86.0</score><score subject=\"s3\">87.0</score></person>"
                + "</exam>";

        Source src = new StreamSource(new StringReader(xmlStr));
        transformer.transform(src, result);

        String xmlString = result.getWriter().toString();
        System.out.println(xmlString);
    }
}

