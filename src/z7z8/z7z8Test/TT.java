package z7z8.z7z8Test;

import java.util.Arrays;

public class TT<K, V> {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={12,343,45,546,763,53,5};
		int b[] =new int[10];
		System.arraycopy(a, 1, b, 2, 5);
		System.out.println(Arrays.toString(b));
	}

}
