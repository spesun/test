package qsplog;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import qsplog.logpattern.CreateSearchResultsPattern;
import qsplog.logpattern.TestPattern;
import qsplog.logpattern.rpc.RpcDocsvrPattern;
import qsplog.logpattern.rpc.RpcSearchPattern;
import qsplog.logpattern.sort.ResultSortPattern;
import qsplog.logpattern.sort.SortGetDocsvrPattern;
import qsplog.logpattern.sort.SortRemoteDocPattern;
import qsplog.logpattern.sort.SortGetLocalPattern;
import qsplog.logpattern.sort.SortGetSubIndexsPattern;
import qsplog.logpattern.sort.SortHighterPattern;
import qsplog.logpattern.sort.SortPattern;
import qsplog.logpattern.sort.SortPreCallDocPattern;
import qsplog.logpattern.sort.SortPrepareDocPattern;
import qsplog.logpattern.sort.SplitTaskPattern;

public class SortLogStat extends LogStat {

	
	public SortLogStat() {
		patterns.add(new SortPattern());
		
		
		//search_client
		patterns.add(new SortGetSubIndexsPattern());
		patterns.add(new SplitTaskPattern());
		patterns.add(new RpcSearchPattern());
		
		patterns.add(new ResultSortPattern());
		
		patterns.add(new CreateSearchResultsPattern());
		patterns.add(new RpcDocsvrPattern());
		
		//patterns.add(new SortCallThreadPattern());
		//patterns.add(new SortSearchClientPattern());
		//patterns.add(new SortThreadRunPattern());
		//patterns.add(new SortParallelSearchPattern());
		
		/*patterns.add(new SortGetDocsvrPattern());
		patterns.add(new SortGetLocalPattern());
		patterns.add(new SortHighterPattern());
		patterns.add(new SortPrepareDocPattern());*/
		//patterns.add(new SortRemoteDocPattern());
		
		//patterns.add(new SortPreCallDocPattern());
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		LogStat logStat =  new SortLogStat();
		logStat.setLogPath("D:/log/sort");
		List<Map> list = logStat.stat();
		
		logStat.outToFiles(list);	
	}

}