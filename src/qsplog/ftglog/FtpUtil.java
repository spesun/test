package qsplog.ftglog;

import java.io.DataInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.FileInputStream; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.List; 
import sun.net.*; 
import sun.net.ftp.FtpClient;

public class FtpUtil {

	
	static int FTP_SUCCESS = 1;
	static int FTP_TRY_AGAIN = 2;
	static int FTP_ERROR = 3;
	
	FtpClient ftpClient;
	
	/** 
	    * connectServer 
	    * ����ftp������ 
	    * @throws java.io.IOException 
	    * @param path �ļ��У��մ����Ŀ¼ 
	    * @param password ���� 
	    * @param user   ��½�û� 
	    * @param server ��������ַ 
	    */
	  public void connectServer(String server,int port, String user, String password,  String path) 
	  throws IOException  
	  { 
	     // server��FTP��������IP��ַ��user:��¼FTP���������û��� 
	     // password����¼FTP���������û����Ŀ��path��FTP�������ϵ�·�� 
	     ftpClient = new FtpClient(server,port); 
	     //ftpClient.openServer(); 
	     ftpClient.login(user, password); 
	     //path��ftp��������Ŀ¼����Ŀ¼ 
	     if (path.length() != 0)  ftpClient.cd(path); 
	     //��2�����ϴ������� 
	     ftpClient.binary();      
	 } 
	  
	 /** 
	    * upload 
	    * �ϴ��ļ� 
	    * @throws java.lang.Exception 
	    * @return -1 �ļ������� 
	    *          -2 �ļ�����Ϊ��  
	    *          >0 �ɹ��ϴ��������ļ��Ĵ�С 
	    * @param newname �ϴ�������ļ��� 
	    * @param filename �ϴ����ļ� 
	    */
	 public long upload(String filename,String newname) throws Exception  
	 { 
	     long result = 0; 
	     TelnetOutputStream os = null; 
	     FileInputStream is = null; 
	     try {          
	         java.io.File file_in = new java.io.File(filename); 
	         if (!file_in.exists()) return -1; 
	         if (file_in.length()==0) return -2; 
	         os = ftpClient.put(newname); 
	         result = file_in.length(); 
	         is = new FileInputStream(file_in); 
	         byte[] bytes = new byte[1024]; 
	         int c; 
	         while ((c = is.read(bytes)) != -1) { 
	              os.write(bytes, 0, c); 
	         } 
	     } finally { 
	         if (is != null) { 
	             is.close(); 
	         } 
	         if (os != null) { 
	            os.close(); 
	         } 
	     } 
	    return result; 
	 } 
	 /** 
	    * upload 
	    * @throws java.lang.Exception 
	    * @return  
	    * @param filename 
	    */
	 public long upload(String filename) 
	 throws Exception  
	 { 
	    String newname = ""; 
	    if (filename.indexOf("/")>-1) 
	    { 
	       newname = filename.substring(filename.lastIndexOf("/")+1); 
	    }else
	    { 
	       newname = filename; 
	    } 
	    return upload(filename,newname); 
	 } 
	  
	 /** 
	    *  download 
	    *  ��ftp�����ļ������� 
	    * @throws java.lang.Exception 
	    * @return  
	    * @param newfilename �������ɵ��ļ��� 
	    * @param filename �������ϵ��ļ��� 
	    */
	 public long download(String filename,String newfilename)  
	 throws Exception
	 {   
	    long result = 0; 
	    TelnetInputStream is = null; 
	    FileOutputStream os = null; 
	    try  
	    { 
	       is = ftpClient.get(filename);        
	       java.io.File outfile = new java.io.File(newfilename); 
	       os = new FileOutputStream(outfile); 
	       byte[] bytes = new byte[1024]; 
	       int c; 
	       while ((c = is.read(bytes)) != -1) { 
	           os.write(bytes, 0, c); 
	           result = result + c; 
	       } 
	    } catch (IOException e)  
	    { 
	       e.printStackTrace(); 
	    } 
	    finally { 
	         if (is != null) { 
	             is.close(); 
	         } 
	         if (os != null) { 
	            os.close(); 
	         } 
	     } 
	     return result; 
	 } 
	 /** 
	  * ȡ��ĳ��Ŀ¼�µ������ļ��б� 
	  * 
	  */
	 public List getFileList(String path) 
	 { 
	    List list = new ArrayList(); 
	    try  
	    { 
	       DataInputStream dis = new  DataInputStream(ftpClient.nameList(path)); 
	       String filename = ""; 
	       while((filename=dis.readLine())!=null)   
	       {   
	         list.add(filename);         
	       }   
	    
	    } catch (Exception e)  
	    { 
	       e.printStackTrace(); 
	    } 
	    return list; 
	 } 
	  
	 /** 
	    * closeServer 
	    * �Ͽ���ftp������������ 
	    * @throws java.io.IOException 
	    */
	 public void closeServer() 
	 throws IOException  
	 {    
	   try  
	   { 
	      if (ftpClient != null)  
	      { 
	        ftpClient.closeServer();      
	      } 
	   } catch (IOException e) { 
	      e.printStackTrace(); 
	   } 
	 } 
	 
	 public String sendCommand(String s) throws Exception {
		 ftpClient.sendServer(s);
		 
		 int reply = ftpClient.readServerResponse();
		 if (FTP_ERROR == reply) {
			 throw new Exception("FTP_ERROR");
		 }
			 
		 String str = ftpClient.getResponseString();
		 
		 //System.out.println("reply" + reply);
		 return str;
	 }
	 
	 public FtpClient getFtpClient() {
		 return ftpClient;
	 }
	   
	  public static void main(String [] args) throws Exception  
	  { 
	    FtpUtil ftp = new FtpUtil(); 
	    try { 
	         //����ftp������ 
	         ftp.connectServer("10.163.7.15",21, "cxl", "1", "info2"); 
	         /**  �ϴ��ļ��� info2 �ļ����� */
	         System.out.println("filesize:"+ftp.upload("f:/download/Install.exe")+"�ֽ�"); 
	         /** ȡ��info2�ļ����µ������ļ��б�,�����ص� E���� */
	         List list = ftp.getFileList("."); 
	         for (int i=0;i<list.size();i++) 
	         { 
	            String filename = (String)list.get(i); 
	            System.out.println(filename); 
	            ftp.download(filename,"E:/"+filename); 
	         } 
	    } catch (Exception e) { 
	       /// 
	    }finally
	    { 
	       ftp.closeServer(); 
	    } 
	  }   
	}
