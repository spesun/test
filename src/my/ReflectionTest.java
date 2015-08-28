package my;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import my.pattern.TestQspPattern;

public class ReflectionTest {

	
	public static void main(String[] args) throws Exception {
		//Class myClass = Class.forName("my.pattern.TestQspPattern"); 
		rf();
		//noRf();
	}
	
	static void rf() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			Method method = TestQspPattern.class.getMethod("doc"); 
			Class c = method.getDeclaringClass();
			
			Object o = method.invoke(c.newInstance());
		}
		
		long end = System.currentTimeMillis();

		System.out.println("total = " + (end - start));
	}
	
	static void noRf() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			
			TestQspPattern t = new TestQspPattern();
			t.doc();
		}
		
		long end = System.currentTimeMillis();

		System.out.println("total = " + (end - start));
	}
}
