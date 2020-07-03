package z7z8.byteCodeEnhance.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 为 了 利 用 ASM 实 现 AOP, 需 要 定 义 两 个 类： 一 个 是 CustomizedClassVisitor类,
 * 用于对字节码的 visit 以及修改；
 * 另一个是 Generator 类,在这个类中定义
 * ClassReader 和 ClassWriter,其中的逻辑是,classReader 读取字节码,然后交
 * 给 CustomizedClassVisitor 类处理,处理完成后由 ClassWriter 写字节码并将旧的字节码替换掉
 * @description: 自定义classVisitor
 * @author: cash
 * @create: 2020/7/3 15:10
 **/
public class CustomizedClassVisitor extends ClassVisitor implements Opcodes {

    public CustomizedClassVisitor(ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {

        cv.visit(version, access, name, signature, superName, interfaces);
    }
    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        //Base 类中有两个方法:无参构造以及 process 方法,这里不增强构造方法
        if (!name.equals("<init>") && mv != null) {
            mv = new CustomizedMethodVisitor(mv);
        }
        return mv;
    }
    class CustomizedMethodVisitor extends MethodVisitor implements Opcodes {//自定义methodVisitor
        public CustomizedMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }
        @Override
        public void visitCode() {// Starts the visit of the method's code 开始访问方法code
            //aop 前置逻辑
            super.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
                    "Ljava/io/PrintStream;");
            mv.visitLdcInsn("start");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                    "println", "(Ljava/lang/String;)V", false);
        }
        @Override
        public void visitInsn(int opcode) {//Visits a zero operand instruction  访问无参指令
            //判断是否是"return"指令  aop后置逻辑
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                    || opcode == Opcodes.ATHROW) {
                // 方法在返回之前,打印 "end"
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
                        "Ljava/io/PrintStream;");
                mv.visitLdcInsn("end");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(Ljava/lang/String;)V", false);
            }
            mv.visitInsn(opcode);
        }
    }
}
