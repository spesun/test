package qsplog.ftglog;

import java.util.List;


public class FtpLog {

	
	public static String[] ips = new String[] {
			"10.46.173.141","10.46.173.168","10.46.173.171"
			/*"10.47.160.242","10.47.160.243","10.47.160.245"*/
	};
	
	public static void main(String[] args) throws Exception {
		

		String ftpLog = "/home/qsp/qsplogs";
		String downDir = "D:/logDown/";
		
		for (int i = 0; i < ips.length; i++) {
			FtpUtil ftp = new FtpUtil();
			//连接ftp服务器 
	         ftp.connectServer(ips[i], 21, "qsp", "qsp", ftpLog); 
	         List list = ftp.getFileList("."); 
	         for (int j=0;j<list.size();j++) 
	         { 
	            String filename = (String)list.get(j); 
	            if (filename.endsWith(".log")
	            		&& filename.indexOf("collect") == -1
	            		&& filename.indexOf("index") == -1
	            		&& filename.indexOf("wordlib") == -1) {
	            	System.out.println(filename); 
	            	ftp.download(filename, downDir+filename);
	            }
	            
	         } 
		}
         
	}
}
