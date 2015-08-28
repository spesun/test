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
 * 文件目录有问题(jj) QueryParser对象 Field域的正确的设置----关系到索引的查询 和性能问题
 * 
 * 
 * @author Administrator
 * 
 */
public class NumericTest {

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
		NumericField num = new NumericField("number", Field.Store.YES, true).setIntValue(1); // 是这条记录中的一些字段
		doc.add(num);
		iwriter.addDocument(doc);
		
		doc = new Document();
		num = new NumericField("number", Field.Store.YES, true).setIntValue(50); // 是这条记录中的一些字段
		doc.add(num);
		iwriter.addDocument(doc);
		
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
	public static void serarchIndex(
			String indexDir) {

		String field = "number";
		try {
			Directory dir = FSDirectory.open(new File(indexDir));
			IndexSearcher searcher = new IndexSearcher(dir); // 来对索引进行检索
			
			NumericRangeQuery numericRangeQuery = NumericRangeQuery.newIntRange(field, 0, 50, true, true);
			
			Sort sort = new Sort();
			sort.setSort(new SortField(field, SortField.INT, true));
			
			TopDocs top = searcher.search(numericRangeQuery, null, 10, sort );
			
			int size = top.totalHits; // 命中率（个数）
			System.out.println("命中个数：" + size);
			
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
		NumericTest.createIndex(tempDir);
		NumericTest.serarchIndex(tempDir);
	}
	
	
}
