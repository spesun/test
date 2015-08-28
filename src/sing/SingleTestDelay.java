package sing;

public class SingleTestDelay {

	public static SingleTestDelay test = null;
	
	public int i=0;
	private SingleTestDelay() {
		
		System.out.println("init");
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		i=1;
		
		System.out.println("end");
	}
	
	
	public static SingleTestDelay getInc() {
		if (test == null) {
			test = new SingleTestDelay();
		}
		
		return test;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for (int i = 0; i <= 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println(SingleTestDelay.getInc().i);
				}
			}).start();
			
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}



