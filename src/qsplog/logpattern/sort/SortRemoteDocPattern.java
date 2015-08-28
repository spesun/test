package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortRemoteDocPattern  extends SortPattern{
	
	public SortRemoteDocPattern() {
		
		pattern = "[\\s]*([0-9,: -]+)(.*)getRemoteDoc  spend:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_remote_doc";
		
		order = LogConstant.order_sort_remote_doc;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
	}
	
}
