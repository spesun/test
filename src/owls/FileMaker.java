package owls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileMaker {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String root = "d:/";
		String[] paths = new String[] {
				"127.0.0.1/I/121124/22/26/LXX1010186002080497350100000001720008020121124222617000000049144_0022_0168.txt"
		};
		
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i];
			int index = path.indexOf("/");
			String real = root + path.substring(index);
			
			File file  = new File(real);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
				writer.append("");
				writer.close();
			}
		}
		
	}

}
