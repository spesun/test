import java.io.IOException;


public class TestException {

	
	public static void main(String[] args) {
		
		try {
			
			if (true) {
				throw new IOException("aaa");
			}
			
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (Exception e) {
			System.out.println("Exception");
		} finally {
			System.out.println("finally");
		}
		
		
		// 不管是否catch,finally都能走到
		try {
			
			if (true) {
				throw new RuntimeException("aaa");
			}
			
			if (false) {
				throw new IOException("aaa");
			}
			
		} catch (IOException e) {
			System.out.println("IOException");
		} finally {
			System.out.println("finally");
		}
	}
}
