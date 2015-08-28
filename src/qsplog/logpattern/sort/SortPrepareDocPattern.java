package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortPrepareDocPattern  extends SortPattern{
	
	public SortPrepareDocPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*prepare dcache spend:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_prepare_doc";
		
		order = LogConstant.order_sort_prepare_doc;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
	}
	
}
