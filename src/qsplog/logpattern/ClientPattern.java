package qsplog.logpattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;

public class ClientPattern  extends PatternOne{
	
	public ClientPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - search[\\s]*([0-9]+)[\\s]*records, spend time:[\\s]*([0-9]+)[\\s]*";
		patternName = "client";
		
		order = LogConstant.order_client;
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
			map.put("record", m.group(2));
			map.put("cost", m.group(3));
			
			map.put("outFileName", outFileName);
			
			return map;
		} else {
			return null;
		}
		
	}

}
