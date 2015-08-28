package qsplog;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qsplog.logpattern.ClientPattern;
import qsplog.logpattern.DocSvrPattern;

public class DocSvrLogStat extends LogStat {

	public  String pattern = "[\\s]*([0-9,: -]+).* - (.+)[\\s]*: DocSvc node has finished,it spends:[\\s]*([0-9]+)[\\s]*";
	
	public DocSvrLogStat() {
		patterns.add(new DocSvrPattern());
	}
	
	public static void main(String[] args) throws IOException {
		LogStat logStat = new DocSvrLogStat();
		logStat.setLogPath("D:/log/client");
		
		List<Map> list = logStat.stat();
		logStat.outToFiles(list);
	}
	
}
