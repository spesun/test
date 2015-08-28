package qsplog;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import qsplog.logpattern.ClientPattern;
import qsplog.logpattern.rpc.ClientRpcPattern;

public class ClientLogStat extends LogStat {

	public  String pattern = "";
	
	public static String RPC_Pattern = "";
	
	public ClientLogStat() {
		
		patterns.add(new ClientPattern());
		patterns.add(new ClientRpcPattern());
	}
	
	public static void main(String[] args) throws IOException {
		LogStat logStat = new ClientLogStat();
		logStat.setLogPath("D:/log/hadoop.log");
		
		List<Map> list = logStat.stat();
		logStat.outToFiles(list);
	}
	
}
