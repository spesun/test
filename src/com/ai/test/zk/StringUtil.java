package com.ai.test.zk;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class StringUtil {
	public static final String REG_EXP_OF_CONTAINS_IP = "((?s).*?)([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}((?s).*?)";
	public static final String   REG_EXP_OF_IP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	public static final Pattern PATTERN_OF_CONTAINS_IP = Pattern.compile( REG_EXP_OF_CONTAINS_IP );

	
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	public static boolean isBlank( String originalStr ) {
		if ( null == originalStr ) {
			return true;
		}
		
		return trimToEmpty( originalStr ).isEmpty();
	}

	/***
	 * check if orginalStr is null or empty
	 * 
	 * @param String
	 *            ... originalStrArray
	 * @return true if have one blank at least.
	 */
	public static boolean isBlank( String... originalStrArray ) {

		if ( null == originalStrArray || 0 == originalStrArray.length )
			return true;
		for ( int i = 0; i < originalStrArray.length; i++ ) {
			if ( isBlank( originalStrArray[i] ) )
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a copy of the string, with leading and trailing whitespace
	 * omitted. Don't worry the NullPointerException. Will never return Null.
	 * 
	 * @param originalStr
	 * @return "" or String without empty str.
	 */
	public static String trimToEmpty( String originalStr ) {
		if ( null == originalStr || originalStr.isEmpty() )
			return ZKServerStatusCollector.EMPTY_STRING;
		
		return originalStr.trim();
	}
	
	public static boolean containsIp( String originalStr ) {

		if ( StringUtil.isBlank( originalStr ) )
			return false;

		Matcher match = PATTERN_OF_CONTAINS_IP.matcher( originalStr );
		return match.matches();
	}
	
	
	 public final static String BR = "<br/>";
	 public static final String WORD_SEPARATOR = Character.toString( ( char )2 );
	 public static int getProcessRst(Process process, StringBuffer rstLog) {

			BufferedReader br = null;
			try {
				
				String line;
				//取得正常输出
				br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
				
				while ((line = br.readLine()) != null) {
					rstLog.append(line + BR);
				}
				
				
			}  catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
				//rstLog.append(e.getMessage());
				
			} finally {
				
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						logger.error("", e);
					}
				}
			}
			
			try {
				return process.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
				//rstLog.append(e.getMessage());
				return process.exitValue();
			}
			
	 }

}
