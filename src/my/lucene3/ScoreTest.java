package my.lucene3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class ScoreTest {

	static String index_dir_pre = "C:/Documents and Settings/zter/×ÀÃæ/food/subindex";

	public static void main(String[] args) throws Exception {

		List<IndexSearcher> list = new ArrayList();
		for (int i : new int[] { 0,2 }) {
			File f = new File(index_dir_pre + i + "/main");
			Directory dir = FSDirectory.open(f);
			RAMDirectory ramdir = new RAMDirectory(dir);
			IndexSearcher indexSearcher0 = new IndexSearcher(ramdir);
			list.add(indexSearcher0);

		}

		TermQuery tq = new TermQuery(new Term("food_name", "Ëá²Ë"));
		
		TermQuery tq1 = new TermQuery(new Term("food_id", "q9252"));
		
		BooleanQuery bl = new BooleanQuery();
		bl.add(tq, Occur.MUST);
		bl.add(tq1, Occur.MUST);
		
		Searcher s = new MultiSearcher(list.toArray(new IndexSearcher[0]));
		TopDocs topDocs = s.search(bl, 100);
		ScoreDoc[] scoreDoc = topDocs.scoreDocs;
		for (int i = 0; i < scoreDoc.length; i++) {
			System.out.println(scoreDoc[i].score);
			Document d = s.doc(scoreDoc[i].doc);
			System.out.println(d);
		}

	}
}
