package my.memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class manageTest {

	
	public static void main(String[] args) throws Exception {
		MemoryMXBean s = ManagementFactory.getMemoryMXBean();
		
		MemoryUsage memo = s.getHeapMemoryUsage();
		MemoryUsage nonmemo = s.getNonHeapMemoryUsage();
		
		System.out.println(memo.getUsed());
		System.out.println(nonmemo.getUsed());
		
		Thread.sleep(100000);
		
		
		
	}
}
