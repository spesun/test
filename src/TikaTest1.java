

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.parser.CompositeParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class TikaTest1 {

	
	static String path = "d:/1.txt";
	
	public static void main(String[] args) throws Exception {
		
		TikaConfig tikaConfig = TikaConfig.getDefaultConfig();
		
		
		MimeTypes mimeTypes = tikaConfig.getMimeRepository();
		InputStream in = new FileInputStream(path);
		MimeType mimeType = tikaConfig.getMimeRepository().getMimeType(path);
		
		ContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		
		if (mimeType != null && !mimeType.equals(""))
		{
			metadata.set(Metadata.CONTENT_TYPE, mimeType.toString());
		}
		else
		{
			//mimeType = mimeTypes.detect(in, metadata);
			//metadata.set(Metadata.CONTENT_TYPE, mimeType.toString());
		}
		
		
		Parser parser = tikaConfig.getParser(MediaType.parse(mimeType.toString()));
		if (parser == null)
		{
			throw new TikaException("can not get Parser by mimeType="
					+ mimeType.toString());
		}
		System.out.println(parser.toString());
		parser.parse(in, handler, metadata, null);
		System.out.println(handler.toString());
		
		
		
		
	}
}
