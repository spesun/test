package qsplog.logpattern.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

@Deprecated 
public class PreparePattern  extends SearchPattern{
	
	public PreparePattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*prepare for search spend:([0-9]+)[\\s]*";
		patternName = "search_prepare";
		
		order = LogConstant.order_search_prepare;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);

		
	}
	

}
