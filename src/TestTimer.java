import java.util.Timer;
import java.util.TimerTask;


public class TestTimer {

	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Timer paraseLogTimer = new Timer(); 
		paraseLogTimer.schedule(new TaskTest(), 0, 5*1000);
		//Thread.currentThread().sleep(5*1000);
		paraseLogTimer.cancel();
		System.out.println("main end");
	}
	
}

class TaskTest extends TimerTask {

	@Override
	public void run() {
		//while (true) {
		System.out.println(" start ");
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" continue ");
		
		try {
			Thread.currentThread().sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" end ");
	//}
		
	}
	
}




