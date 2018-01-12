package z7z8.z7z8Test;

import java.util.ArrayList;
import java.util.List;

public class MathTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*int i=1300;
		int j=1000;
		int k=(int) Math.ceil((double)i/j);
		System.out.println(k);
		
		
		String msgId="95d84f37-87e8-4dba-8d95-1fe8c7e49873";
		msgId=msgId.split("#")[0];
		System.out.println(msgId);*/
		List<Integer> candidates=new ArrayList<>();
		candidates.add(1);
/*		candidates.add(2);
		candidates.add(3);*/
		
		candidates.remove(0);
		
		for(Integer i:candidates){
			System.out.println(i);
		}

	}

}
