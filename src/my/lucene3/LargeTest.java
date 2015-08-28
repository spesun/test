package my.lucene3;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ztesec.owls.collect.plugin.OwlsTikaUtil;

public class LargeTest {
	public static String tempDir = "D:\\luceneTest";

	/**
	 * @param args
	 * @throws IOException 
	 * @throws LockObtainFailedException 
	 * @throws CorruptIndexException 
	 */
	public static void main(String[] args) throws Exception {
		File indexDirs = new File(tempDir);
		// 创建索引；他的作用是把Document对象加到索引中来（此时他只能对索引进行写操作，不能读取或搜索）
		IndexWriter iwriter = new IndexWriter(FSDirectory.open(indexDirs),
				new IKAnalyzer(), true,
				IndexWriter.MaxFieldLength.LIMITED);
		
		
		String value="D:/mailtest/LXX1123125050029001100100330531813896220121226115137000000012304_0101_0002.eml";
    	File file = new File(value);
		URL url = file.toURI().toURL();
		
		String str ;
		str = OwlsTikaUtil.getMailContent(url, "utf-8");
		
		for (int i = 0; i < 10000; i++) {
			Document doc = new Document(); // Document是描述要创建索引的文档对象（可理解为数据库中的一条记录）
			Field title = new Field("title", str
					, Field.Store.NO,Field.Index.ANALYZED); // 是这条记录中的一些字段
			doc.add(title);
			iwriter.addDocument(doc);
		}
		
		iwriter.commit();
		iwriter.close();

	}

}
