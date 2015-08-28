package my.lucene;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.mira.lucene.analysis.IK_CAnalyzer;

public class QueryParseTest {

	public static void main(String[] args) throws ParseException {
		QueryParser qp = new QueryParser("aa", new IK_CAnalyzer());
		
		Query q = qp.parse("tt:中兴^4");
		System.out.println(q.toString());
		System.out.println(q.getClass());
	
		q = qp.parse("(tt:中兴技术)^4 (00:中兴)^5 ");
		System.out.println(q.toString());
		System.out.println(q.getClass());
		
		
		
		Term t = new Term("tt", "中兴");
		TermQuery query = new TermQuery(t);
		query.setBoost(4);
		
		
		/*Term o = new Term("oo", "中兴");
		TermQuery queryo = new TermQuery(o);
		queryo.setBoost(5);
		
		BooleanQuery bq = new BooleanQuery();
		bq.add(query, Occur.SHOULD);
		bq.add(queryo, Occur.SHOULD);
		
		System.out.println(bq.toString());*/
		
	}
}
