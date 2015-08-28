package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortHighterPattern  extends SortPattern{
	
	public SortHighterPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*highLighter spend:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_highLighter";
		
		order = LogConstant.order_sort_highLighter;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
	}
	
}
