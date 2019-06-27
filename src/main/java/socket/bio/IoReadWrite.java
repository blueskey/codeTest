package socket.bio;

import java.io.IOException;
import java.net.Socket;

public class IoReadWrite implements Runnable {

	private Socket socket;

	public IoReadWrite(Socket socket) {
		this.socket = socket;
	}

	@Override public void run() {

		try {
			System.out.println("服务器接收到数据："+socket.getInputStream().read(new byte[1024]));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			socket.getOutputStream().write("hello".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
