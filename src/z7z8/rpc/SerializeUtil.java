package z7z8.rpc;


import java.io.*;

/**
 * 序列化
 * author cash
 * create 2019-03-03-18:50
 **/
public class SerializeUtil {

    public static byte[] serialize(Object obj){

        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] bytes=bos.toByteArray();
            return bytes;
        } catch (IOException e) {
            System.out.println("序列化对象出错！");
            e.printStackTrace();
            return null;
        }

    }

    public static Object unSerialize(byte[] bytes){
        try {
            ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
            ObjectInputStream ois=new ObjectInputStream(bis);
            return  ois.readObject();
        } catch (Exception e) {
            System.out.println("反序列出错！");
            e.printStackTrace();
            return null;
        }
    }
}
