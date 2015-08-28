package my.pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestQspPattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		//doc();
	}
	
	
	public void doc() {
		String s = "2012-02-08 07:11:50,882 INFO [com.zte.qsp.searcher.docsvrnode.DocSvr.getDocResult(168)] - food: DocSvc node has finished,it spends:14";
		
		String pattern = "[\\s]*([0-9,: -]+).* - (.+)[\\s]*: DocSvc node has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		boolean b = m.matches();
		/*if (b) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}*/
	}
	
	
	static void createSearchResultsPattern() {
		String s = "2012-02-01 13:24:01,685 INFO [com.zte.qsp.searcher.sortnode.TimeoutExecutor.remoteSearch(118)] - 10.130.72.209_1395 create SearchResults by hits, spend 87";
		
		String pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*create SearchResults by hits, spend[\\s]*([0-9]+)[\\s]*";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		boolean b = m.matches();
		if (b) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}
	}
	
	
	static void search() {
		String s = "2012-02-01 13:16:56,016 INFO [com.zte.qsp.searcher.searchnode.Searcher.search(256)] - 10.130.72.209_1223 return search Hit. total hits:19 spend:1";
		
		String pattern = "[\\s]*([0-9,: -]+).* - [\\s]*([0-9._]+)[\\s]*return search Hit. total hits:[\\s]*([0-9]+)[\\s]*[\\s]*spend:([0-9]+)[\\s]*";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		boolean b = m.matches();
		if (b) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
			System.out.println(m.group(4));
		}
	}
	
	
	static void client() {
		String s = "2012-02-03 12:46:33,468 INFO  response.QSPSearcher - search 2 records, spend time:468";
		
		String pattern = "[\\s]*([0-9,: -]+).* - search[\\s]*([0-9]+)[\\s]*records, spend time:[\\s]*([0-9]+)[\\s]*";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		boolean b = m.matches();
		if (b) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}
	}
	
	static void sort() {
		String s = "2012-02-01 13:24:01,681 INFO [com.zte.qsp.searcher.sortnode.UserCall.callSearcher(47)] - 10.130.72.209_1394 sort node call search has finished,it spends:108";
		
		String pattern = "[\\s]*([0-9,: -]+).* - ([0-9_.]+)[\\s]*sort node call search has finished,it spends:[\\s]*([0-9]+)[\\s]*";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		boolean b = m.matches();
		if (b) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}
	}

}


