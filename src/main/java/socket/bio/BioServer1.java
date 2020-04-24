package socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ju
 *
 * 单线程接收客户端连接
 */
public class BioServer1 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8081);
        System.out.println("服务启动了----------------准备接受请求");
        while (true) {
            //1.server.accept()会导致阻塞
            Socket socket= server.accept();
            System.out.println("请求来了。。。。");
            socket.getOutputStream().write(("您好，欢迎连接"+"\n").getBytes());


            byte[] b = new byte[1024];
            //2.read()会导致阻塞，如果单线程下，client1连完，但不发消息给server,client2就会因为client1此处阻塞，导致client2连不上server
            socket.getInputStream().read(b);
            //3.解决2上面提的问题，server端改用多线程处理客户端通信。每个client发不发消息不影响其他client连接。
            //4.线程有限的，不适应高并发场景。。
            System.out.println("收到消息：" + new String(b));
        }

    }
}
