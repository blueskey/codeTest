package socket.bio;

import java.io.InputStream;
import java.net.Socket;

public class BioClient {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 8100);

		while (true) {
			InputStream inputStream = System.in;
			socket.getOutputStream().write(new byte[1024]);

		}
	}
}
