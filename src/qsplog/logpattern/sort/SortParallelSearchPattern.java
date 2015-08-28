package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;

public class SortParallelSearchPattern  extends SortPattern{

	
	public SortParallelSearchPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*parallel search hits complete. millisecond:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_parallel_search";
		order = LogConstant.order_sort_parallel_search;
		
		outFileName = getOutFileName();
		p = Pattern.compile(pattern);
		
	}


}
