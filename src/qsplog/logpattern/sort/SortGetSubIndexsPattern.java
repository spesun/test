package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;

public class SortGetSubIndexsPattern  extends SortPattern{

	
	public SortGetSubIndexsPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*get subindexs for queryDB spend:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_get_subindexs";
		
		order = LogConstant.order_sort_get_subindexs;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
		
	}


}
