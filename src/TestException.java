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
		
		
		// �����Ƿ�catch,finally�����ߵ�
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
