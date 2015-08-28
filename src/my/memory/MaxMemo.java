package my.memory;

import java.util.ArrayList;
import java.util.List;

public class MaxMemo {

	public static void main(String[] args) throws Exception {

		int mb = 1024 * 1024;

		// Getting the runtime reference from system
		
		/*Runtime runtime = Runtime.getRuntime();

		System.out.println("##### Heap utilization statistics [MB] #####");
		// use memory
		List testList = new ArrayList();
		for (int i = 0; i < 5000; i++) {
			Thread.sleep(1000);
			for (int i1 = 0; i1 < 5000; i1++) {
				testList.add(new String[1000]);
			}
			System.out.println("Used Memory:"
							+ (runtime.totalMemory() - runtime.freeMemory())/ mb + "M");

			System.out.println("total Memory:" + runtime.totalMemory() / mb + "M");
			System.out.println("Max Memory:" + runtime.maxMemory() / mb + "M");
			
		}

		System.out.println("Free Memory:" + runtime.freeMemory() / mb + "M");
		System.out.println("Total Memory:" + runtime.totalMemory() / mb + "M");*/
		
		Runtime runtime = Runtime.getRuntime();
		System.out.println((runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory()))/mb);

	}

}
