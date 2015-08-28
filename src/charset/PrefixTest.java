package charset;

import java.io.UnsupportedEncodingException;

public class PrefixTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String keyword =  new String("﻿Accept-Encode");
		//keyword =  new String("");
        byte b[] = keyword.getBytes("UTF-8");
        System.out.println(CharTest.byteToHex(b));

	}

}
