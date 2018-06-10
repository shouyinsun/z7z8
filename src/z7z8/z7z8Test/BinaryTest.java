package z7z8.z7z8Test;

import java.util.Arrays;
/**
 * number 和 string
 * 转成二进制串
 * 
 * **/
public class BinaryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Integer.MAX_VALUE);

		// int 转 二进制 串
		Integer xx = 31;
		System.out.println(Integer.toBinaryString(xx));

		// 字符串转二进制串
		String str = "徐超";
		char[] strChar = str.toCharArray();
		System.out.println(Arrays.toString(strChar));
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]) + " ";
		}
		System.out.println(result);

	}

}
