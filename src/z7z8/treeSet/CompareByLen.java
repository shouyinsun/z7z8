package z7z8.treeSet;

import java.util.Comparator;


/**
 * Comparator 比较器,负责比较逻辑
 * Comparable 可比较对象,实现类中compareTo方法
 * */
public class CompareByLen implements  Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		// TODO Auto-generated method stub
		 int num = s1.length() - s2.length();        //长度为主要条件
         return num == 0 ? s1.compareTo(s2) : num;    //内容为次要条件
	}

	

}
