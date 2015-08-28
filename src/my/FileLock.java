package my;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.OverlappingFileLockException;

public class FileLock {

	public static void main(String[] args) throws Exception {
		File file = new File("d:/del/test");
		FileChannel channel = new RandomAccessFile(file, "rw").getChannel();

		java.nio.channels.FileLock lock = channel.lock();

		try {
			lock = channel.tryLock();
		} catch (OverlappingFileLockException e) {
			e.printStackTrace();
		}

		Thread.currentThread().sleep(20000);
		
		try {
			lock.release();
			channel.close();
		}

		catch (Exception e) {
		}
	}
}
