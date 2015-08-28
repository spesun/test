package sing;

public class SingleTest {

	public static SingleTest test = new SingleTest();
	
	public int i=0;
	private SingleTest() {
		
		System.out.println("end");
		
		/*try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		
		i=1;
	}
	
	
	public static SingleTest getInc() {
		return test;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i <= 1; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println(SingleTest.getInc().i);
				}
			}).start();
			
			/*try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

	}

}



