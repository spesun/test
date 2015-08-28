package my.lucene3;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class MutiSearchTest {

	static  String index_dir_pre = "D:/iptvqsengine/indexnode/CHANNELINFO/index";
	static long total = 0;
	public static void main(String[] args) throws CorruptIndexException, IOException {
		
		int size = 10;
		for (int i = 0; i < size; i++) {
			testMutiSearch();
		}

		System.out.println("total= " + total);
		System.out.println("everage= " + total/size);
		
	}
	
	
	public static void testMutiSearch() throws CorruptIndexException, IOException {
		int size = 10;
		IndexSearcher[] searchers = new IndexSearcher[size];
		for (int i = 0; i < size; i++) {
			
			File f = new File(index_dir_pre + i + "/main");
			Directory dir = FSDirectory.open(f);
			
			//RAMDirectory ramdir = new RAMDirectory(dir);
			IndexSearcher indexSearcher = new IndexSearcher(dir);  
			
			searchers[i] = indexSearcher;
		}
		

		//Searcher s = new ParallelMultiSearcher(searchers);
		Searcher s = new MultiSearcher(searchers);
		
		Sort sort = new Sort();  
        SortField sortField = new SortField("food_name", SortField.STRING, false);  
        sort.setSort(sortField); 
		TermQuery tq = new TermQuery(new Term("food_name", "Ëá²Ë"));
		
		long start = System.currentTimeMillis();
		TopDocs topDocs = s.search(tq, null, 10, sort);
		long end = System.currentTimeMillis();
		long t = end-start;
		System.out.println("t = " + t);
		total = total +t;
		
        ScoreDoc [] scoreDoc = topDocs.scoreDocs; 
		for (int i = 0; i < scoreDoc.length; i++) {
			//System.out.println(scoreDoc[i].doc);
			//Document d = s.doc(scoreDoc[i].doc);  
	        //System.out.println(d);  
		}
		 
	}
}
