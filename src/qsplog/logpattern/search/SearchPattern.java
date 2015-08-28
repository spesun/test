package qsplog.logpattern.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SearchPattern  extends PatternOne{
	
	public SearchPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*searchnode total spend:([0-9]+)[\\s]*";
		patternName = "search";
		
		order = LogConstant.order_search;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);

	}
	
	@Override
	public Map match(String str) {
		
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		
		if (b){
			Map map = new LinkedHashMap();

			//map.put("fileName", fileName);	
			map.put("time", ("\""+m.group(1).trim()+"\""));	
			map.put("threadNum", m.group(2));	
			map.put("cost", m.group(3));
			
			map.put("outFileName", outFileName);
			
			return map;
		} else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		String s = "2012-02-25 17:42:52,412 INFO [com.zte.qsp.searcher.searchnode.Searcher.search(439)] - 10.46.173.141_23 searchnode total spend:163";
		System.out.println(new SearchPattern().match(s));
	}

}
