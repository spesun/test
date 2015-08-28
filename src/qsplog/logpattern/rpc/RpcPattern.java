package qsplog.logpattern.rpc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;

import qsplog.logpattern.PatternOne;

public class RpcPattern  extends PatternOne{


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
}
