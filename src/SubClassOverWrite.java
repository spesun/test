//1、（子转父）同名实例方法被覆盖，同名静态方法不被覆盖，
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
	
	
	
	//当变量类型是父类（ParentClass）时，不管我们创建的对象是父类（ParentClass）的还是子类（SubClass）的，都不存在属性覆盖的问题
	static void testParent() {
		ParentClass parentClass = new SubClassOverWrite();
		ParentClass subClass = new SubClassOverWrite();
		
		
		System.out.println(parentClass.i + subClass.i); // 20

		System.out.println(parentClass.i);// 10
		System.out.println(subClass.i);// 10 
		
		//调用父类的方法，父类方法只能访问父类属性，
		parentClass.print1();// 10
		subClass.print1();// 10
		
		
		((SubClassOverWrite)subClass).print1(); //10
		((SubClassOverWrite)subClass).printAll(); 
	}
	
	
	static void testSub() {
		
		ParentClass parentClass = new SubClassOverWrite();
		SubClassOverWrite subClass = new SubClassOverWrite();
		
		
		System.out.println(parentClass.i + subClass.i); // 自加

		System.out.println(parentClass.i);// 10
		System.out.println(subClass.i);// 30 
		
		
		parentClass.print1();//10
		subClass.print1();//10
		
		//子类方法可以直接访问子类属性，父类方法只能访问父类属性，子类方法可以访问子类属性和父类属性（当然得有权限）
		subClass.printAll(); 
	}
	
	public static void main(String[] args) {
		testSub();

	}

}