package nio;
/**
 * Fly_m at 2009-5-20
 */

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/** @author Fly_m */
//ģ�����ط���
public class DownloadServer<T> implements Callable<T>{
	private Selector selector;//����ȫ��selector
	private Map<SocketChannel, Handle> map = new HashMap<SocketChannel, Handle>();//socketChannel��handle֮���ӳ��

	//����һ��������serverSocketChannel,����selector����ע��
	public DownloadServer() throws Exception {
		selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(1234));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	//��selector.select���е���,�����ν��д���
	public T call() throws Exception {
		System.out.println("startTo listen in 1234....");
		for(; ;) {
			selector.select();
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			while(keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if(key.isValid())
					handle(key);
				keyIterator.remove();
			}
		}
	}

	//����ÿ��key,����acceptable��key,��������д���,�������¼�,�����ڲ�����д���
	private void handle(final SelectionKey key) throws Exception {
		if(key.isAcceptable()) {
			ServerSocketChannel channel = (ServerSocketChannel) key.channel();
			SocketChannel socketChannel = channel.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);//ע����¼�
			map.put(socketChannel, new Handle());//��socket��handle���а�
		}
		//��map�е�handle����read��write�¼�,��ģ�����ļ�ͬʱ��������
		if(key.isReadable() || key.isWritable()) {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			final Handle handle = map.get(socketChannel);
			if(handle != null)
				handle.handle(key);
		}
	}

	//�ڲ���,ģ��һ���ڲ��ദ��һ���ļ����ط���,�������Դ������ļ����ط���
	private class Handle{
		private StringBuilder message;
		private boolean writeOK = true;
		private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		private FileChannel fileChannel;
		private String fileName;

		private void handle(SelectionKey key) throws Exception {
			if(key.isReadable()) {
				SocketChannel socketChannel = (SocketChannel) key.channel();
				if(writeOK)
					message = new StringBuilder();
				while(true) {
					byteBuffer.clear();
					int r = socketChannel.read(byteBuffer);
					if(r == 0)
						break;
					if(r == -1) {
						socketChannel.close();
						key.cancel();
						return;
					}
					message.append(new String(byteBuffer.array(), 0, r));
				}
				//�����յ�����Ϣת�����ļ���,��ӳ�䵽�������ϵ�ָ���ļ�
				if(writeOK && invokeMessage(message)) {
					socketChannel.register(selector, SelectionKey.OP_WRITE);
					writeOK = false;
				}
			}
			//��ͻ���д����
			if(key.isWritable()) {
				if(!key.isValid())
					return;
				SocketChannel socketChannel = (SocketChannel) key.channel();
				if(fileChannel == null)
					fileChannel = new FileInputStream(fileName).getChannel();
				byteBuffer.clear();
				int w = fileChannel.read(byteBuffer);
				//����ļ���д��,��ص�key��socket
				if(w <= 0) {
					fileName = null;
					fileChannel.close();
					fileChannel = null;
					writeOK = true;
					socketChannel.close();
					key.channel();
					return;
				}
				byteBuffer.flip();
				socketChannel.write(byteBuffer);
			}
		}

		//����Ϣת�����ļ���
		private boolean invokeMessage(StringBuilder message) {
			String m = message.toString();
			try {
				File f = new File(m);
				if(!f.exists())
					return false;
				fileName = m;
				return true;
			} catch(Exception e) {
				return false;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		/*
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(new DownloadServer<Object>());
		executorService.shutdown();
		*/
		new DownloadServer().call();
	}
}
