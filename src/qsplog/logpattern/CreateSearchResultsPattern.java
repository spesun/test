package qsplog.logpattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.sort.SortPattern;

public class CreateSearchResultsPattern  extends SortPattern{

	
	public CreateSearchResultsPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*create SearchResults by hits, spend[\\s]*([0-9]+)[\\s]*";
		patternName = "createSearchResults";
		
		order = LogConstant.order_sort_createSearchResults;
		outFileName = getOutFileName();

		p = Pattern.compile(pattern);
	}


}
