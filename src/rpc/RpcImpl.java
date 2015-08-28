package rpc;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RpcImpl implements IRpc
{

	public static final Log LOG = LogFactory.getLog(RpcImpl.class);
	public String ping(String in) {
		
		long start = System.currentTimeMillis();
		System.out.println(in);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = "rpc result";
		
		LOG.info("---- business spend:" + (System.currentTimeMillis()-start));
		return result;
	}

	@Override
	public long getProtocolVersion(String protocol, long clientVersion)
			throws IOException {
		
		return 0;
	}
	
}
