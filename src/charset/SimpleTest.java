package charset;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		String s = "好";

		// 分、在ISO_8859_1没有对应编码。会造成byte丢失
		byte[] gbs = s.getBytes("ISO_8859_1");// 数组长度为1，内容 为63，即问号
		System.out.println(CharTest.byteToHex(gbs));// 返回3F,即63

		System.out.println(new String(gbs, "ISO_8859_1"));

		gbs = s.getBytes("gb2312");// 数组长度为2
		System.out.println(CharTest.byteToHex(gbs));

        //正常
		s = new String(gbs, "gb2312");
		System.out.println(s);

		//乱码
		s = new String(gbs, "utf-8");
		System.out.println(s);
	}
}
