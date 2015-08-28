package dfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

public class SeqFileTest {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		System.out.println(fs.getClass().toString());
		Path seqFile = new Path("D:/testexport/myseq");
		System.out.println(seqFile.toString());
		
		/*// Reader内部类用于文件的读取操作
		SequenceFile.Reader reader = new SequenceFile.Reader(fs, seqFile, conf);
		// 通过reader从文档中读取记录
		ImmutableBytesWritable key = new ImmutableBytesWritable();
		Result value = new Result();
		while (reader.next(key, value)) {
			System.out.println(new String(key.get()));
			System.out.println(value);
		}
		IOUtils.closeStream(reader);// 关闭read*/
		
		
		// Writer内部类用于文件的写操�?假设Key和Value都为Text类型
		SequenceFile.Writer writer = new SequenceFile.Writer(fs, conf, seqFile, Text.class, Text.class);
		// 通过writer向文档中写入记录
		writer.append(new Text("key"), new Text("value"));
		writer.append(new Text("key1"), new Text("value1"));
		IOUtils.closeStream(writer);// 关闭writer
		
	}

}
