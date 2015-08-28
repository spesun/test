package my.lucene;

import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;

public class MyTestTermQuery {

public static Searcher searcher =null;
	
	static {
		try {
			searcher = new IndexSearcher("C:/home/qs/indexnode/klallnode/index/main");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Term t = new Term("nodetitle", "Ì×");
		Query q = new TermQuery(t);
		
		System.out.println(q.toString());
		ScoreDoc[] docs = searcher.search(q, null ,100).scoreDocs;
		for (int i = 0; i < docs.length; i++) {
			System.out.println(searcher.doc(docs[i].doc).get("nodetitle"));
		}
	}
	
}
