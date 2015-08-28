package my.lucene3;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * �ļ�Ŀ¼������(jj) QueryParser���� Field�����ȷ������----��ϵ�������Ĳ�ѯ ����������
 * 
 * 
 * @author Administrator
 * 
 */
public class NumericTest {

	public static String tempDir = "D:\\luceneTest";
	
	/**
	 * ���������ļ�
	 * 
	 * @param indexDir
	 *            �����ļ���ŵ�·��
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	public static void createIndex(String indexDir) throws Exception {
		File indexDirs = new File(indexDir);
		// �������������������ǰ�Document����ӵ�������������ʱ��ֻ�ܶ���������д���������ܶ�ȡ��������
		IndexWriter iwriter = new IndexWriter(FSDirectory.open(indexDirs),
				new StandardAnalyzer(Version.LUCENE_30), true,
				IndexWriter.MaxFieldLength.LIMITED);
		
		
		
		Document doc = new Document(); // Document������Ҫ�����������ĵ����󣨿����Ϊ���ݿ��е�һ����¼��
		NumericField num = new NumericField("number", Field.Store.YES, true).setIntValue(1); // ��������¼�е�һЩ�ֶ�
		doc.add(num);
		iwriter.addDocument(doc);
		
		doc = new Document();
		num = new NumericField("number", Field.Store.YES, true).setIntValue(50); // ��������¼�е�һЩ�ֶ�
		doc.add(num);
		iwriter.addDocument(doc);
		
		 // ���ĵ�������ӵ����������д�������
		iwriter.optimize(); // �Ż�����
		iwriter.close();
		
	}

	/**
	 * 
	 * @param docName
	 *            Ҫ��ѯ��������
	 * @param keyWord
	 *            ����Ĺؼ���
	 * @param indexDir
	 *            �����ļ���Ŀ�
	 */
	public static void serarchIndex(
			String indexDir) {

		String field = "number";
		try {
			Directory dir = FSDirectory.open(new File(indexDir));
			IndexSearcher searcher = new IndexSearcher(dir); // �����������м���
			
			NumericRangeQuery numericRangeQuery = NumericRangeQuery.newIntRange(field, 0, 50, true, true);
			
			Sort sort = new Sort();
			sort.setSort(new SortField(field, SortField.INT, true));
			
			TopDocs top = searcher.search(numericRangeQuery, null, 10, sort );
			
			int size = top.totalHits; // �����ʣ�������
			System.out.println("���и�����" + size);
			
			ScoreDoc[] sdoc = top.scoreDocs;
			printResult(sdoc, field, searcher);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param ts
	 * @param key
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	public static void printResult(ScoreDoc[] ts, String key, IndexSearcher searcher) throws CorruptIndexException, IOException {
		if (ts.length != 0) {
			System.out.println("���� " + key + " һ���ҵ�" + ts.length + "���ĵ�");
			for (int i = 0; i < ts.length; i++) {
				int doc = ts[i].doc;
				System.out.println(ts[i].doc + " = " +  searcher.doc(doc));
				//System.out.println(ts[i].toString());
			}
		}
		// for (int i = 0; i < sdoc.length; i++) {
		// Document doc = searcher.doc(sdoc[i].doc);// new method is.doc()
		// System.out.println(doc.getField("filename") + "   "
		// + sdoc[i].toString() + "  ");
		// }
	}

	public static void main(String[] args) throws Exception {
		NumericTest.createIndex(tempDir);
		NumericTest.serarchIndex(tempDir);
	}
	
	
}
