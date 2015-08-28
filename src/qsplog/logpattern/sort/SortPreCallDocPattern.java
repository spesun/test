package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortPreCallDocPattern  extends SortPattern{
	
	public SortPreCallDocPattern() {
		
		pattern = "[\\s]*([0-9,: -]+)(.*)prepare call doc:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_order_sort_prepare_get_doc";
		
		order = LogConstant.order_sort_prepare_get_doc;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
	}
	
}
