package qsplog.logpattern;

import java.util.Map;
import java.util.regex.Pattern;

public abstract class PatternOne {
	
	//public static sort_order;
	
	public Pattern p;
	public String pattern;
	public int order;
	public String outFileName;
	
	public String patternName;

	public abstract Map match(String str);
	
	public String getOutFileName() {
		return "out" + order + "_" + patternName +".csv";
	}

}
