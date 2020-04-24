package socket.bio;

import java.io.IOException;
import java.net.Socket;

public class IoReadWrite implements Runnable {

	private Socket socket;

	public IoReadWrite(Socket socket) {
		this.socket = socket;
	}

	@Override public void run() {
		byte[] data = new byte[1024];
		try {
			socket.getOutputStream().write(("您好，欢迎连接"+"\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				socket.getInputStream().read(data);
				System.out.println("服务器接收到数据："+new String(data));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
