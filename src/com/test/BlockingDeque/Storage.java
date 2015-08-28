package com.test.BlockingDeque;


import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Storage {

	private static Log LOG = LogFactory.getLog(Storage.class);
	
	int max_size = 1000;
	LinkedBlockingDeque<String> list = new LinkedBlockingDeque<String>(1);

	public void produe(String str) {
		if (list.size() == max_size) {
			LOG.info(" queue is full ");
		}
		
		try {
			list.put(str);
		} catch (InterruptedException e) {
			LOG.error("produe Interrupted", e);
		}
	}
	
	
	public String consume() {
		if (list.size() == 0) {
			LOG.info(" queue is empty ");
		}
		
		try {
			return list.take();
		} catch (InterruptedException e) {
			LOG.error("consume Interrupted", e);
		}
		
		return null;
	}
	
	public void close() {
		list.clear();
	}
	
	
	public static void testThread() {
		 
		 final Storage s = new Storage();
		 final Vector<Future>  v = new Vector();
		 
		 
		 ExecutorService produceThreadPool = Executors.newFixedThreadPool(1);
		 
		 Future f = produceThreadPool.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				s.produe("1");
				s.produe("2");

				System.out.println("sleep");
				Thread.currentThread().sleep(10000);
				
				System.out.println("thread end");
				return true;
			}
		});
		 
		 v.add(f);
		 
		 produceThreadPool.shutdownNow();
		 
		 for (int i = 0; i < v.size(); i++) {
			try {
				v.get(i).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		 
		 System.out.println(" all end ");
		 
	 }
	
	public static void main(String[] args) {
		testThread();
	}
	
}
