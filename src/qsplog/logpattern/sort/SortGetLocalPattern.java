package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortGetLocalPattern  extends SortPattern{
	
	public SortGetLocalPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*get from local spend:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_get_local";
		
		order = LogConstant.order_sort_get_local;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
	}
	
}
