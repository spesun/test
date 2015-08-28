package time;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class DelTest {

	public static String deletedPath = "/home/qsp/index/del";
	public static String copyPath = "/home/qsp/index/copy";

	public static File deletedFile = new File(deletedPath);

	static Object syn = new Object();

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		
		long i=0;
		while (true) {
			 File copyFile = new File(copyPath);
			 
			 long start = System.currentTimeMillis();
			 FileUtils.copyDirectory(copyFile, deletedFile);
			 long end = System.currentTimeMillis();
			 System.out.println("copy spend = " + (end-start));
			 
			 start = System.currentTimeMillis();
			 FileUtils.deleteDirectory(deletedFile);
			 end = System.currentTimeMillis();
			 System.out.println("delete spend = " + (end-start));
			 //System.out.println("delete");
			 
			 Thread.currentThread().sleep(10);
			 i++;
			 System.out.println(i);
		}
			 
		//FileUtils.deleteDirectory(deletedFile);

	}
}
