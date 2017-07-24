package charset;

public class UnicodeTest {

	public static void main(String[] args) throws Exception {
		String s = "好";

		System.out.println(s.length());
		//unicode
		String unicode = string2Unicode(s);
		System.out.println(unicode);
		System.out.println(unicode2String(unicode));


		//  \\u后面加上十六进制代码来表示Unicode字符。
		//  0x开头,代表的是16进制数,0开头的,代表的是8进制数,可以使用windows自带的计算器看一下(注意调成科学型模式):0x56=86(十进制下),056=46(十进制下).
		System.out.println(Byte.MAX_VALUE);
		System.out.println();

	}

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);

			// 追加成string
			string.append((char) data);
		}

		return string.toString();
	}

	private static void printArray(byte[] o) {
		for (int i = 0; i < o.length; i++) {
			System.out.println();
		}
	}
}
