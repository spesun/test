import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
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

		/*String path = "d:/collect.xml";
		System.out.println(path.substring(path.indexOf("/")));
		File file = new File(path);
		URL u = file.toURI().toURL();
		System.out.println(u);
		java.io.File f = new java.io.File(u.getPath());
		
		
		System.out.println(f.exists());
		
		String s = URLDecoder.decode("62f82430b48f34e9fb908f684b5b878e");
		System.out.println(s);*/
		
		
		/*String ss = "|a||";
		System.out.println(ss.split("\\|", -1).length);*/
		
		/*Integer[] tt = {1}; 
		List keyColList = Arrays.asList(tt);
		System.out.println(keyColList.contains(1));
		for (int i = 0; i < keyColList.size(); i++) {
			System.out.println(keyColList.get(i));
		}*/
		
		/*BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("e:/part-r-00000"), "gb2312"));
		String line = reader.readLine();
		System.out.println(line);
		//while (en.hasMoreElements()) {
		//	type type = (type) en.nextElement();	
		//}
*/	    
		
	
		//System.out.println(PropertiesUtil.getPropertie("scaclass"));
		/*SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(format.format(new Date()));
		Thread.currentThread().sleep(1000*60);
		System.out.println(format.format(new Date()));*/
		
		/*String s = "460036131471993|18913482346||ctwap@mycdma.cn|61.151.232.36|80|10.73.224.6|65138|115.169.21.12|172.25.50.110|115.169.21.12|0|37680001D4A3|00A8C0006C000000AC19320C02020022|59|1|201|20141226190001|20141226190003|2009|609|1550|6|6|||1|1|0|0|0|QQ/5.3.0.319 CFNetwork/672.1.15 Darwin/14.0.0|http://opensdk.uu.qq.com/analytics/upload|opensdk.uu.qq.com|opensdk.uu.qq.com|122|application/x-www-form-urlencoded|0||5|200|187|1||3G|";
		String[] array = s.split("\\|", -1);
		System.out.println(array.length);
		System.out.println(array[array.length-2]);
		System.out.println(array[15]);*/
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = format.parse("20141111000000");
		System.out.println(date.getTime());
		date = format.parse("20141202000000");
		System.out.println(date.getTime());
		//1416159527987
		
		System.out.println(format.format(new Date(1415635200000L)));
		System.out.println(format.format(new Date(1417449600000L)));
		System.out.println("==================================");
		byte b = 20;
		String s = Integer.toBinaryString(20 | 256);
		System.out.println(Integer.toBinaryString(20));
		System.out.println(Integer.toBinaryString(256));
		System.out.println(Integer.parseInt(s));
		System.out.println("length=" + s.length());
		s = s.substring(s.length() - 8);
		System.out.println(s);
		
		System.out.println(Integer.MAX_VALUE);
		
	}
	

	public static void testDate() {
		Date d = new Date();
		d.setTime((long)1369719823000L);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(f.format(d));
		
		/*f = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(f.parse("2013-03-01").getTime());
		System.out.println(f.parse("2013-03-07").getTime());*/
		
	
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

