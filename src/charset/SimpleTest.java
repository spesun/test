package charset;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		String s = "��";

		// �֡���ISO_8859_1û�ж�Ӧ���롣�����byte��ʧ
		byte[] gbs = s.getBytes("ISO_8859_1");// ���鳤��Ϊ1������ Ϊ63�����ʺ�
		System.out.println(CharTest.byteToHex(gbs));// ����3F,��63

		System.out.println(new String(gbs, "ISO_8859_1"));

		gbs = s.getBytes("gb2312");// ���鳤��Ϊ2
		System.out.println(CharTest.byteToHex(gbs));

        //����
		s = new String(gbs, "gb2312");
		System.out.println(s);

		//����
		s = new String(gbs, "utf-8");
		System.out.println(s);
	}
}
