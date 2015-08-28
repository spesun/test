package dfs;

import java.io.File;


public class FilePathTest {
	public static void main(String[] args) throws Exception {
/*
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		
		//classload根路�?
		System.out.println(FilePathTest.class.getClassLoader().getResource(""));
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(FilePathTest.class.getResource("/")); 
		//以上都是file:/D:/workspace/crawlerzky/bin/
		
		// Class文件�?��路径
		System.out.println(FilePathTest.class.getResource(""));
		//file:/D:/workspace/crawlerzky/bin/test
		
		
		System.out.println(new File("/").getAbsolutePath());
		//D:\
		
		System.out.println(System.getProperty("user.dir"));
		//D:\workspace\crawlerzky

		System.out.println(new File(".").getAbsolutePath());
		//D:\workspace\crawlerzky\.
		System.out.println(FilePathTest.class.getResource("a")); */	
		
		
		//System.out.println(FilePathTest.class.getResource(""));
		System.out.println(FilePathTest.class.getResource("/"));
	}

}
