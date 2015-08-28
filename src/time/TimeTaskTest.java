package time;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTaskTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new Task(), 1 * 1000, 20 * 1000);
	}

}

class Task extends TimerTask {
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + "one start");
		try {
			Thread.currentThread().sleep(10 * 1000);
			System.out.println(Thread.currentThread().getName() + " one end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
