package qsplog.logpattern.rpc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;


public class RpcDocsvrPattern  extends RpcPattern{

	
	public RpcDocsvrPattern() {
		
		pattern = "[\\s]*([0-9,: -]+).* - Method:.*DocSvrTask.* has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_rpc_docsvr";
		
		order = LogConstant.order_sort_rpc_docsvr;
		outFileName = "out" + order +"_sort_rpc_docsvr.csv";
		
		p = Pattern.compile(pattern);
		
	}
	
	

}
