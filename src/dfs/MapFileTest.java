package dfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;

public class MapFileTest {

	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();   
		FileSystem fs=new LocalFileSystem();   
		fs.initialize(new URI("/home/mr"), conf);
		Path mapFile=new Path("mapFile.map");   
		
		//Reader内部类用于文件的读取操作    
		//MapFile.Reader reader=new MapFile.Reader(fs,mapFile.toString(),conf);
		
		//Writer内部类用于文件的写操�?假设Key和Value都为Text类型    
		MapFile.Writer writer=new MapFile.Writer(conf,fs,mapFile.toString(),Text.class,Text.class);   
		//通过writer向文档中写入记录    
		writer.append(new Text("key"),new Text("value"));   
		IOUtils.closeStream(writer);//关闭write�?   
		//通过reader从文档中读取记录    
		/*Text key=new Text();   
		Text value=new Text();   
		while(reader.next(key,value)){   
		    System.out.println(key);   
		    System.out.println(key);   
		}   
		IOUtils.closeStream(reader);//关闭read�? 
*/
	}
}
