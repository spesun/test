package dfs;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {

	public static void main(String[] args) throws Exception {
		URL url = new URL(
				"http://e.zte.com.cn/pub/ztercn/news/media/201204/t20120405_299739.html");
		URLConnection c = url.openConnection();
		InputStream in = c.getInputStream();
		BufferedReader r = new BufferedReader(new InputStreamReader(in, "GBK"));
		String line;
		while ((line = r.readLine()) != null) {
			System.out.println(line);
		}


	}
}
