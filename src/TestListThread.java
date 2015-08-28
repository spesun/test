import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestListThread {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//线程中只是读取应该不需要synchronizedList，参考javadoc
		//final List l = Collections.synchronizedList(new ArrayList());
		final List l = new ArrayList();
		for (int i = 0; i < 100; i++) {
			l.add(Integer.toString(i));
		}

		
		ExecutorService es = Executors.newFixedThreadPool(120);
		List<Future> futures = new ArrayList();
		for (int i = 0; i < 10; i++) {
			ListCall call = new ListCall(l, i);
			futures.add(es.submit(call));
		}
		
		for (int i = 0; i < futures.size(); i++) {
			futures.get(i).get();
		}
		es.shutdown();
		
	}
}

class ListCall implements Callable {
	
	List l;
	int rownum;
	public ListCall(List l, int rownum) {
		this.l = l;
		this.rownum = rownum;
	}
	
	@Override
	public Object call() throws Exception {
		for (int i = 0; i < l.size(); i++) {
			System.out.println(rownum + "=" + l.get(i));
		}
		return null;
	}
}

