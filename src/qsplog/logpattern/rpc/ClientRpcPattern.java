package qsplog.logpattern.rpc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;


public class ClientRpcPattern  extends RpcPattern{

	
	public ClientRpcPattern() {
		pattern = "[\\s]*([0-9,: -]+).* - Method:.*QueryFactor.* has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		patternName = "client_rpc";
		
		order = LogConstant.order_client_prc;

		outFileName = getOutFileName();
		p = Pattern.compile(pattern);
	}
	
	
	
	public static void main(String[] args) {
		
		
	}

}
