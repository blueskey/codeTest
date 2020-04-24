package socket.bio;

import java.io.InputStream;
import java.net.Socket;

public class BioClient {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 8081);
		byte[] in = new byte[1024];
		byte[] out = new byte[1024];

		while (true) {

			socket.getInputStream().read(in);
			System.out.println("server:" + new String(in));

			InputStream inputStream = System.in;
			int n=inputStream.read(out);
			socket.getOutputStream().write(out);

		}
	}
}
