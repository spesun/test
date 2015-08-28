package my;

public class ExceptionTest {
	
	public void test() throws InterruptedException {
		
		/*int i = 0; 
		
		while (true) {
			Thread.sleep(5000);
			if (i == 100) {
				break;
			}
			
			System.out.println(i);
			i++;
			
		}*/
		
		try {
			if (true) {
				throw new NullPointerException();
			}
		
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
			throw e;
		} catch (Exception e) {
			System.out.println("Exception");
			//notice this
			//return;
		} finally {
			System.out.println("finally");
		}
		
		
		System.out.println("end");
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		ExceptionTest test = new ExceptionTest();
		
		test.test();
	}
}
