package owls;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;






public class TimeTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception {
		Date d = new Date();
		d.setTime((long)1351619819 * 1000);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(f.format(d));
	}
	
	
}

