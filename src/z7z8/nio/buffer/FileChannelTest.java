package z7z8.nio.buffer;

import java.io.IOException;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * 
 * @author cash
 * @date 2017年9月18日 下午4:41:37
 * @decription
 *  Channel:
    FileChannel 	 文件Channel
	DatagramChannel	能通过UDP读写网络中的数据
	SocketChannel   能通过TCP读写网络中的数据
	ServerSocketChannel 	可以监听新进来的TCP连接,像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel
	
  当向buffer写入数据时,buffer会记录下写了多少数据。
  一旦要读取数据,需要通过flip()方法将Buffer从写模式切换到读模式。
 在读模式下,可以读取之前写入到buffer的所有数据。
 一旦读完了所有的数据,就需要清空缓冲区,让它可以再次被写入。
 有两种方式能清空缓冲区：调用clear()或compact()方法。
 clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。
 任何未读的数据都被移到缓冲区的起始处,新写入的数据将放到缓冲区未读数据的后面。
 
 */

public class FileChannelTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile aFile = new RandomAccessFile("./nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();//FileChannel

		ByteBuffer buf = ByteBuffer.allocate(48);//create buffer with capacity of 48 bytes

		int bytesRead = inChannel.read(buf);
		
		while (bytesRead != -1) {
			buf.flip();//读取数据,需要通过flip()方法将Buffer从写模式切换到读模式  flip将position置为0,limit为原来的position值
			//buf.rewind();

			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());//read 1 byte at a time
			}

			buf.clear();//make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		aFile.close();

	}

}
