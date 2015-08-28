import java.util.concurrent.TimeUnit;


public class ThreadTest implements Runnable {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread test = new Thread(new ThreadTest());
		test.setDaemon(true);
		test.start();
		
		//TimeUnit.MICROSECONDS.sleep(175);

	}

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MICROSECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " = " + this);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
