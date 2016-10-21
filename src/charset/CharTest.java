package charset;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Set;

public class CharTest {

	public static void main(String[] args) throws Exception {
		BeLe();
		//test();
	}

	public static void test() throws IOException {
		/*
		 * String testStr = "�й�"; System.out.println("a".getBytes().length);
		 * System.out.println(Arrays.toString("��".getBytes()));
		 */

		Set set = Charset.availableCharsets().keySet();
		for (Object o : set) {
			//System.out.println(o);
		}
		System.out.println(byteToHex("��".getBytes("UTF-16BE")));
		System.out.println(byteToHex("��".getBytes("UTF-16LE")));
		System.out.println(byteToHex("��".getBytes("UTF-16")));
		System.out.println(byteToHex("��".getBytes("UTF-8")));
		
		//System.out.println((int)'a');

		// ת��Ϊchar����
		char[] defaultChars = { '��', '��' };

		// ��UTF-8���б��루encode��

		byte[] utfbytes = StringCoding.encode("UTF-8", defaultChars, 0,
				defaultChars.length);

		// ��UTF-8���н��루decode��
		char[] utfChars = StringCoding.decode("UTF-8", utfbytes, 0,
				utfbytes.length);
		
		// ��ת���������ַ�����ӡ����
		String utfStr = Arrays.toString(utfChars);
		System.out.println(utfStr);
	}

	public static void BeLe() throws IOException {

		String str = "��";

		// ------------����

		// Java��ʹ�õ���UTF-16BE��ʽ���洢���ݵ�
		System.out.println(Integer
				.toHexString(str.charAt(0) & 0x00FFFF | 0xFF0000)
				.substring(2, 6).toUpperCase());// 4E2D
		/*
		 * ���б���ʱ����Ϊ UTF-16 ���뷽ʽ����δָ���ֽ�˳���ǣ�����Ĭ��ʹ�� Big Endian �ֽ� ˳����룬���� Big
		 * Endian �ֽ�˳����д�뵽���У�������ǰ����� FE FF ���ֽڵĸ��ֽ� ˳����
		 */
		System.out.println(byteToHex(str.getBytes("utf-16")));// FE FF 4E 2D

		/*
		 * ���б���ʱ��UTF-16BE �� UTF-16LE charset ���Ὣ�ֽ�˳����д�뵽����
		 * ���������������ÿ���ַ�ֻռ�����ֽڣ�Ҫע����ǽ���ʱҪʹ��ͬ���ı��� ��ʽ����Ȼ�������������
		 */
		System.out.println(byteToHex(str.getBytes("utf-16BE")));// 4E 2D
		System.out.println(byteToHex(str.getBytes("utf-16LE")));// 2D 4E

		// ʹ�� utf-16BE �Ը��ֽ�����н��룬�����ֽ�˳���ǣ������Ὣ��ǰ���ֽ����ݿ����ֽ�����
		System.out.println(new String(new byte[] { 0x4E, 0x2D }, "utf-16BE"));// ��
		// ʹ�� utf-16LE �Ե��ֽ�����н��룬�����ֽ�˳���ǣ������Ὣ��ǰ���ֽ����ݿ����ֽ�����
		System.out.println(new String(new byte[] { 0x2D, 0x4E }, "utf-16LE"));// ��

		// ------------����

		/*
		 * ʹ�� utf-16 ���н���ʱ���������ǰ���ֽ��ڲ���ȷ���ǵͻ��Ǹ��ֽ�˳���������ǰ���ֽ� �ڲ����� ���ֽ��� FE
		 * FF��Ҳ���ǵ��ֽ��� FF FEʱ����Ĭ��ʹ�� ���ֽ��� ��ʽ������
		 */

		// ��Ϊ0x4E,0x2DΪ���С��ֵĸ��ֽڱ�ʾ������ǰ����Ҫ���� FE FF �ֽ�˳������ָʾ��
		System.out.println(new String(new byte[] { (byte) 0xFE, (byte) 0xFF,
				0x4E, 0x2D }, "utf-16"));// ��

		// ��Ϊ0x2D,0x4EΪ���С��ֵĵ��ֽڱ�ʾ������ǰ����Ҫ���� FF FE �ֽ�˳������ָʾ��
		String tmp = new String(new byte[] { (byte) 0xFF, (byte) 0xFE,
				0x2D, 0x4E, }, "utf-16");
		System.out.println(tmp);// ��
		
		System.out.println(byteToHex(tmp.getBytes("utf-16")));

		// ʹ��Ĭ�� ���ֽ�˳�� ��ʽ�����룬
		System.out.println(new String(new byte[] { 0x4E, 0x2D }, "utf-16"));// ��

		// ��Ϊ 0x2D,0x4E Ϊ���С��ĵ��ֽ��򣬵� utf-16 Ĭ��ȴ���� ���ֽ�������ģ����Գ�������
		System.out.println(new String(new byte[] { 0x2D, 0x4E, }, "utf-16"));// ?
	}

	public static String byteToHex(byte[] bt) {
		StringBuilder sb = new StringBuilder(4);
		for (int b : bt) {
			sb.append(Integer.toHexString(b & 0x00FF | 0xFF00).substring(2, 4)
					.toUpperCase());
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * �����淽��������?
	 *
	 * @param b
	 */
	public static void printHexString( byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() );
		}
	}

}
