
/**
* 
*/
public class UnicodeTest {

public static void main(String[] args) {
	String[] cns = new String[] {
			"fulltext.title=È«ÎÄ¼ìË÷"
	};
	for (int i = 0; i < cns.length; i++) {
		  String cn = cns[i];
		  System.out.println(cnToUnicode(cn))  ;
	}
	
    /* String unicode = "\\u5f00\\u59cb\\u4efb\\u52a1";
    System.out.println(unicodeToCn(unicode));*/
}

private static String unicodeToCn(String unicode) {
    String[] strs = unicode.split("\\\\u");
    String returnStr = "";
    for (int i = 1; i < strs.length; i++) {
      returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
    }
    return returnStr;
}

private static String cnToUnicode(String in) {
	String[] strs = in.split("=");
	String cn ;
	if (strs.length <2) {
		cn="";
	} else {
		cn = strs[1];
	}
	
	String key = strs[0];
	
	
    char[] chars = cn.toCharArray();
    String returnStr = key + "=";
    for (int i = 0; i < chars.length; i++) {
      returnStr += "\\u" + Integer.toString(chars[i], 16);
    }
    return returnStr;
}

}