package qsplog.logpattern.sort;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.PatternOne;

public class SortPattern  extends PatternOne{
	
	public SortPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*sort node call search has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort";
		
		order = LogConstant.order_sort;
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
			map.put("threadNum", m.group(2));
			map.put("cost", m.group(3));
			
			map.put("outFileName", outFileName);
			
			return map;
		} else {
			return null;
		}
		
	}

}
