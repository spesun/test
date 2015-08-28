package my.lucene3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import my.util.FileUtil;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class LuceneCount {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		File in = new File("C:\\Documents and Settings\\zter\\×ÀÃæ\\task1");
		List<File> all = FileUtil.getDirectorys(in);
		
		System.out.println(all);
		
		long sum=0;
		for (int i = 0; i < all.size(); i++) {
			
			String fileName = (String)all.get(i).getAbsolutePath();
			if (fileName.endsWith("main")) {
				File f =  all.get(i);
				Directory dir = FSDirectory.open(f);
				RAMDirectory ramdir = new RAMDirectory(dir);
				IndexSearcher indexSearcher = new IndexSearcher(ramdir);  
				
				int maxDoc = indexSearcher.maxDoc();
				System.out.println(all.get(i));
				for (int j = 0; j < maxDoc; j++) {
					System.out.println("    " + indexSearcher.doc(j));
				}
				
				sum = sum + maxDoc;
			}
			
		}
		
		System.out.println("sum = " + sum);
		
	}

	
}
