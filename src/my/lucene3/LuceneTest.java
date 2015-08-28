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
 * 文件目录有问题(jj) QueryParser对象 Field域的正确的设置----关系到索引的查询 和性能问题
 * 
 * 
 * @author Administrator
 * 
 */
public class LuceneTest {

	public static String tempDir = "D:\\luceneTest";
	
	/**
	 * 创建索引文件
	 * 
	 * @param indexDir
	 *            索引文件存放的路径
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	public static void createIndex(String indexDir) throws Exception {
		File indexDirs = new File(indexDir);
		// 创建索引；他的作用是把Document对象加到索引中来（此时他只能对索引进行写操作，不能读取或搜索）
		IndexWriter iwriter = new IndexWriter(FSDirectory.open(indexDirs),
				new StandardAnalyzer(Version.LUCENE_30), true,
				IndexWriter.MaxFieldLength.LIMITED);
		
		
		
		Document doc = new Document(); // Document是描述要创建索引的文档对象（可理解为数据库中的一条记录）
		Field title = new Field("title", "driving", Field.Store.YES,Field.Index.ANALYZED); // 是这条记录中的一些字段
		//Field title1 = new Field("title", "新浪", Field.Store.YES,Field.Index.ANALYZED); // 是这条记录中的一些字段
		Field path = new Field("path", "www.baidu.com", Field.Store.YES,Field.Index.NOT_ANALYZED);
		/*doc.add(path); // 添加到文档中去
		doc.add(title);
		iwriter.addDocument(doc);*/
		
		for (int i = 0; i < 100000; i++) {
			doc = new Document(); // Document是描述要创建索引的文档对象（可理解为数据库中的一条记录）
			title = new Field("title", "新浪", Field.Store.YES,Field.Index.ANALYZED); // 是这条记录中的一些字段
			path = new Field("path", "从内存中flush到硬盘上是按照设置的内存大小来DocumentsWriter.ramBufferSize触发的，每次flush会生成一个segments，与hbase类似都是生成新的。ramBufferSize默认值为16M。索引比较大的话，segments就会比较多。后面的合并耗时较多", Field.Store.NO,Field.Index.ANALYZED);
			doc.add(path); // 添加到文档中去
			doc.add(title);
			
			path = new Field("path", "从内存中flush到硬盘上是按照设置的内存大小来DocumentsWriter.ramBufferSize触发的，每次flush会生成一个segments，与hbase类似都是生成新的。ramBufferSize默认值为16M。索引比较大的话，segments就会比较多。后面的合并耗时较多", Field.Store.NO,Field.Index.ANALYZED);
			doc.add(path); // 添加到文档中去
			
			iwriter.addDocument(doc);
		}
		
		
		/*doc = new Document(); // Document是描述要创建索引的文档对象（可理解为数据库中的一条记录）
		title = new Field("title", "新浪", Field.Store.YES,Field.Index.NOT_ANALYZED); // 是这条记录中的一些字段
		path = new Field("path", "www.siana.com", Field.Store.YES,Field.Index.NOT_ANALYZED);
		doc.add(path); // 添加到文档中去
		doc.add(title);
		iwriter.addDocument(doc);
		
		doc = new Document(); // Document是描述要创建索引的文档对象（可理解为数据库中的一条记录）
		title = new Field("title", "新浪", Field.Store.YES,Field.Index.NOT_ANALYZED); // 是这条记录中的一些字段
		path = new Field("path", "", Field.Store.YES,Field.Index.NOT_ANALYZED);
		
		doc.add(path); // 添加到文档中去
		doc.add(title);
		iwriter.addDocument(doc);*/
		
			
		 // 将文档对象添加到索引对象中创建索引
		iwriter.optimize(); // 优化索引
		iwriter.close();
		
	}

	/**
	 * 
	 * @param docName
	 *            要查询的索引名
	 * @param keyWord
	 *            输入的关键字
	 * @param indexDir
	 *            索引文件的目錄
	 */
	public static void serarchIndex(String docName, String keyWord,
			String indexDir) {
		Query query = null;
		QueryParser qparser = null;
		try {
			Directory dir = FSDirectory.open(new File(indexDir));
			IndexSearcher searcher = new IndexSearcher(dir); // 来对索引进行检索
			qparser = new QueryParser(Version.LUCENE_20, docName,
					new StandardAnalyzer(Version.LUCENE_CURRENT)); // 将要搜索的对象解析成Query对象
			query = qparser.parse(keyWord); // 对关键字进行解析
			System.out.println(query);
			TopDocs top = searcher.search(query, null, 10);
			
			int size = top.totalHits; // 命中率（个数）
			System.out.println("命中个数：" + size);
			
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
			System.out.println("搜索 " + key + " 一共找到" + ts.length + "个文档");
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
		LuceneTest.serarchIndex("path", "多从", tempDir);
		//wildTest();
		//EmptyTest();
		//System.out.println(Integer.toString('?', 16));
		//System.out.println("\u003f");
	}
	
	public static void EmptyTest() throws Exception {
		Directory dir = FSDirectory.open(new File(tempDir));
		IndexSearcher searcher = new IndexSearcher(dir); // 来对索引进行检索
		
		QueryParser p = new QueryParser(Version.LUCENE_36, "aa",new KeywordAnalyzer());
		Query query = p.parse("title:新浪 +path:\"\"");
		
		/*
		TermQuery path = new TermQuery(new Term("path", ""));
		TermQuery title = new TermQuery(new Term("title", "新浪"));
		
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
		IndexSearcher searcher = new IndexSearcher(dir); // 来对索引进行检索
		
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
