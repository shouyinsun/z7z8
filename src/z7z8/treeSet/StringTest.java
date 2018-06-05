package z7z8.treeSet;

import java.util.Iterator;
import java.util.TreeSet;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 将字符串按照长度排序
		TreeSet<String> ts = new TreeSet<>(new CompareByLen());
		ts.add("aaaaaaaa");
		ts.add("a");
		ts.add("wc");
		ts.add("nba");
		ts.add("cba");

		System.out.println(ts);

		TreeSet treesubset = (TreeSet) ts.subSet("b", "dddddddddd");

		// create iterator
		Iterator iterator;
		iterator = treesubset.iterator();

		// displaying the Tree set data
		System.out.println("Tree subset data: ");
		while (iterator.hasNext()) {
			System.out.println(iterator.next() + " ");
		}

	}

}
