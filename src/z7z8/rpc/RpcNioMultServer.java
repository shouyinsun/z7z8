package z7z8.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务端
 * author cash
 * create 2019-03-03-19:07
 **/
public class RpcNioMultServer {

    private Selector selector;

    public static void start() throws IOException{
        RpcNioMultServer server=new RpcNioMultServer();
        server.initServer(8080);
        server.listen();
    }

    public void initServer(int port) throws IOException{
        //获得一个ServerSocket通道
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //获得通道管理器
        this.selector=Selector.open();
        //通道管理器和该通道绑定,并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后,
        //当该事件到达时,selector.select()会返回,如果该事件没到达selector.select()会一直阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen(){
        System.out.println("服务端启动完成！");
        while (true){
            try {
                //当注册的事件到达时,方法返回；否则,该方法会一直阻塞
                selector.select();
                Iterator ite=selector.selectedKeys().iterator();
                while(ite.hasNext()){
                    SelectionKey key= (SelectionKey) ite.next();
                    //删除已选的key,防止重复处理
                    ite.remove();
                    if(key.isAcceptable()){//连接事件
                        ServerSocketChannel server= (ServerSocketChannel) key.channel();
                        //客户端连接的通道
                        SocketChannel channel=server.accept();
                        //非阻塞
                        channel.configureBlocking(false);
                        //客户端连接成功之后,为了可以接收到客户端的信息,注册读事件
                        channel.register(this.selector,SelectionKey.OP_READ);
                    }else if(key.isReadable()){//可读事件
                        SocketChannel channel= (SocketChannel) key.channel();
                        byte[] bytes=readMsgFromClient(channel);
                        if(null!=bytes && bytes.length>0){
                            //提交服务端处理任务
                            RpcNioMultServerTask task=new RpcNioMultServerTask(bytes,channel);
                            ThreadPoolUtil.addTask(task);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public byte[] readMsgFromClient(SocketChannel channel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        try {
            // 首先读取消息头（自己设计的协议头,此处是消息体的长度）
            int headCount = channel.read(byteBuffer);
            if (headCount < 0) {
                return null;
            }
            byteBuffer.flip();
            int length = byteBuffer.getInt();
            // 读取消息体
            byteBuffer = ByteBuffer.allocate(length);
            int bodyCount = channel.read(byteBuffer);
            if (bodyCount < 0) {
                return null;
            }
            return byteBuffer.array();
        } catch (IOException e) {
            System.out.println("读取数据异常");
            e.printStackTrace();
            return null;
        }
    }
}
