package z7z8.z7z8Test;

import java.util.UUID;

public class TT<K, V> {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
/*		Random random = new Random(50);
		Random random1 = new Random(50);

		System.out.println(random.nextInt(100));
		System.out.println(random.nextInt(100));
		System.out.println(random1.nextInt(100));
		System.out.println(random1.nextInt(100));

		System.out.println(random.nextInt(10));
		System.out.println(random1.nextInt(10));

		byte[] b=new byte[4];
		random.nextBytes(b);
		System.out.println("------------------");
		for(byte bb:b){
			System.out.println((char)bb);
		}*/

		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.nameUUIDFromBytes("cash".getBytes()).toString());
		System.out.println(UUID.nameUUIDFromBytes("cash".getBytes()).toString());

	}

}
