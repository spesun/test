package my.lucene3;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogDocMergePolicy;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ztesec.owls.collect.plugin.OwlsTikaUtil;

public class LargeTestMerger {
	public static String tempDir = "D:\\luceneTest\\2";

	/**
	 * @param args
	 * @throws IOException 
	 * @throws LockObtainFailedException 
	 * @throws CorruptIndexException 
	 */
	public static void main(String[] args) throws Exception {
		File indexDirs = new File(tempDir);
		// �������������������ǰ�Document����ӵ�������������ʱ��ֻ�ܶ���������д���������ܶ�ȡ��������
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, new IKAnalyzer());
		
		config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
		config.setRAMBufferSizeMB(1);
		
		String value="D:/mailtest/LXX1123125050029001100100330531813896220121226115137000000012304_0101_0002.eml";
    	File file = new File(value);
		URL url = file.toURI().toURL();
		
		String str ;
		str = OwlsTikaUtil.getMailContent(url, "utf-8");
		
		LogDocMergePolicy t = new LogDocMergePolicy();
		//LogByteSizeMergePolicy t = new LogByteSizeMergePolicy();
	    //t.setUseCompoundFile(true);
		t.setNoCFSRatio(1.0D);
		
		//t.setMaxMergeMB(1024);
		t.setMergeFactor(2);
		
		
		config.setMergePolicy(t);
		IndexWriter iwriter =  new IndexWriter(FSDirectory.open(indexDirs), config);
		
		long start = System.currentTimeMillis();
		System.out.println("start = " + start);
		for (int i = 0; i < 1000; i++) {
			Document doc = new Document(); // Document������Ҫ�����������ĵ����󣨿����Ϊ���ݿ��е�һ����¼��
			Field title = new Field("title", str
					, Field.Store.NO,Field.Index.ANALYZED); // ��������¼�е�һЩ�ֶ�
			doc.add(title);
			iwriter.addDocument(doc);
		}
		
		System.out.println(" merge start");
		iwriter.forceMerge(1, true);
		iwriter.commit();
		iwriter.close();
		
		long end = System.currentTimeMillis();
		System.out.println("end = " + end);
		System.out.println((end - start) / 1000);
	}

}
