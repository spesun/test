package qsplog.test;

import qsplog.ftglog.FtpLog;
import qsplog.ftglog.FtpUtil;



public class FtpCommand {

	public static void main(String[] args) throws Exception {
		
		String[] ips = FtpLog.ips;
		
		String ftpLog = "/home/qsp/qsplogs";
		for (int i = 0; i < ips.length; i++) {
			FtpUtil ftp = new FtpUtil();
			//连接ftp服务器 
	         ftp.connectServer(ips[i], 21, "qsp", "qsp", ftpLog); 
	         
	        
	         String response = ftp.sendCommand("pwd\r\n");
	         System.out.println(response);
		}
	}
}
