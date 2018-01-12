package z7z8.z7z8Test;

import java.util.Arrays;

/**
 * 
 * @author cash
 * @date 2017年9月8日 下午4:47:30
 * @decription
 * int []
 * num>>5判断所在数组  0-31在第一个数组里  某 bit为1,表示这个数数存在
 * 00000000000000000000000000000001  --{1}
 * 00000000000000000000000000010000  --{4}
 * 00000000000000000000000000010100  --{4,2}
 * 0000000000000000000000000001010000000000000000000000000000010100  --{36,34,4,2}
 * 
 */

public class Bitmap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num=4;
		final int SHIFT=5;
		final int MASK=31;
		
		int space = num / 32 + 1;
		int [] arr=new int[space];
		for (int i=0;i<arr.length;i++) {
			arr[i]&=0;
		}
		
		System.out.println(Arrays.toString(arr));
		
		int index_loc = num >> SHIFT; // 等价于n / 32
		int bit_loc = num & MASK;	// 等价于n % 32
		
		//arr[index_loc] |= 1 <<bit_loc;	
		arr[index_loc] =bit_loc;		
		
		String str="";
		for(int i=0;i<arr.length;i++){
			 str=toFullBinaryString(arr[i])+str;
		}
		
		System.out.println(str);
		
	}
	
	/* public static String toFullBinaryString(int num) {
	        char[] chs = new char[Integer.SIZE];
	        for(int i = 0; i < Integer.SIZE; i++) {
	            chs[Integer.SIZE - 1 - i] = (char)(((num >> i) & 1) + '0');
	        }
	        return new String(chs);        
	 }*/
	 
	 public static String toFullBinaryString(int num) {
	        char[] chs = new char[Integer.SIZE];
	        for(int i = 0; i < Integer.SIZE; i++) {
	        	if(num>0 && (Integer.SIZE-i==num+1)){
	        		chs[i]='1';
	        	}else{
	        		chs[i]='0';
	        	}
	        }
	        return new String(chs);        
	 }
	

}
