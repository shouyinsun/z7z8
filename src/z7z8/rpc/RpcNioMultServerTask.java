package z7z8.rpc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 服务端线程池任务
 * author cash
 * create 2019-03-03-19:29
 **/
public class RpcNioMultServerTask implements  Runnable {
    private byte[] bytes;

    private SocketChannel channel;

    public RpcNioMultServerTask(byte[] bytes, SocketChannel channel) {
        this.bytes = bytes;
        this.channel = channel;
    }

    @Override
    public void run() {
        if (bytes != null && bytes.length > 0 && channel != null) {
            // 反序列化
            RequestMultObject requestMultObject = (RequestMultObject) SerializeUtil.unSerialize(bytes);
            // 调用服务并序列化结果然后返回
            requestHandle(requestMultObject, channel);
        }
    }

    public void requestHandle(RequestMultObject requestObject, SocketChannel channel) {
        Long requestId = requestObject.getRequestId();
        Object obj = BeanContainer.getBean(requestObject.getCalzz());
        String methodName = requestObject.getMethodName();
        Class<?>[] parameterTypes = requestObject.getParamTypes();
        Object[] arguments = requestObject.getArgs();
        try {
            Method method = obj.getClass().getMethod(methodName, parameterTypes);
            String result = (String) method.invoke(obj, arguments);
            byte[] bytes = SerializeUtil.serialize(result);
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length + 12);
            // 为了便于客户端获得请求ID,直接将id写在头部
            // 然后写入消息题的长度,最后写入返回内容
            buffer.putLong(requestId);//requestId 8 byte
            buffer.putInt(bytes.length);//data length 4 byte
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }
}
