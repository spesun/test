package my.lucene3;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
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
public class LuceneTest {

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
		Field title = new Field("title", "driving", Field.Store.YES,Field.Index.ANALYZED); // ��������¼�е�һЩ�ֶ�
		//Field title1 = new Field("title", "����", Field.Store.YES,Field.Index.ANALYZED); // ��������¼�е�һЩ�ֶ�
		Field path = new Field("path", "www.baidu.com", Field.Store.YES,Field.Index.NOT_ANALYZED);
		/*doc.add(path); // ��ӵ��ĵ���ȥ
		doc.add(title);
		iwriter.addDocument(doc);*/
		
		for (int i = 0; i < 100000; i++) {
			doc = new Document(); // Document������Ҫ�����������ĵ����󣨿����Ϊ���ݿ��е�һ����¼��
			title = new Field("title", "����", Field.Store.YES,Field.Index.ANALYZED); // ��������¼�е�һЩ�ֶ�
			path = new Field("path", "���ڴ���flush��Ӳ�����ǰ������õ��ڴ��С��DocumentsWriter.ramBufferSize�����ģ�ÿ��flush������һ��segments����hbase���ƶ��������µġ�ramBufferSizeĬ��ֵΪ16M�������Ƚϴ�Ļ���segments�ͻ�Ƚ϶ࡣ����ĺϲ���ʱ�϶�", Field.Store.NO,Field.Index.ANALYZED);
			doc.add(path); // ��ӵ��ĵ���ȥ
			doc.add(title);
			
			path = new Field("path", "���ڴ���flush��Ӳ�����ǰ������õ��ڴ��С��DocumentsWriter.ramBufferSize�����ģ�ÿ��flush������һ��segments����hbase���ƶ��������µġ�ramBufferSizeĬ��ֵΪ16M�������Ƚϴ�Ļ���segments�ͻ�Ƚ϶ࡣ����ĺϲ���ʱ�϶�", Field.Store.NO,Field.Index.ANALYZED);
			doc.add(path); // ��ӵ��ĵ���ȥ
			
			iwriter.addDocument(doc);
		}
		
		
		/*doc = new Document(); // Document������Ҫ�����������ĵ����󣨿����Ϊ���ݿ��е�һ����¼��
		title = new Field("title", "����", Field.Store.YES,Field.Index.NOT_ANALYZED); // ��������¼�е�һЩ�ֶ�
		path = new Field("path", "www.siana.com", Field.Store.YES,Field.Index.NOT_ANALYZED);
		doc.add(path); // ��ӵ��ĵ���ȥ
		doc.add(title);
		iwriter.addDocument(doc);
		
		doc = new Document(); // Document������Ҫ�����������ĵ����󣨿����Ϊ���ݿ��е�һ����¼��
		title = new Field("title", "����", Field.Store.YES,Field.Index.NOT_ANALYZED); // ��������¼�е�һЩ�ֶ�
		path = new Field("path", "", Field.Store.YES,Field.Index.NOT_ANALYZED);
		
		doc.add(path); // ��ӵ��ĵ���ȥ
		doc.add(title);
		iwriter.addDocument(doc);*/
		
			
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
	public static void serarchIndex(String docName, String keyWord,
			String indexDir) {
		Query query = null;
		QueryParser qparser = null;
		try {
			Directory dir = FSDirectory.open(new File(indexDir));
			IndexSearcher searcher = new IndexSearcher(dir); // �����������м���
			qparser = new QueryParser(Version.LUCENE_20, docName,
					new StandardAnalyzer(Version.LUCENE_CURRENT)); // ��Ҫ�����Ķ��������Query����
			query = qparser.parse(keyWord); // �Թؼ��ֽ��н���
			System.out.println(query);
			TopDocs top = searcher.search(query, null, 10);
			
			int size = top.totalHits; // �����ʣ�������
			System.out.println("���и�����" + size);
			
			ScoreDoc[] sdoc = top.scoreDocs;
			printResult(sdoc, keyWord, searcher);
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
		
		LuceneTest.createIndex(tempDir);
		LuceneTest.serarchIndex("path", "���", tempDir);
		//wildTest();
		//EmptyTest();
		//System.out.println(Integer.toString('?', 16));
		//System.out.println("\u003f");
	}
	
	public static void EmptyTest() throws Exception {
		Directory dir = FSDirectory.open(new File(tempDir));
		IndexSearcher searcher = new IndexSearcher(dir); // �����������м���
		
		QueryParser p = new QueryParser(Version.LUCENE_36, "aa",new KeywordAnalyzer());
		Query query = p.parse("title:���� +path:\"\"");
		
		/*
		TermQuery path = new TermQuery(new Term("path", ""));
		TermQuery title = new TermQuery(new Term("title", "����"));
		
		BooleanQuery query = new BooleanQuery();
		query.add( title, BooleanClause.Occur.MUST ); 
		query.add(path, BooleanClause.Occur.MUST_NOT);*/
		
		System.out.println(query.toString() + "=" + query.getClass());
		TopDocs top = searcher.search(query, 1000);
		ScoreDoc[] sdoc = top.scoreDocs;
		System.out.println(sdoc.length);
		printResult(sdoc, "path", searcher);
	}
	
	
	public static void wildTest() throws Exception {
		Directory dir = FSDirectory.open(new File(tempDir));
		IndexSearcher searcher = new IndexSearcher(dir); // �����������м���
		
		//WildcardQuery query = new WildcardQuery(new Term("path", ("www." + "si\u003fna" + "*" )));
		QueryParser p = new QueryParser(Version.LUCENE_36, "aa",new KeywordAnalyzer());
		Query query = p.parse("path:www." + "s*i\\?na.com" );
		System.out.println(query.toString() + "=" + query.getClass());
		TopDocs top = searcher.search(query, 1000);
		ScoreDoc[] sdoc = top.scoreDocs;
		System.out.println(sdoc.length);
		printResult(sdoc, "path", searcher);
	}
}
