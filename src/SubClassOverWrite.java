//1������ת����ͬ��ʵ�����������ǣ�ͬ����̬�����������ǣ�
//http://wenku.baidu.com/link?url=jAM9ttgeEfrwjbYkTisnH9twfB6cqYCs3xukTqxN8h_hofM-iO2IDVJltIGTiYBBcVcJfDZWcxKgHXLal-Q7hKU9a2GmoVB3i2WDoaIDfRy

class ParentClass {
	public static int i = 10;
	
	public void print1() {
		System.out.println("Parent=" + i);
	}
	
	
	
}

public class SubClassOverWrite extends ParentClass {
	public static int i = 30;
	
	
	public void printAll() {
		System.out.println("printAll=" + i);
		System.out.println("printAll=" + super.i);
	}
	
	
	
	//�����������Ǹ��ࣨParentClass��ʱ���������Ǵ����Ķ����Ǹ��ࣨParentClass���Ļ������ࣨSubClass���ģ������������Ը��ǵ�����
	static void testParent() {
		ParentClass parentClass = new SubClassOverWrite();
		ParentClass subClass = new SubClassOverWrite();
		
		
		System.out.println(parentClass.i + subClass.i); // 20

		System.out.println(parentClass.i);// 10
		System.out.println(subClass.i);// 10 
		
		//���ø���ķ��������෽��ֻ�ܷ��ʸ������ԣ�
		parentClass.print1();// 10
		subClass.print1();// 10
		
		
		((SubClassOverWrite)subClass).print1(); //10
		((SubClassOverWrite)subClass).printAll(); 
	}
	
	
	static void testSub() {
		
		ParentClass parentClass = new SubClassOverWrite();
		SubClassOverWrite subClass = new SubClassOverWrite();
		
		
		System.out.println(parentClass.i + subClass.i); // �Լ�

		System.out.println(parentClass.i);// 10
		System.out.println(subClass.i);// 30 
		
		
		parentClass.print1();//10
		subClass.print1();//10
		
		//���෽������ֱ�ӷ����������ԣ����෽��ֻ�ܷ��ʸ������ԣ����෽�����Է����������Ժ͸������ԣ���Ȼ����Ȩ�ޣ�
		subClass.printAll(); 
	}
	
	public static void main(String[] args) {
		testSub();

	}

}