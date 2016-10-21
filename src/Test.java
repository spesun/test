import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;






import com.hive.sca.PropertiesUtil;
import com.hive.sca.ResourceLoader;








public class Test {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception {

		testUUID();
	}
	
	
	public static void testUUID() {
		UUID  id = UUID.randomUUID();
		System.out.println(id.toString().length());
	 	System.out.println(id.getLeastSignificantBits());		
	}
	
	public static void testBinary() {

		byte b = 20;
		String s = Integer.toBinaryString(20 | 256);
		System.out.println(Integer.toBinaryString(20));
		System.out.println(Integer.toBinaryString(256));
		System.out.println(Integer.parseInt(s));
		System.out.println("length=" + s.length());
		s = s.substring(s.length() - 8);
		System.out.println(s);
		
	}

	public static void testDate() throws ParseException {
		Date d = new Date();
		d.setTime((long)1369719823000L);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(f.format(d));
		
		/*f = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(f.parse("2013-03-01").getTime());
		System.out.println(f.parse("2013-03-07").getTime());*/
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = format.parse("20141111000000");
		System.out.println(date.getTime());
		date = format.parse("20141202000000");
		System.out.println(date.getTime());
		//1416159527987
		
		System.out.println(format.format(new Date(1415635200000L)));
		System.out.println(format.format(new Date(1417449600000L)));
		System.out.println("==================================");
		
	
	}
	
	private static long nextRandomSeed() {
		Random randomSeed =
			      new Random(System.currentTimeMillis());
	      return randomSeed.nextLong();
	}
	
	 public static int getPartition(Integer key, String value,
             int numReduceTasks) {
		 System.out.println("aaaaa="+(key.hashCode() & Integer.MAX_VALUE));
		return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;
	}
	 
}

