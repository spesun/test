package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;

public class SortSearchClientPattern  extends SortPattern{

	
	public SortSearchClientPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*search indexdb spend:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_search_client";
		
		order = LogConstant.order_sort_search_client;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
		
	}


}
