package z7z8.treeSet;

import java.util.Iterator;
import java.util.TreeSet;

import com.alibaba.fastjson.JSONObject;
/***
 * TreeSet 
 * 元素实现 Comparator 接口
 * @author cash
 * @date 2018年6月1日 下午3:52:05
 * @decription
 */
public class IntegerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<Integer> treeSet = new TreeSet();
		treeSet.add(10);
		treeSet.add(10);
		treeSet.add(6);
		treeSet.add(2);
		treeSet.add(19);
		treeSet.add(18);
		treeSet.add(9);
		treeSet.add(1);

		System.out.println(JSONObject.toJSONString(treeSet));
		TreeSet treesubset = (TreeSet) treeSet.subSet(3, 7);
		
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
