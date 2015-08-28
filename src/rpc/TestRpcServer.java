package rpc;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import com.zte.qsp.searcher.ipc.RPC;
import com.zte.qsp.searcher.ipc.Server;

public class TestRpcServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		RpcImpl userCall = new RpcImpl();
		Server server = RPC.getServer(userCall, "10.46.173.154", 8888,
				30, false, new Configuration());
		server.setSocketSendBufSize(1024);
		server.start();
		server.join();
	}
}
