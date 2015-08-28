package rpc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;

import com.zte.qsp.searcher.ipc.RPC;

public class testRpcClient {

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, IOException, InterruptedException {

		
		String[] tmp;
		try {
			Method method = RpcImpl.class.getMethod("ping",
					new Class[] { String.class });
			method.setAccessible(true);
			String[][] querys = new String[][] { { "rpc in" } };
			InetSocketAddress[] addrs = new InetSocketAddress[] { new InetSocketAddress(
					"10.46.173.154", 8888) };

			Configuration conf = new Configuration();
			
			// 性能测试日志的增加
			long start = System.currentTimeMillis();
			tmp = (String[]) RPC.call(method, querys, addrs, /* null, */conf);

			System.out.println(tmp[0]);
			System.out.println("aa = " + ( System.currentTimeMillis() - start));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		for (int i = 0; i < 1; i++) {
			Thread t = new Thread(new Runnable() {
	
				@Override
				public void run() {
					
				}
			});
			
			t.start();
		}*/

		System.out.println();
		System.out.println();
		
		Object o = new Object();
		synchronized (o) {
			((Object) o).wait(1000);
		}

		Method method = RpcImpl.class.getMethod("ping",
				new Class[] { String.class });
		method.setAccessible(true);
		String[][] querys = new String[][] { { "rpc in" } };
		InetSocketAddress[] addrs = new InetSocketAddress[] { new InetSocketAddress(
				"10.46.173.154", 8888) };

		Configuration conf = new Configuration();
		
		// 性能测试日志的增加
		long start = System.currentTimeMillis();
		tmp = (String[]) RPC.call(method, querys, addrs, /* null, */conf);

		System.out.println(tmp[0]);
		System.out.println("aa = " + ( System.currentTimeMillis() - start));
	}

}
