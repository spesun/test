package com.ai.test.zk;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Description: 采集zooKeeper上的状态信息
 *
 * @author 银时 yinshi.nc@taobao.com
 * @Date Dec 26, 2011
 */
public class ZKServerStatusCollector implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger( ZKServerStatusCollector.class );

    public final static String EMPTY_STRING = "";
    public final static String COMMAND_CONS = "echo cons | nc %s %s";
	public final static String COMMAND_STAT = "echo stat | nc %s %s";
	public final static String COMMAND_WCHS = "echo wchs | nc %s %s";
	public final static String COMMAND_WCHC = "echo wchc | nc %s %s";
	//zookeeper 3.6.0才有
	public final static String COMMAND_RWPS = "echo rwps | nc %s %s";
	
    private static final String MODE_FOLLOWER = "Mode: follower";
    private static final String MODE_LEADERER = "Mode: leader";
    private static final String MODE_STANDALONE = "Mode: standalone";
    private static final String MODE_OBSERVER = "Mode: observer";
    private static final String NODE_COUNT = "Node count:";

    private static final String STRING_CONNECTIONS_WATCHING = "connections watching";
    private static final String STRING_PATHS = "paths";
    private static final String STRING_TOTAL_WATCHES = "Total watches:";

    private static final String STRING_SENT = "Sent:";

    private static final String STRING_RECEIVED = "Received:";

    private String ip;
    private String port;
   

    public ZKServerStatusCollector( String ip, String port) {
        this.ip = ip;
        this.port = port;
    }
    

    @Override
    public void run() {
        try {

            if( StringUtil.isBlank( ip ) || StringUtil.isBlank( port )){
                return;
            }
            ZooKeeperStatusV2 zooKeeperStatus = new ZooKeeperStatusV2();
            sshZooKeeperAndHandleStat( ip, Integer.parseInt( port ), zooKeeperStatus );
            telnetZooKeeperAndHandleWchs( ip, Integer.parseInt( port ), zooKeeperStatus );
            sshZooKeeperAndHandleWchc( ip, Integer.parseInt( port ), zooKeeperStatus );
            sshZooKeeperAndHandleRwps( ip, Integer.parseInt( port ), zooKeeperStatus);
           
            LOG.debug("zooKeeperStatus is = "  + zooKeeperStatus);
            LOG.info( "Finish #" + ip + ":" +  port);

        } catch ( Exception e ) {
            e.printStackTrace();
            LOG.error("", e);
        }
    }

    /**
     * 进行Telnet连接并进行返回处理,执行 stat命令
     */
    private void sshZooKeeperAndHandleStat( String ip, int port, ZooKeeperStatus zooKeeperStatus ) {
    	
        BufferedReader bufferedRead = null;
        StringBuffer sb = new StringBuffer();
        try {
        	
        	String cmd = String.format(COMMAND_STAT, ip , port);
        	LOG.debug(" exec cmd = " + cmd);
    		String[] cmds= {"/bin/sh","-c", cmd};
    		Process process = Runtime.getRuntime().exec(cmds);
    		bufferedRead = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            
    		//TODO
    		int i = process.waitFor();
    		
    		
            /**
             * 通常的内容是这样： Zookeeper version: 3.3.3-1073969, built on 02/23/2011
             * 22:27 GMT Clients:
             * /1.2.37.111:43681[1](queued=0,recved=434,sent=434)
             * /10.13.44.47:54811[0](queued=0,recved=1,sent=0)
             *
             * Latency min/avg/max: 0/1/227 Received: 2349 Sent: 2641
             * Outstanding: 0 Zxid: 0xc00000243 Mode: follower Node count: 8
             */
            String line = "";
            zooKeeperStatus.setIp( ip );
            List< String > clientConnectionList = new ArrayList< String >();
            while ( ( line = bufferedRead.readLine() ) != null ) {
                if ( analyseLineIfClientConnection( line ) ) { // 检查是否是客户端连接
                    clientConnectionList.add( line );
                } else if ( line.contains( MODE_FOLLOWER ) ) {
                    zooKeeperStatus.setMode( "F" );
                } else if ( line.contains( MODE_LEADERER ) ) {
                    zooKeeperStatus.setMode( "L" );
                    zooKeeperStatus.setLeader(true);
                } else if ( line.contains( MODE_STANDALONE ) ) {
                    zooKeeperStatus.setMode( "S" );
                } else if ( line.contains( MODE_OBSERVER ) ) {
                    zooKeeperStatus.setMode( "O" );
                }else if ( line.contains( NODE_COUNT ) ) {
                    zooKeeperStatus.setNodeCount( Integer.parseInt( StringUtil.trimToEmpty( line.replace( NODE_COUNT, EMPTY_STRING ) ) ) );
                } else if ( line.contains( STRING_SENT ) ) {
                    zooKeeperStatus.setSent( StringUtil.trimToEmpty( line.replace( STRING_SENT, EMPTY_STRING ) ) );
                } else if ( line.contains( STRING_RECEIVED ) ) {
                    zooKeeperStatus.setReceived( StringUtil.trimToEmpty(line.replace( STRING_RECEIVED, EMPTY_STRING ) ) );
                }
                sb.append( line ).append( "<br/>" );
            }
            zooKeeperStatus.setClientConnectionList( clientConnectionList );
            zooKeeperStatus.setStatContent( sb.toString() );
        }  catch ( Exception e ) {
            LOG.error( "程序出错:" + e.getMessage() );
        } finally {
            IOUtils.closeQuietly(bufferedRead);
        }
        
        LOG.info("stat result ="  +  sb.toString());
    }

    /** 分析一行内容, 判断是否为客户端连接 */
    private boolean analyseLineIfClientConnection( String line ) {
        if ( StringUtil.isBlank( line ) ) {
            return false;
        }
        // 标准的一行客户端连接是这样的
        // /1.2.37.111:43681[1](queued=0,recved=434,sent=434)
        line = StringUtil.trimToEmpty( line );
        if ( line.startsWith( "/" ) && StringUtil.containsIp( line ) ) {
            return true;
        }
        return false;
    }

    /**
     * 进行Telnet连接并进行执行wchs。
     */
   private void telnetZooKeeperAndHandleWchs( String ip, int port, ZooKeeperStatus zooKeeperStatus ) {

        try {
            if ( StringUtil.isBlank( ip, port + EMPTY_STRING ) ) {
                LOG.warn( "Ip is empty" );
                return;
            }
            
            String cmd = String.format(COMMAND_WCHS, ip , port);
        	LOG.debug(" exec cmd = " + cmd);
    		String[] cmds= {"/bin/sh","-c", cmd};
    		Process process = Runtime.getRuntime().exec(cmds);
    		StringBuffer rstLog = new StringBuffer();
    		StringUtil.getProcessRst(process, rstLog);
            String wchsOutput = rstLog.toString();
            
            LOG.debug("wchs result = " + wchsOutput);
           /**
             * Example: 59 connections watching 161 paths Total watches:405
             */
            if ( StringUtil.isBlank( wchsOutput ) ) {
                LOG.warn( "No output execute " + cmd );
                return;
            }

            String[] wchsOutputArray = wchsOutput.split( StringUtil.BR );
            if ( 2 != wchsOutputArray.length ) {
                LOG.warn( "Illegal output of command " + cmd + ":" + wchsOutput );
                return;
            }
            String firstLine = wchsOutputArray[0].replace( STRING_CONNECTIONS_WATCHING, StringUtil.WORD_SEPARATOR ).replace( STRING_PATHS, EMPTY_STRING );
            String[] firstLineArray = firstLine.split( StringUtil.WORD_SEPARATOR );


            int watchedPaths = Integer.parseInt( StringUtil.trimToEmpty( firstLineArray[1] ) );
            zooKeeperStatus.setWatchedPaths( watchedPaths );

            // 分析第二行来获取watches数
            String secondtLine = wchsOutputArray[1].replace( STRING_TOTAL_WATCHES, EMPTY_STRING );
            int watches = Integer.parseInt( StringUtil.trimToEmpty( secondtLine ) );
            zooKeeperStatus.setWatches( watches );
        } catch ( Exception e ) {
            LOG.error( "",  e.getMessage() );
        }
    }

    /**
     * 进行Telnet连接并进行执行wchc。
     *
     * @throws Exception
     */
    private void sshZooKeeperAndHandleWchc( String ip, int port, ZooKeeperStatus zooKeeperStatus) {


        try {
            if ( StringUtil.isBlank( ip, port + EMPTY_STRING ) ) {
                LOG.warn( "Ip is empty" );
                return;
            }
            
            String cmd = String.format(COMMAND_WCHC, ip , port);
        	LOG.debug(" exec cmd = " + cmd);
    		String[] cmds= {"/bin/sh","-c", cmd};
    		Process process = Runtime.getRuntime().exec(cmds);
    		StringBuffer rstLog = new StringBuffer();
    		StringUtil.getProcessRst(process, rstLog);
            String wchcOutput = rstLog.toString();

            LOG.debug("wchc result = " + wchcOutput);
            if ( StringUtil.isBlank( wchcOutput ) ) {
                LOG.warn( "No output execute " + cmd );
                return;
            }

            StringBuffer wchcOutputWithIp = new StringBuffer();
            String[] wchcOutputArray = wchcOutput.split( StringUtil.BR );
            if ( 0 == wchcOutputArray.length ) {
                LOG.warn( "No output of command " + cmd );
                return;
            }
            Map< String, List< String > > watchedPathMap = new HashMap< String, List< String > >();
            String sessionId = EMPTY_STRING;
            List< String > watchedPathList = new ArrayList< String >();

            for ( String line : wchcOutputArray ) {
                if ( StringUtil.isBlank( line ) ) {
                    wchcOutputWithIp.append( line ).append( StringUtil.BR );
                    continue;
                } else if ( line.startsWith( "0x" ) ) {
                    // 将上次list放入
                    if ( !StringUtil.isBlank( sessionId ) ) {
                        watchedPathMap.put( sessionId, watchedPathList );
                    }

                    sessionId = StringUtil.trimToEmpty( line );
                    
                    wchcOutputWithIp.append( sessionId ).append( StringUtil.BR );
                } else {
                    watchedPathList.add( StringUtil.trimToEmpty( line ) );
                    wchcOutputWithIp.append( line ).append( StringUtil.BR );
                }
            }// 遍历wchc返回的内容
            
            LOG.debug( ip + " 的所有Watch情况是:" + watchedPathMap.keySet() );
            zooKeeperStatus.setWatchedPathMap( watchedPathMap );
            zooKeeperStatus.setWatchedPathMapContent( wchcOutputWithIp.toString() );
        }  catch ( Exception e ) {
            LOG.error( "程序错误: " + e.getMessage() );
            e.printStackTrace();
        }
    }


    /**
     * 进行Telnet连接并进行执行rwps。
     *
     * @throws Exception
     */
    private void sshZooKeeperAndHandleRwps( String ip, int port, ZooKeeperStatusV2 zooKeeperStatus) {

        try {
            if ( StringUtil.isBlank( ip, port + EMPTY_STRING ) ) {
                LOG.warn( "Ip is empty" );
                return;
            }
            
            String cmd = String.format(COMMAND_RWPS, ip , port);
        	LOG.debug(" exec cmd = " + cmd);
    		String[] cmds= {"/bin/sh","-c", cmd};
    		Process process = Runtime.getRuntime().exec(cmds);
    		StringBuffer rstLog = new StringBuffer();
    		StringUtil.getProcessRst(process, rstLog);
            String rwpsOutput = rstLog.toString();
            
            LOG.debug("rwps result = " + rwpsOutput);
            /**
             * RealTime R/W Statistics:
             * getChildren2:   0.0
             *
             * createSession:  0.0
             *
             * closeSession:   0.1
             *
             * setData:        11.9
             *
             * setWatches:     0.0
             *
             * getChildren:    27.9
             *
             * delete:         0.0
             *
             * create:         0.0
             *
             * exists:         51.5
             *
             * getDate:        2881.1
             */
            if ( StringUtil.isBlank( rwpsOutput ) ) {
                LOG.warn( "No output execute " + cmd );
                return;
            }

            String[] wchcOutputArray = rwpsOutput.split( StringUtil.BR );
            if ( 0 == wchcOutputArray.length ) {
                LOG.warn( "No output of command " + cmd );
                return;
            }
            

            double getChildren2 = 0, createSession = 0, closeSession = 0, setData = 0, setWatches = 0, getChildren = 0, delete=0,
                    create=0,exists=0,getData=0;

            ZooKeeperStatusV2.RWStatistics rwps = null;

            for ( String line : wchcOutputArray ) {

                if ( StringUtil.isBlank( line ) ) {
                    continue;
                } else if ( line.contains( "getChildren2" ) ) {

                    line = line.substring( line.indexOf( "getChildren2" ) + ( "getChildren2".length() + 1 ) );
                    getChildren2 = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "createSession" ) ) {

                    line = line.substring( line.indexOf( "createSession" ) + ( "createSession".length() + 1 ) );
                    createSession = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                } else if ( line.contains( "closeSession" ) ) {

                    line = line.substring( line.indexOf( "closeSession" ) + ( "closeSession".length() + 1 ) );
                    closeSession = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "setData" ) ) {

                    line = line.substring( line.indexOf( "setData" ) + ( "setData".length() + 1 ) );
                    setData = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "setWatches" ) ) {

                    line = line.substring( line.indexOf( "setWatches" ) + ( "setWatches".length() + 1 ) );
                    setWatches = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "getChildren" ) ) {

                    line = line.substring( line.indexOf( "getChildren" ) + ( "getChildren".length() + 1 ) );
                    getChildren = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "delete" ) ) {

                    line = line.substring( line.indexOf( "delete" ) + ( "delete".length() + 1 ) );
                    delete =  Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "create" ) ) {

                    line = line.substring( line.indexOf( "create" ) + ( "create".length() + 1 ) );
                    create = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "exists" ) ) {

                    line = line.substring( line.indexOf( "exists" ) + ( "exists".length() + 1 ) );
                    exists = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else if ( line.contains( "getDate" ) ) {

                    line = line.substring( line.indexOf( "getDate" ) + ( "getDate".length() + 1 ) );
                    getData = Math.round( Double.valueOf( StringUtil.trimToEmpty( line ) ) * 100 )/100;

                }else {
                    continue;
                }
            }// 遍历rwps返回的内容

            rwps = new ZooKeeperStatusV2.RWStatistics( getChildren2, createSession, closeSession, setData, setWatches, getChildren, delete, create, exists, getData );
            zooKeeperStatus.setRwps( rwps );


        }  catch ( Exception e ) {
            LOG.error( "程序错误: " + e.getMessage() );
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
		ZKServerStatusCollector c = new ZKServerStatusCollector("hadoop3", "2181");
		new Thread(c).start();
		
	}

}