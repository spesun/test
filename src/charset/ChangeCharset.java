package charset;

import java.io.UnsupportedEncodingException;

public class ChangeCharset {
	/** 7λASCII�ַ���Ҳ����ISO646-US��Unicode�ַ����Ļ��������� */

	public static final String US_ASCII = "US-ASCII";

	/** ISO������ĸ�� No.1,Ҳ����ISO-LATIN-1 */

	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 8 λ UCS ת����ʽ */

	public static final String UTF_8 = "UTF-8";

	/** 16 λ UCS ת����ʽ��Big Endian(��͵�ַ��Ÿ�λ�ֽ�)�ֽ�˳�� */

	public static final String UTF_16BE = "UTF-16BE";

	/** 16 λ UCS ת����ʽ��Litter Endian(��ߵ�ַ��ŵ�λ�ֽ�)�ֽ�˳�� */

	public static final String UTF_16LE = "UTF-16LE";

	/** 16 λ UCS ת����ʽ���ֽ�˳���ɿ�ѡ���ֽ�˳��������ʶ */

	public static final String UTF_16 = "UTF-16";

	/** ���ĳ����ַ��� **/

	public static final String GBK = "GBK";

	public static final String GB2312 = "GB2312";

	/** ���ַ�����ת����US-ASCII�� */

	public String toASCII(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, US_ASCII);

	}

	/** ���ַ�����ת����ISO-8859-1 */

	public String toISO_8859_1(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, ISO_8859_1);

	}

	/** ���ַ�����ת����UTF-8 */

	public String toUTF_8(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, UTF_8);

	}

	/** ���ַ�����ת����UTF-16BE */

	public String toUTF_16BE(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, UTF_16BE);

	}

	/** ���ַ�����ת����UTF-16LE */

	public String toUTF_16LE(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, UTF_16LE);

	}

	/** ���ַ�����ת����UTF-16 */

	public String toUTF_16(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, UTF_16);

	}

	/** ���ַ�����ת����GBK */

	public String toGBK(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, GBK);

	}

	/** ���ַ�����ת����GB2312 */

	public String toGB2312(String str) throws UnsupportedEncodingException {

		return this.changeCharset(str, GB2312);

	}

	/***
	 * �ַ�������ת����ʵ�ַ���
	 * 
	 * @param str
	 *            ��ת�����ַ���
	 * 
	 * @param newCharset
	 *            Ŀ�����
	 */

	public String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {

		if (str != null) {

			// ��Ĭ���ַ���������ַ�������ϵͳ��أ�����windowsĬ��ΪGB2312

			byte[] bs = str.getBytes();

			return new String(bs, newCharset); // ���µ��ַ����������ַ���
		}

		return null;

	}

	/**
	 * 
	 * �ַ�������ת����ʵ�ַ���
	 * 
	 * @param str
	 *            ��ת�����ַ���
	 * 
	 * @param oldCharset
	 *            Դ�ַ���
	 * 
	 * @param newCharset
	 *            Ŀ���ַ���
	 */

	public String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {

		if (str != null) {

			// ��Դ�ַ���������ַ���

			byte[] bs = str.getBytes(oldCharset);

			return new String(bs, newCharset);

		}

		return null;

	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		ChangeCharset test = new ChangeCharset();

		String str = "This is a ���ĵ� String!";

		System.out.println("str:" + str);

		String gbk = test.toGBK(str);

		System.out.println("ת����GBK�룺" + gbk);

		System.out.println();

		String ascii = test.toASCII(str);

		System.out.println("ת����US-ASCII:" + ascii);

		System.out.println();

		String iso88591 = test.toISO_8859_1(str);

		System.out.println("ת����ISO-8859-1�룺" + iso88591);

		System.out.println();

		gbk = test.changeCharset(iso88591, ISO_8859_1, GBK);

		System.out.println("�ٰ�ISO-8859-1����ַ���ת����GBK�룺" + gbk);

		System.out.println();

		String utf8 = test.toUTF_8(str);

		System.out.println();

		System.out.println("ת����UTF-8�룺" + utf8);

		String utf16be = test.toUTF_16BE(str);

		System.out.println("ת����UTF-16BE�룺" + utf16be);

		gbk = test.changeCharset(utf16be, UTF_16BE, GBK);

		System.out.println("�ٰ�UTF-16BE������ַ�ת����GBK�룺" + gbk);

		System.out.println();

		String utf16le = test.toUTF_16LE(str);

		System.out.println("ת����UTF-16LE�룺" + utf16le);

		gbk = test.changeCharset(utf16le, UTF_16LE, GBK);

		System.out.println("�ٰ�UTF-16LE������ַ���ת����GBK�룺" + gbk);

		System.out.println();

		String utf16 = test.toUTF_16(str);

		System.out.println("ת����UTF-16�룺" + utf16);

		String gb2312 = test.changeCharset(utf16, UTF_16, GB2312);

		System.out.println("�ٰ�UTF-16������ַ���ת����GB2312�룺" + gb2312);

	}

}