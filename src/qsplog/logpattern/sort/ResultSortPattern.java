package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;

public class ResultSortPattern  extends SortPattern{

	
	public ResultSortPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*sort with hit array. spend=[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_result_sort";
		
		order = LogConstant.order_sort_result_sort;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
		
	}


}
