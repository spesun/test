package my.memory;

//TestStaticGc.java 
public class TestStaticGc {
	// ia��һ����̬����������������Ϊһ��int��������á�
	static int[] ia = new int[1024 * 1024];

	public static void main(String[] args) {
		int i = 0;
		// Ϊ������������ˣ���ѭ����ÿ�ζ�����System.gc();
		// �ڵڣ���ѭ��ʱ���Ұ�ia��������Ϊnull��
		// ���iaָ��Ķ����ܱ����յĻ���Ӧ�������Ե��ڴ��������
		do {
			if (3 == i++) {
				ia = null;
				System.out.println("release ");
			}
			System.out.println(i);
			System.gc();
		} while (i < 6);
	}
}
