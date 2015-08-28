package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortCallThreadPattern  extends PatternOne{

	
	public SortCallThreadPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).*sort call Thread has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_call_thread";
		
		order = LogConstant.order_sort_call_thread;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);
		
	}

	

	@Override
	public Map match(String str) {
		
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		
		if (b){
			Map map = new LinkedHashMap();
			//map.put("fileName", fileName);	
			map.put("time", ("\""+m.group(1).trim()+"\""));	
			map.put("cost", m.group(2));
			
			map.put("outFileName", outFileName);
			
			return map;
		} else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		
		String s = "2012-02-22 16:20:51,447 INFO [com.zte.qsp.searcher.sortnode.TimeoutExecutor.timeoutSearch(654)] - sort call Thread has finished,it spends:15679";
		SortCallThreadPattern p = new SortCallThreadPattern();
		System.out.println(p.match(s));
		
	}

}
