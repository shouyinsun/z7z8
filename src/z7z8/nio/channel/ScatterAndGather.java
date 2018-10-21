package z7z8.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * @author cash
 * @date 2017年9月18日 下午5:09:51
 * @decription
 * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此,Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中
        聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel,因此,Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel
 */
public class ScatterAndGather {
	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("./nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();//FileChannel
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body   = ByteBuffer.allocate(512);

		ByteBuffer[] bufferArray = { header, body };
		
		inChannel.read(bufferArray);
		
		for(ByteBuffer bf:bufferArray){
			System.out.println("------------------------");
			bf.flip();//读取数据,需要通过flip()方法将Buffer从写模式切换到读模式  flip将position置为0,limit为原来的position值
			while (bf.hasRemaining()) {
				System.out.print((char) bf.get());//read 1 byte at a time
			}
		}
		aFile.close();
		
	}

}
