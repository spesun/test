package my.lucene3;
import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class TestLucene12580 {

	public static void main(String[] args) throws Exception {

		/*QueryParser qp = new QueryParser(Version.LUCENE_30, "aa", new IKAnalyzer());
		
		Query q = qp.parse("(+(+(+(gdsclassid:2) +((+(pricelow:[n0.0 TO o12.0]) +pricehigh:[n0.0 TO o12.0]))) +(other:1)) +vcid:1)");*/
		
		Directory d = FSDirectory.open(new File("d:/main"));
		IndexSearcher search = new IndexSearcher(d);
		
		TermRangeQuery rangeQ = new TermRangeQuery("pricehigh", "n1.0","o12.0", true,true);
		
		System.out.println(rangeQ.toString());
		Sort sort = new Sort();
		SortField sf = new SortField("pricehigh", SortField.STRING, false);
		sort.setSort(sf);
		
		ScoreDoc[] docs = search.search(rangeQ, null, 99999, sort).scoreDocs;
		
		System.out.println("result");
		for (int i = 0; i < docs.length; i++) {
			Document doc = search.doc(docs[i].doc);
			
			//System.out.print(doc.get("goodsname"));
			System.out.print("\t\t" + doc.get("pricehigh"));
			System.out.println();
		}
	}
}
