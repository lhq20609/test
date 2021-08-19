package lhq.test;
/*
在调用intern()方法时，如果字符串
在sun.misc.Version类中已经声明过java等字符串常量，调用intern()时返回的是不一样的地址
 */
public class Test {
    public static void main(String[] args) {
        String str1=new StringBuilder("lin").append("hai").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1==str1.intern());
        String str2=new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2==str2.intern());
    }
}
