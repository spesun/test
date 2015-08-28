package testThread;

public class testThread {

	public static void main(String[] args) {
		MyThread my = new MyThread();
		my.start();
		
		String v = my.get();
		System.out.println(v);
		
	}
}

class MyThread extends Thread {
	

		
	public String get() {
		return "get from thread";
	}
	
	public void run() {
		try {
			
			Thread.currentThread().sleep(4000);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("thread end");
	}
	
}