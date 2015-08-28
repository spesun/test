

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogNew1 {
	
	static {
		Logger myTest = Logger.getLogger("myTest");  
		  
		Layout layout = new PatternLayout("%d %p [%c.%M(%L)] - %m%n");  
		  
		Appender appender;
		try {
			appender = new FileAppender(layout, "d:/test.log");
			myTest.addAppender(appender); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		 
	}

	
	public static void main(String[] args) throws Exception {
		Log log = LogFactory.getLog("myTest");
		
		for (int i = 0; i < 100; i++) {
			Thread.currentThread().sleep(1000);
			log.error(22222);
		}
	}
}
