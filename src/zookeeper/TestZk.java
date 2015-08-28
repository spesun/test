package zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class TestZk {

	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		ZooKeeper zk = new ZooKeeper("localhost:2181" ,30000, new Watcher() {
			 public void process(WatchedEvent event) { 
	                System.out.println("�Ѿ�������" + event.getType() + "�¼���"); 
	            } 
		});
		
		// ����һ��Ŀ¼�ڵ�
		zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,  CreateMode.PERSISTENT); 
		
		
		// ����һ����Ŀ¼�ڵ�
		zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		
		System.out.println(new String(zk.getData("/testRootPath",false,null))); 
		 // ȡ����Ŀ¼�ڵ��б�
		 System.out.println(zk.getChildren("/testRootPath",false)); 
		 // �޸���Ŀ¼�ڵ�����
		 zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(), -1);
		 
		 System.out.println("Ŀ¼�ڵ�״̬��["+zk.exists("/testRootPath",false)+"]"); 
		 // ��������һ����Ŀ¼�ڵ�
		 zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		
		 //System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
		 //existsҲ�ܼ�ص� NodeDataChanged
		  zk.exists("/testRootPath/testChildPathTwo", true);
		 // ��������
		 zk.setData("/testRootPath/testChildPathTwo","modifyChildDataOne".getBytes(), -1);
		 
		 //�������»�ȡ�����ܼ�����delete�¼�
		 zk.getData("/testRootPath/testChildPathTwo",true,null);
		 zk.delete("/testRootPath/testChildPathTwo",-1); 
		 
		 
		 zk.delete("/testRootPath/testChildPathOne",-1); 
		 // ɾ����Ŀ¼�ڵ�
		 zk.delete("/testRootPath",-1); 
		 
		 
		 
		 
		 
		 // �ر�����
		 zk.close(); 
		 
		 
		 
	}
}
