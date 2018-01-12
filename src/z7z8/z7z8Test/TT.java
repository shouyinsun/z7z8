package z7z8.z7z8Test;

import java.sql.Timestamp;

public class TT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timestamp t1=new Timestamp(1513051932000L);
		System.out.println(t1);
		Timestamp t2=(Timestamp) t1.clone();
		System.out.println(t2.getTime());
		System.out.println(t2);
		
	}

}
