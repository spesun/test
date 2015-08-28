package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortGetDocsvrPattern  extends SortPattern{
	
	public SortGetDocsvrPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*get from docsvr spend:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_get_docsvr";
		
		order = LogConstant.order_sort_get_docsvr;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
	}
	
}
