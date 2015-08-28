package nio;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class DownClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws Exception {
		
		SocketFactory socketFactory = SocketFactory.getDefault();
		
		Socket socket = socketFactory.createSocket("localhost", 1234);

	}

}
