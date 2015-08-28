package qsplog.logpattern.rpc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.LogConstant;


public class RpcSearchPattern  extends RpcPattern{

	
	public RpcSearchPattern() {
		pattern = "[\\s]*([0-9,: -]+).* - Method:.*SearchTask.* has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		patternName = "sort_rpc_search";
		
		order = LogConstant.order_sort_rpc_search;

		outFileName = "out" + order +"_sort_rpc_search.csv";
		p = Pattern.compile(pattern);
	}
	
	
	
	public static void main(String[] args) {
		String s = "2012-02-22 16:06:15,309 INFO [com.zte.qsp.searcher.ipc.QspRPC.call(133)] - Method:public com.zte.qsp.searcher.searchnode.DistributeResult com.zte.qsp.searcher.searchnode.Searcher.search(com.zte.qsp.searcher.searchtask.SearchTask) throws java.io.IOException has finished,it spends:131";
		RpcSearchPattern p = new RpcSearchPattern();
		System.out.println(p.match(s));
		
	}

}
