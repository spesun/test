package my.memory;

//TestStaticGc.java 
public class TestStaticGc {
	// ia是一个静态变量，在这里它作为一个int数组的引用。
	static int[] ia = new int[1024 * 1024];

	public static void main(String[] args) {
		int i = 0;
		// 为了清楚，我用了６次循环，每次都调用System.gc();
		// 在第３次循环时，我把ia的引用设为null。
		// 如果ia指向的对象能被回收的话，应该有明显的内存减少现象。
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
