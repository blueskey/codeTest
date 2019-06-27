package socket.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorServer {
	private Selector selector;

	public void init() throws Exception{
		this.selector = Selector.open();

	}

	public void star() throws Exception{
		while (true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = (SelectionKey) iterator.next();
				iterator.remove();
				if(selectionKey.isAcceptable()) {
					accept(selectionKey);
				}
				if(selectionKey.isReadable()) {
					read(selectionKey);
				}
			}
		}

	}

	public void accept(SelectionKey selectionKey) throws Exception{
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
	}

	public void read(SelectionKey selectionKey) throws Exception{
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		socketChannel.read(byteBuffer);
		String s = new String(byteBuffer.array()).trim();
		System.out.println(s);
	}

	public void close() throws Exception{
		while (true) {
			selector.select();
		}
	}
}
