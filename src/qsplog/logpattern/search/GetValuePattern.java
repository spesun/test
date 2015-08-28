package qsplog.logpattern.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class GetValuePattern  extends SearchPattern{
	
	public GetValuePattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*getValue spend:([0-9]+)[\\s]*";
		patternName = "search_getValue";
		
		order = LogConstant.order_search_getValue;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);

		
	}
	
	
	
	public static void main(String[] args) {
		String s = "2012-02-29 09:32:08,193 INFO [com.zte.qsp.searcher.searchnode.Searcher.search(424)] - 10.46.173.171_1 getValue spend:159";
		System.out.println(new GetValuePattern().match(s));
	}

}
