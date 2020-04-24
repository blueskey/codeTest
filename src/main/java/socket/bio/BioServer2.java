package socket.bio;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程接收客户端连接
 */
public class BioServer2 {

	public static void main(String[] args) throws Exception{

		ExecutorService executorService=Executors.newCachedThreadPool();
		System.out.println("升级版服务启动了----------------准备接受请求");
		ServerSocket server = new ServerSocket(8081);
		Socket socket=null;
		while (true) {
			socket=server.accept();
			System.out.println("请求来了。。。。");
			executorService.submit(new IoReadWrite(socket));
		}
	}
}
