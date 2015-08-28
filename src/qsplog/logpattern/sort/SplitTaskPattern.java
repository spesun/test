package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;

public class SplitTaskPattern  extends SortPattern{

	
	public SplitTaskPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*split task spend:[\\s]*([0-9]+)[\\s]*.*";
		patternName = "sort_split_task";
		
		order = LogConstant.order_sort_split_task;
		outFileName = getOutFileName();
		p = Pattern.compile(pattern);
		
	}
	
	public static void main(String[] args) {
		//不管的新加的total tasks:
		String s = "2012-02-23 11:42:28,903 INFO [com.zte.qsp.searcher.searchnode.SearchClient.callSearchers(217)] - 10.46.173.141_30split task spend:2 total tasks:1";
		System.out.println(new SplitTaskPattern().match(s));
	
	}

}
