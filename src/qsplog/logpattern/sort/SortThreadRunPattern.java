package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;

@Deprecated
public class SortThreadRunPattern  extends SortPattern{

	
	public SortThreadRunPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*TimeoutSearchRunner search has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_thread_run";
		
		order = LogConstant.order_sort_thread_run;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
		
	}


}
