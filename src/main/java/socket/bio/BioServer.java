package socket.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

	public static void main(String[] args) throws Exception{

		ExecutorService executorService=Executors.newCachedThreadPool();

		ServerSocket server = new ServerSocket(8100);
		Socket socket=null;
		while (true) {
			socket=server.accept();
			System.out.println("有客服端连接了…………");
			executorService.submit(new IoReadWrite(socket));
		}
	}
}
