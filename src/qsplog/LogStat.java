package qsplog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import qsplog.logpattern.PatternOne;

public abstract class LogStat  {
	
	public static Set<TotalBean> total = Collections.synchronizedSet(new HashSet());
	public static String total_out = "d:/";
	
	List<PatternOne> patterns = new ArrayList<PatternOne>();

	public  String outDire = "";
	public  String logPath = "";

	protected List<File> logFiles = new ArrayList<File>();
	
	List<Map> statResult = new ArrayList<Map>();
	String outFileName = "out.csv";
	

	
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	
	public void setLogPath(String logPath) {
		this.logPath = logPath;
		this.outDire = logPath;
	}
	
	public void addLogFile(File file) {
		logFiles.add(file);
		
		outDire = file.getParentFile().getAbsolutePath();
	}
	
	
	public LogStat() {
	}
	
	public List<Map> stat() throws IOException {
		
		total_out = outDire;
		if (logPath != null && !"".equals(logPath)) {
			File f = new File(logPath);
			logFiles = getFiles(f);
		}
		
		if (logFiles.size() < 1) {
			System.err.println(this.getClass().getName() + " input logFiles.size == 0");
			return new ArrayList();
		}
		
		for (int i = 0; i < logFiles.size(); i++) {
			doOneFile(logFiles.get(i));
		}

		return statResult;
	}
	
	public boolean outToFiles(List<Map> list) {
		
		Map<String,List> all = new HashMap();
		
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			String outFileName = (String)map.get("outFileName");
			if (all.containsKey(outFileName)) {
				
				all.get(outFileName).add(map);
			} else {
				List tmp = new ArrayList();
				tmp.add(map);
				
				all.put(outFileName, tmp);
			}
			
			map.remove(("outFileName"));
		}
		
		for(Entry<String, List> e: all.entrySet()) {
			String outFileName = e.getKey();
			List val = e.getValue();
			
			setOutFileName(outFileName);
			out(val);
		}
		
		return true;
		
	}
	

    private boolean out(List<Map> list) {
		String outPath = null;
		
		if (list.size()==0) {
			System.out.println(this.getClass().getName() + " out list.size ==0");
			return false;
		}
		
		
		
		File f = new File(outDire);
		if (f.isDirectory() ) {
			outPath = f.getAbsolutePath() + File.separatorChar + outFileName;
		} else {
			File parent = f.getParentFile();
			outPath = parent.getAbsolutePath() + File.separatorChar+ outFileName;
		}
		
		long count = list.size();
		long maxCost = 0;
		long minCost = Long.MAX_VALUE;
		long sumCost = 0;
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outPath));
			
			//先输出一行title
			StringBuffer title = new StringBuffer();
			if (list.size() > 0) {
				Map map = list.get(0);
				Set<Entry<String, String>> set = map.entrySet();
				for (Entry<String, String> e : set) {
					//整行不需要输出
					String key = e.getKey();
					/*if ("line".equals(key)) {
						continue;
					}*/
					
					title.append(key);
					title.append(",");
				}
			}
			
			title.deleteCharAt(title.length()-1);
			writer.write(title.toString());
			writer.newLine();
			
			for (int i = 0; i < list.size(); i++) {
				
				StringBuffer valLine = new StringBuffer();
				
				Map map = list.get(i);
				Set<Entry<String, String>> set = map.entrySet();
				for (Entry<String, String> e : set) {
					
					String key = e.getKey();
					/*
					//整行不需要输出
					if ("line".equals(key)) {
						continue;
					}*/
					
					String value = e.getValue();
					valLine.append(value);
					valLine.append(",");
					
					if ("cost".equals(key)) {
						int cost = Integer.parseInt(value);
						sumCost = sumCost + cost;
						if (cost > maxCost) {
							maxCost = cost;
						}
						
						if (cost < minCost) {
							minCost = cost;
						}
						
					}
				}
				
				valLine.deleteCharAt(valLine.length()-1);
				writer.write(valLine.toString());
				writer.newLine();
			}
			
			writer.newLine();
			StringBuffer stat = new StringBuffer();
			stat.append("maxCost:"+  maxCost+";");
			stat.append("minCost:"+  minCost+";");
			stat.append("count:" +  count+";");
			
			long averageCost = sumCost/count;
			stat.append("averageCost:"+  sumCost/count+";");
			writer.write(stat.toString());
			
			TotalBean tt = new TotalBean();
			tt.setMaxCost(maxCost);
			tt.setMinCost(minCost);
			tt.setCount(count);
			tt.setOutFileName(outFileName);
			tt.setAverageCost(averageCost);
			total.add(tt);
			
			writer.close();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		System.out.println("end :" + outPath);
		return true;
	}

	public List<Map> doOneFile(File file) {
		
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(file));
	
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
	
				
				setOneLine(line, file.getName());
			}
			
			
			reader.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return statResult;
	}
	
	
	
	protected  void setOneLine(String line, String fileName) {
		
		
		for (int i = 0; i < patterns.size(); i++) {
			PatternOne one = patterns.get(i);
			Map map = one.match(line);
			if (map != null) {
				map.put("fileName", fileName);
				statResult.add(map);
				break;
				
			} 
		}
		
	}

	public List<File> getFiles(File inFile) {
		
		List<File> files = new ArrayList<File>();
		if (inFile.isDirectory()) {
			File[] tmp =  inFile.listFiles();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isDirectory()) {
					files.addAll(getFiles(tmp[i]));
				} else {
					
					if (tmp[i].getName().endsWith(".csv")) {
						//System.out.println(".csv");
						continue;
					}
					
					files.add(tmp[i]);
				}
			}
			
		} else {
			files.add(inFile);
		}
		
		return files;
	}

	
	public static void main(String[] args) throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
		
		String inPath = "D:/logDown";
		if (args.length > 0) {
			inPath = args[0];
		}
		
		//分布式，单机
		String type="fenbu";
		if (args.length >= 2) {
			type = args[1];
		}		
		
		//单机版时都在sortserver上
		String sortserver = "sortserver";
		String searchserver = "searchserver";
		String docsvrserver = "docsvrserver";
		if ("danji".equals(type)) {
			searchserver = "sortserver";
			docsvrserver = "sortserver";
		} 
		
		File path = new File(inPath);
		File[] files = path.listFiles();
		
	          
		
		final LogStat clientStat = new ClientLogStat();
		final LogStat sortStat =  new SortLogStat();
		final LogStat searchStat = new SearchLogStat();
		final LogStat docSvrStat = new DocSvrLogStat();
		
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			
			if (f.isDirectory()) {
				continue;
			}
			
			boolean addLog = false;
			
			if (f.getName().endsWith(".log")) {
				//需要考虑单机的情况
				if (f.getName().indexOf(sortserver) != -1) {
					addLog = true;
					sortStat.addLogFile(f);
				} 
				if (f.getName().indexOf(searchserver) != -1) {
					addLog = true;
					searchStat.addLogFile(f);
				} 
				if (f.getName().indexOf(docsvrserver) != -1) {
					addLog = true;
					docSvrStat.addLogFile(f);
				} 
				
				if (!addLog) {
					clientStat.addLogFile(f);
				}
			}
			
		}
		final LogStat[] logstats = new LogStat[4];
		logstats[0] = clientStat;  
		logstats[1] = sortStat; 
		logstats[2] = searchStat; 
		logstats[3] = docSvrStat; 
		
		//多线程的处理
		ThreadGroup group = new ThreadGroup("logStat");
		for (int i = 0; i < logstats.length; i++) {
			final LogStat logStat = logstats[i];
			
			new Thread(group, ""){
				
				@Override
				public void run() {
					try {
						long t1 = (System.currentTimeMillis());
						List list = logStat.stat();
						//System.out.println(logStat.getClass().getName() + "  Stat end = " + (System.currentTimeMillis() - t1));
						
						t1 = (System.currentTimeMillis());
						logStat.outToFiles(list);
						//System.out.println(logStat.getClass().getName() + "  outToFiles end = " + (System.currentTimeMillis() - t1));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		         
		int activeCount = group.activeCount();
		Thread[] threads = new Thread[activeCount];
		int n = group.enumerate(threads) ;
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		System.out.println(" each end  = " + (System.currentTimeMillis() - start));
		
		outTotalCsv();
		
		System.out.println(" totalTime = " + (System.currentTimeMillis() - start));

	}

	public static void outTotalCsv() throws IOException {
		
		Set<TotalBean> set = new TreeSet<TotalBean>(new Comparator<TotalBean>() {

			@Override
			public int compare(TotalBean o1, TotalBean o2) {
				int order1 = Integer.parseInt(o1.getOutFileName().replaceAll("\\D", ""));
				int order2 = Integer.parseInt(o2.getOutFileName().replaceAll("\\D", ""));
				return  order1-order2;
			}
		});
		
		set.addAll(LogStat.total);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(total_out + "/totalStatLog.csv"));
		
		
		//输出title
		StringBuffer title = new StringBuffer();
		title.append("fileName"+ ",");
		title.append("maxCost"+ ",");
		title.append("minCost"+ ",");
		title.append("count"+ ",");
		title.append("averageCost"+ ",");
		
		writer.write(title.toString());
		writer.newLine();

		//输出内容
		TotalBean[] ts= set.toArray(new TotalBean[0]);		
		for (int i = 0; i < ts.length; i++) {
			StringBuffer line = new StringBuffer();
			line.append(ts[i].getOutFileName() + ",");
			line.append(ts[i].getMaxCost() + ",");
			line.append(ts[i].getMinCost() + ",");
			line.append(ts[i].getCount() + ",");
			line.append(ts[i].getAverageCost() + ",");
			writer.write(line.toString());
			writer.newLine();
		}
				
		writer.close();
	}
}
