package my.ik;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.mira.lucene.analysis.IK_CAnalyzer;


public class IkTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		//System.out.println(Character.UnicodeBlock.of());
		
		Analyzer a = new IK_CAnalyzer();
		TokenStream ts =  a.tokenStream("ff", new StringReader("中兴通讯"));
		
		List l = new ArrayList();
		while(true) {
			org.apache.lucene.analysis.Token t = ts.next();
			if(t==null) {
				break;
			}
			l.add(t.termText());
			//System.out.println(t.termText());
		}
		
		System.out.println(l);
		
		/*//英文中文数字的merger
		List merge = new ArrayList();
		merge.add(l.get(0));
		for (int i = 1; i < l.size(); i++) {
			String s = (String)l.get(i);
			
			
			int curType = getType(s);
			
			String pre = (String)l.get(i-1);
			int preType = getType(pre);
			
			merge.add(s);
			
			if (curType != -1 && preType != -1) {
				if (curType != preType) {
					//if  () {
						merge.add(pre+s);
					//}
					
				}
			}
			
		}
		System.out.println(merge);*/
	}
	
	private static int getType(String s) {
		int type = -1;
		
		if (isAllChinese(s)) {
			type = 0;
		}
		
		else if (isAllEnglish(s)) {
			type = 1;
		}
		
		else if (isAllNumber(s)) {
			type = 2;
		
		}
		
		return type;
			
	}
	
	private static boolean isAllChinese(String word) {
		java.util.regex.Pattern p=java.util.regex.Pattern.compile("[\u4e00-\u9fa5]+");
	    java.util.regex.Matcher m=p.matcher(word);
	    
	    return m.matches();
	}
		
	private static boolean isAllEnglish(String word) {
		java.util.regex.Pattern p=java.util.regex.Pattern.compile("[a-z]+");
	    java.util.regex.Matcher m=p.matcher(word);
	    
	    return m.matches();
	}
	
	private static boolean isAllNumber(String word) {
		java.util.regex.Pattern p=java.util.regex.Pattern.compile("[1-9.]+");
	    java.util.regex.Matcher m=p.matcher(word);
	    
	    return m.matches();
	}

}
