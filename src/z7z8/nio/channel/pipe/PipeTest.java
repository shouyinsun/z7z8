package z7z8.nio.channel.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 
 * @author cash
 * @date 2017年9月19日 下午2:09:20
 * @decription
 *  管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取
 */
public class PipeTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Pipe pipe = Pipe.open();
		Pipe.SinkChannel sinkChannel = pipe.sink();
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());

		buf.flip();

		while(buf.hasRemaining()) {
		    sinkChannel.write(buf);
		}
		
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		int bytesRead = sourceChannel.read(buf2);
		while (bytesRead != -1) {
			buf2.flip();//读取数据,需要通过flip()方法将Buffer从写模式切换到读模式  flip将position置为0,limit为原来的position值
			while (buf2.hasRemaining()) {
				System.out.print((char) buf2.get());//read 1 byte at a time
			}
			buf2.clear();//make buffer ready for writing
			bytesRead = sourceChannel.read(buf2);
		}
	}

}
