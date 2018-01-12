package z7z8.innerClass.privateInner;

class Out {   
    private int age = 12;   

    private class In {   
        public void print() {   
            System.out.println(age);   
        }   
    }   
    public void outPrint() {   
        new In().print();   
    }   
}   

public class Demo {   
    public static void main(String[] args) {   
        //此方法无效   
        /*   
        Out.In in = new Out().new In();   
        in.print();   
        */   
        Out out = new Out();   
        out.outPrint();   
    }   
}
