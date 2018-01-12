package z7z8.z7z8Test;

/***
 * 
 * @author cash
 * @date 2017年9月8日 下午4:08:36
 * @decription bitmap排序
 */
public class BitmapTest {
	
	int numSize = 1000;

	int arraySize = (int) Math.ceil((double) numSize / 32);

	private int array[] = new int[arraySize];

	public static void main(String[] args) {

		BitmapTest test = new BitmapTest();
		test.initBitMap();
		int sortArray[] = new int[] { 324,34,56,687,897,987,1, 4, 32, 2, 6, 9,100,199,345,565,72, 45,6,68,98,100};
		for (int i = 0; i < sortArray.length; i++) {
			test.set(sortArray[i]);
		}
		for (int i = 0; i < test.numSize; i++) {
			if (test.get(i) != 0) {
				System.out.print((i) + " ");
			}
		}

	}

	public void initBitMap() {
		for (int i = 0; i < arraySize; i++) {
			array[i] = 0;
		}
	}

	public void set(int pos) {
		array[pos >> 5] = array[pos >> 5] | (1 << (pos % 32)); // 给相应位置1

	}

	public int get(int pos) {
		return array[pos >> 5] & (1 << (pos % 32));
	}
}
