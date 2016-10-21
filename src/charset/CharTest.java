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
		 * String testStr = "中国"; System.out.println("a".getBytes().length);
		 * System.out.println(Arrays.toString("中".getBytes()));
		 */

		Set set = Charset.availableCharsets().keySet();
		for (Object o : set) {
			//System.out.println(o);
		}
		System.out.println(byteToHex("中".getBytes("UTF-16BE")));
		System.out.println(byteToHex("中".getBytes("UTF-16LE")));
		System.out.println(byteToHex("中".getBytes("UTF-16")));
		System.out.println(byteToHex("中".getBytes("UTF-8")));
		
		//System.out.println((int)'a');

		// 转换为char数组
		char[] defaultChars = { '中', '国' };

		// 用UTF-8进行编码（encode）

		byte[] utfbytes = StringCoding.encode("UTF-8", defaultChars, 0,
				defaultChars.length);

		// 用UTF-8进行解码（decode）
		char[] utfChars = StringCoding.decode("UTF-8", utfbytes, 0,
				utfbytes.length);
		
		// 将转换编码后的字符串打印出来
		String utfStr = Arrays.toString(utfChars);
		System.out.println(utfStr);
	}

	public static void BeLe() throws IOException {

		String str = "中";

		// ------------编码

		// Java里使用的是UTF-16BE方式来存储数据的
		System.out.println(Integer
				.toHexString(str.charAt(0) & 0x00FFFF | 0xFF0000)
				.substring(2, 6).toUpperCase());// 4E2D
		/*
		 * 进行编码时，因为 UTF-16 编码方式本身未指定字节顺序标记，所以默认使用 Big Endian 字节 顺序编码，并将 Big
		 * Endian 字节顺序标记写入到流中，所以流前面多了 FE FF 二字节的高字节 顺序标记
		 */
		System.out.println(byteToHex(str.getBytes("utf-16")));// FE FF 4E 2D

		/*
		 * 进行编码时，UTF-16BE 和 UTF-16LE charset 不会将字节顺序标记写入到流中
		 * 即它们所编出的码每个字符只占二个字节，要注意的是解码时要使用同样的编码 方式，不然会出现问题乱码
		 */
		System.out.println(byteToHex(str.getBytes("utf-16BE")));// 4E 2D
		System.out.println(byteToHex(str.getBytes("utf-16LE")));// 2D 4E

		// 使用 utf-16BE 对高字节序进行解码，忽略字节顺序标记，即不会将流前二字节内容看作字节序标记
		System.out.println(new String(new byte[] { 0x4E, 0x2D }, "utf-16BE"));// 中
		// 使用 utf-16LE 对低字节序进行解码，忽略字节顺序标记，即不会将流前二字节内容看作字节序标记
		System.out.println(new String(new byte[] { 0x2D, 0x4E }, "utf-16LE"));// 中

		// ------------解码

		/*
		 * 使用 utf-16 进行解码时，会根据流前两字节内部来确定是低还是高字节顺序，如果流的前两字节 内部不是 高字节序 FE
		 * FF，也不是低字节序 FF FE时，则默认使用 高字节序 方式来解码
		 */

		// 因为0x4E,0x2D为“中”字的高字节表示，所以前面需要加上 FE FF 字节顺序标记来指示它
		System.out.println(new String(new byte[] { (byte) 0xFE, (byte) 0xFF,
				0x4E, 0x2D }, "utf-16"));// 中

		// 因为0x2D,0x4E为“中”字的低字节表示，所以前面需要加上 FF FE 字节顺序标记来指示它
		String tmp = new String(new byte[] { (byte) 0xFF, (byte) 0xFE,
				0x2D, 0x4E, }, "utf-16");
		System.out.println(tmp);// 中
		
		System.out.println(byteToHex(tmp.getBytes("utf-16")));

		// 使用默认 高字节顺序 方式来解码，
		System.out.println(new String(new byte[] { 0x4E, 0x2D }, "utf-16"));// 中

		// 因为 0x2D,0x4E 为“中”的低字节序，但 utf-16 默认却是以 高字节序来解的，所以出现乱码
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
	 * 和上面方法的区别?
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
