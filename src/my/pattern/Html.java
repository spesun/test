package my.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Html {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*String val = "abc,ef,k<font color='red'>abc</font>df,c<font color='red'>def</font>";
		//�Ӹ���ǰ�ĵ�һ�����Ż��Ž�ȡ
		//�ַ�����ʱЧ�ʵ�
		val = val.replaceFirst(".*?[,.����]([^,.����]*?)<font color='red'>", "$1<font color='red'>");
		
		//ת��html�ַ�
		//�ָ���ת���<font color='red'>
		val = val.replaceAll("(.*?)" + "<font color='red'> " + "(.*?)" + "</font>"  + "(.*?)", "$1" + "<font color='red'>" + "$2" + "</font>" + "$3");*/
		
		//����ƥ��
		Pattern p = Pattern.compile("(?!g)abc"); 
		Matcher matcher = p.matcher("abc def abc"); 
		matcher.find();
		System.out.println(matcher.end());
		System.out.println(matcher.matches());

	}

}
