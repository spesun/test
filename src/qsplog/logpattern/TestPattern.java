package qsplog.logpattern;

import java.util.regex.Pattern;

import qsplog.LogConstant;
import qsplog.logpattern.search.SearchPattern;

public class TestPattern  extends SearchPattern{
	
	public TestPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*testtest spend:([0-9]+)[\\s]*";
		patternName = "testtest";
		
		order = LogConstant.order_test_test;
		outFileName = getOutFileName();
		
		p = Pattern.compile(pattern);

		
	}
	
	
	
	public static void main(String[] args) {
		String s = "";
		System.out.println(new TestPattern().match(s));
	}

}
