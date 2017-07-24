package charset;

public class UnicodeTest {

	public static void main(String[] args) throws Exception {
		String s = "��";

		System.out.println(s.length());
		//unicode
		String unicode = string2Unicode(s);
		System.out.println(unicode);
		System.out.println(unicode2String(unicode));


		//  \\u�������ʮ�����ƴ�������ʾUnicode�ַ���
		//  0x��ͷ,�������16������,0��ͷ��,�������8������,����ʹ��windows�Դ��ļ�������һ��(ע����ɿ�ѧ��ģʽ):0x56=86(ʮ������),056=46(ʮ������).
		System.out.println(Byte.MAX_VALUE);
		System.out.println();

	}

	/**
	 * �ַ���ת��unicode
	 */
	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// ȡ��ÿһ���ַ�
			char c = string.charAt(i);

			// ת��Ϊunicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * unicode ת�ַ���
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// ת����ÿһ�������
			int data = Integer.parseInt(hex[i], 16);

			// ׷�ӳ�string
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
