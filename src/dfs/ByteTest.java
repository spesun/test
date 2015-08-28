package dfs;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

public class ByteTest {

	public static void main(String[] args) throws Exception {
		//File f = new File("/home/nutch-1.2/localweb/segments/20120510151355/content/part-00000/data");
		File f = new File("d:/data");
		Class codecClass = Class.forName("org.apache.hadoop.io.compress.DefaultCodec"); 
		Configuration conf = new Configuration(); 
		
		CompressionCodec codec = (CompressionCodec) ReflectionUtils 
		.newInstance(codecClass, conf);
		
		/*CompressionOutputStream o = codec.createOutputStream(new FileOutputStream(f));
		o.write("中国".getBytes());
		o.close();*/
		
		FileInputStream in = new FileInputStream(f);
		CompressionInputStream instr = codec.createInputStream(in);
		byte[] bs = new byte[6];
		instr.read(bs);
		System.out.println(new String(bs));
		
		/*in.read(bs);*/
		
		//System.out.println(new String(bs, "UTF-8"));
		
		
		//DefaultCodec codec  = new DefaultCodec();
		//Compressor c=codec.createCompressor();
		
		//CompressionOutputStream out = codec.createOutputStream(System.out); 
		//IOUtils.copyBytes(System.in, out, 4096, false); 
		//out.finish();
		
		/*int i = c.compress(a.getBytes(), 0, a.getBytes().length);
		System.out.println(i);*/
		
		/*Decompressor d = codec.createDecompressor();
		d.setInput(bs, 0, bs.length);
		
		
		byte[] bs1 = new byte[l];
		System.out.println(d.decompress(bs, 0, bs.length));*/
		
	
		
		/*CompressionOutputStream out = codec.createOutputStream(System.out); 
		IOUtils.copyBytes(System.in, out, 4096, true); 
		out.finish(); */
		
		//myCompress();

	}
	
	private static void myCompress() throws Exception {
		File f = new File("d:/data1");
		Class codecClass = Class.forName("org.apache.hadoop.io.compress.DefaultCodec"); 
		Configuration conf = new Configuration(); 
		
		CompressionCodec codec = (CompressionCodec) ReflectionUtils 
		.newInstance(codecClass, conf);
		
		CompressionOutputStream o = codec.createOutputStream(new FileOutputStream(f));
		o.write("中国".getBytes());
		System.out.println("中国".getBytes().length);
		o.close();
		
		
		FileInputStream in = new FileInputStream(f);
		int l = in.available();
		System.out.println(l);
		
		CompressionInputStream instr = codec.createInputStream(in);
		System.out.println(instr.available());
		byte[] bs = new byte[6];
		instr.read(bs);
		System.out.println(new String(bs));
	}
}
