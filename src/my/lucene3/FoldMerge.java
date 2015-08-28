package my.lucene3;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ztesec.owls.collect.plugin.OwlsTikaUtil;

public class FoldMerge {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws LockObtainFailedException 
	 * @throws CorruptIndexException 
	 */
	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException {
		String d1 = "D:\\luceneTest\\1";
		String d2 = "D:\\luceneTest\\2";
		String d3 = "D:\\luceneTest\\3";
		
		
		String main = "D:\\luceneTest\\main";

		long start = System.currentTimeMillis();
		System.out.println("start = " + start);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, new IKAnalyzer());
		
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		
		//LogDocMergePolicy t = new LogDocMergePolicy();
		LogByteSizeMergePolicy t = new LogByteSizeMergePolicy();
	    t.setUseCompoundFile(true);
		t.setNoCFSRatio(1.0D);
		
		//t.setMaxMergeMB(1024);
		//t.setMergeFactor(2);
		
		
		config.setMergePolicy(t);
		IndexWriter iwriter =  new IndexWriter(FSDirectory.open(new File(main)), config);
		
		//IndexReader[] readers = new IndexReader[2];
		
		FSDirectory dir = FSDirectory.open(new File(d1));
		iwriter.addIndexes(IndexReader.open(dir));
		
		dir = FSDirectory.open(new File(d2));
		iwriter.addIndexes(IndexReader.open(dir));
		
		dir = FSDirectory.open(new File(d3));
		iwriter.addIndexes(IndexReader.open(dir));
		
		iwriter.commit();
		
		long end = System.currentTimeMillis();
		System.out.println("end = " + end);
		System.out.println((end - start) / 1000);
	}

}
