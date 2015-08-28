package qsplog;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import qsplog.logpattern.TestPattern;
import qsplog.logpattern.search.GetLuceneQueryPattern;
import qsplog.logpattern.search.GetValuePattern;
import qsplog.logpattern.search.ExtendsWordPattern;
import qsplog.logpattern.search.SearchLucenePattern;
import qsplog.logpattern.search.SearchPattern;

public class SearchLogStat extends LogStat {

	public  String pattern = "";
	public SearchLogStat() {
		
		patterns.add(new SearchPattern());
		//patterns.add(new PreparePattern());
		patterns.add(new SearchLucenePattern());
		patterns.add(new GetValuePattern());
		patterns.add(new GetLuceneQueryPattern());
		
		//patterns.add(new ExtendsWordPattern());
		//patterns.add(new TestPattern());
		
		
	}

	public static void main(String[] args) throws IOException {
		LogStat logStat = new SearchLogStat();
		logStat.setLogPath("D:/log/search");
		List<Map> list = logStat.stat();
		logStat.outToFiles(list);
	}
}
