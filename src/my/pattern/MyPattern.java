package my.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* test splits
	    Pattern spliter = Pattern.compile("OR|AND"); 
		String[] strs = spliter.split("a AND b OR d");
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}*/

		
		/*test appendReplacement appendTail 
		 Pattern p = Pattern.compile("cat");
		 Matcher m = p.matcher("one cat two cats in the yard");
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
		     m.appendReplacement(sb, "dog");
		 }
		 System.out.println(m.appendTail(sb));
		 System.out.println(sb.toString());*/
		
		
		/*test group 
		Pattern p = Pattern.compile("((A)(B(C)))");
		Matcher m = p.matcher("ABC");
		StringBuffer sb = new StringBuffer();

		while (m.find()) {
			System.out.println("group=" + m.group());
			System.out.println("grouptest=" + m.group(0));
			System.out.println("grouptest=" + m.group(4));
		}*/
		
		/*
		Ó¢ÎÄtest
		Pattern p = Pattern.compile("[a-z]+");
		Matcher m = p.matcher("adf");
		System.out.println(m.matches());*/

	}

}
