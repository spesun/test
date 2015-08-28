package time;

import java.util.Timer;

public class ThreadNew {

	public static void main(String[] args) throws InterruptedException {

		int interval = 1000;
		if (args.length >= 1) {
			interval = Integer.parseInt(args[0]);
		}
		System.out.println("interval(ms)=" + interval);
		long i = 0;
		while (true) {
			Timer timer = new Timer();
			timer.schedule(new Task(), 1 * 1000, 2 * 1000);
			i++;
			Thread.currentThread().sleep(interval);

			System.out.println(i);
		}
	}
}
