package z7z8.byteCodeEnhance.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassReader 和 ClassWriter，
 * classReader 读取字节码，然后交给 给定的ClassVisitor 类处理，
 * 处理完成后由 ClassWriter 写字节码并将旧的字节码替换掉
 *
 * @description: generate 新的字节码
 * @author: cash
 * @create: 2020/7/3 15:36
 **/
public class Generator {
    public static void main(String[] args) throws IOException {
        ClassReader classReader =new ClassReader("z7z8/byteCodeEnhance/asm/Base");
        ClassWriter classWriter =new ClassWriter(ClassWriter.COMPUTE_MAXS);

        // 处理
        ClassVisitor classVisitor = new CustomizedClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        // 输出
//        //绝对地址
//        System.out.println(getClass().getResource("/z7z8/byteCodeEnhance/asm/Base.class").getPath()+'\n');
//        //相对地址 .class文件同目录下进行查询获取
//        System.out.println(getClass().getResource("Base.class").getPath()+'\n');
        File f = new File("E:\\cash\\github\\z7z8\\bin\\z7z8\\byteCodeEnhance\\asm\\Base.class");
        FileOutputStream out = new FileOutputStream(f);
        out.write(data);
        out.close();
        System.out.println("now generator success!!!!!");
    }

}
