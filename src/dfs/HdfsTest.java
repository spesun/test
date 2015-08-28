package dfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsTest {

	public static void main(String[] args) throws Exception {
		createNewHDFSFile("/tmp/test_del", "mytest");
		listAll("/tmp");
		
		byte[] bs = readHDFSFile("hdfs://ods2:8020", 
				"/tmp/test_del");
		
		System.out.println(new String(bs, "UTF-8"));
	}
	
	public static void createNewHDFSFile(String toCreateFilePath, String content) throws IOException
    {
        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(config);
        
        FSDataOutputStream os = hdfs.create(new Path(toCreateFilePath));

        os.write(content.getBytes("UTF-8"));
        
        os.close();
        
        hdfs.close();
    }
	
	public static void listAll(String dir) throws IOException
    {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        
        FileStatus[] stats = fs.listStatus(new Path(dir));
        
        for(int i = 0; i < stats.length; ++i)
        {
            
             System.out.println(stats[i].getPath().toString());
       
                 
        }
        fs.close();
    }
	
	/** read the hdfs file content
     * notice that the dst is the full path name
     */
    public static byte[] readHDFSFile(String uri, String dst) throws Exception
    {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        
        // check if the file exists
        Path path = new Path(dst);
        if ( fs.exists(path) )
        {
            FSDataInputStream is = fs.open(path);
            // get the file info to create the buffer
            FileStatus stat = fs.getFileStatus(path);
            
            // create the buffer
            byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
            is.readFully(0, buffer);
            
            is.close();
            fs.close();
            
            return buffer;
        }
        else
        {
            throw new Exception("the file is not found .");
        }
    }

}
