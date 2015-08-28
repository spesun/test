package qsplog.logpattern.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class GetLuceneQueryPattern  extends SearchPattern{
	
	public GetLuceneQueryPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*lucene query condition spend:([0-9]+)[\\s]*.*";
		patternName = "search_get_lucene_query";
		
		order = LogConstant.order_search_get_lucene_query;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);

	}
	
	
	public static void main(String[] args) {
	
		String s = "2012-02-24 15:52:01,926 INFO [com.zte.qsp.searcher.searchnode.Searcher.search(340)] - 10.46.173.141_2 lucene query condition spend:1531 query:((food_name:À·≤À))";
		System.out.println(new GetLuceneQueryPattern().match(s));
	}

}
