package qsplog.logpattern.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SearchLucenePattern  extends PatternOne{
	
	public SearchLucenePattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*return search Hit. total hits:[\\s]*([0-9]+)[\\s]*[\\s]*spend:([0-9]+)[\\s]*";
		patternName = "search_lucene";
		
		order = LogConstant.order_search_lucene;
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
			map.put("record", m.group(3));
			map.put("cost", m.group(4));
			
			map.put("outFileName", outFileName);
			
			return map;
		} else {
			return null;
		}
		
	}

}
