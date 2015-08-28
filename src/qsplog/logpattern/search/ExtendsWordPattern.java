package qsplog.logpattern.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

@Deprecated
public class ExtendsWordPattern  extends SearchPattern{
	
	public ExtendsWordPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*extendsWord spend:([0-9]+)[\\s]*";
		patternName = "search_extendsWord";
		
		order = LogConstant.order_search_extendsWord;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);

		
	}
	
	
	
	public static void main(String[] args) {
		String s = "2012-02-24 12:12:01,567 INFO [com.zte.qsp.searcher.searchnode.Searcher.search(421)] - 10.46.173.141_4 getValue spend:131";
		System.out.println(new ExtendsWordPattern().match(s));
	}

}
