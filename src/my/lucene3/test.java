package my.lucene3;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class test {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
		IndexSearcher[] searchers = new IndexSearcher[1];
		
		File f = new File("C:/Documents and Settings/Administrator/桌面/back");
		Directory dir = FSDirectory.open(f);
		IndexSearcher mainSearcher = new IndexSearcher(dir); 
		searchers[0] = mainSearcher;
		
		
		MultiSearcher multiSearcher = new MultiSearcher(searchers);
		QueryParser p = new QueryParser(Version.LUCENE_36, "aa"
				,new KeywordAnalyzer());
		
		Query query = p.parse("(+(((capturetime:[1194796800 TO 1355241599]))) +(((mainfile:你好) mainfile:\"你 好\" str_src_ip:你好 str_dst_ip:你好 content:\"你 好\")))");
	
		TopDocs topDocs = multiSearcher.search(query, 100);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		int hitSize = scoreDocs.length;
		
		 for (int i = 0; i < hitSize; i++)
		 {
			 int n = multiSearcher.subSearcher(scoreDocs[i].doc);
			 System.out.println(scoreDocs[i].doc + "=" + scoreDocs[i].score);
			 //System.out.println(n + "=" +multiSearcher.doc(scoreDocs[i].doc));
		 
		 }
		 
		 /*System.out.println(multiSearcher.subSearcher(2147483647));
		 System.out.println(Integer.MAX_VALUE);*/
	}

}
