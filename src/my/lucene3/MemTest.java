package my.lucene3;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class MemTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String path = "D:\\iptvqsengine\\indexnode\\CHANNELINFO\\index\\main";
		if (args.length > 0) {
			path = args[0];
		}

		File f = new File(path);
		
		Runtime runtime = Runtime.getRuntime();
		long s = runtime.totalMemory()-runtime.freeMemory();
		
		Directory dir = FSDirectory.open(f);
		RAMDirectory ramdir = new RAMDirectory(dir);
		IndexSearcher indexSearcher = new IndexSearcher(ramdir); 
		System.out.println("maxDoc = "+indexSearcher.maxDoc());
		
		long start = System.currentTimeMillis();
		indexSearcher.close();
		long end = System.currentTimeMillis();
		System.out.println("totle =" + (end - start));
		
		
		long e = runtime.totalMemory()-runtime.freeMemory();
		System.out.println("used = " + (e - s));
		
	}

}
