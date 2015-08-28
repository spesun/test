package charset;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		String s = "好";

		// 分、在ISO_8859_1没有对应编码。会造成byte丢失
		byte[] gbs = s.getBytes("ISO_8859_1");// 数组长度为1，内容 为63，即问号
		System.out.println(CharTest.byteToHex(gbs));// 返回3F,即63

		System.out.println(new String(gbs, "ISO_8859_1"));

		gbs = s.getBytes("gb2312");// 数组长度为1，内容 为63，即问号
		System.out.println(CharTest.byteToHex(gbs));// 返回3F,即63
		s = new String(gbs, "utf-8");
		gbs = s.getBytes("utf-8");
		System.out.println(CharTest.byteToHex(gbs));
	}

	private static void printArray(byte[] o) {
		for (int i = 0; i < o.length; i++) {
			System.out.println();
		}
	}
}
