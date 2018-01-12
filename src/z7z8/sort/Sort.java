package z7z8.sort;

import java.util.Arrays;

/*

 插入排序：
 1.直接插入排序(左边有序,右边无序,右边插入左边)
 2.希尔排序 
 交换排序：
 1.冒泡排序
 2.快速排序
 选择排序：
 1.直接选择排序(直接完整比较,遍历)
 2.堆排序
 归并排序
 */
public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sort sort = new Sort();
		int a[] = { 234, 23, 3, 32, 456, 45, 576, 876, 233, 2, 78, 445, 340, 90 };
		sort.quickSort(a, 0, 13);
		System.out.println(Arrays.toString(a));

		int b[] = { 234, 23, 3, 32, 456, 45, 576, 876, 233, 2, 78, 445, 340, 90 };
		int c[] = new int[14];
		sort.mergeSort(b, c, 0, 13);
		System.out.println(Arrays.toString(c));

	}

	// 插入排序
	public void StraightInsertSort(int[] src) {
		int i, j, k, tmp;
		int len = src.length;
		for (i = 1; i < len; i++) {
			for (j = 0; j < i; j++) {
				if (src[i] < src[j]) {
					tmp = src[i];
					for (k = i - 1; k >= j; k--) {
						src[k + 1] = src[k];
					}
					src[j] = tmp;
				}
			}
		}

		System.out.println(Arrays.toString(src));

	}

	//直接选择
	public void straightSelectSort(int SA[]) {
		int len = SA.length;
		int i, j, m, temp;
		for (i = 0; i < len - 1; i++) {
			m = i;
			for (j = i + 1; j < len; j++) {
				if (SA[m] > SA[j]) {
					m = j;//m记录需要交换的位置 
				}
			}
			if (m > i) {
				temp = SA[i];
				SA[i] = SA[m];
				SA[m] = temp;
			}
		}
	}

	// 冒泡
	public void bubbleSort(int[] src) {
		int i, j, k, temp;
		int len = src.length;
		for (i = 1; i < len; i++) {
			for (j = 0; j < len - i; j++) {
				if (src[j] < src[j + 1]) {
					temp = src[j + 1];
					src[j + 1] = src[j];
					src[j] = temp;
				}
			}
		}

		System.out.println(Arrays.toString(src));
	}

	// 快速
	public void quickSort(int[] src, int l, int r) {
		//改进：可以每一次递归到足够小时,直接插入排序    r-l<5 
		
		//改进二：左 中 右三个元素中取出中值 然后放到最右侧 更科学的选择主元 提高了快速排序的效率    median3
		if (l >= r) {
			return;
		}

		int pivot = src[r];
		int i = l;
		int j = r;

		while (i < j) {
			for (; src[i] <= pivot && i < j;) {// 从左至右
				i++;
			}
			if (i < j) {
				src[j--] = src[i];
			}

			for (; src[j] > pivot && i < j;) {// 从右至左
				j--;
			}
			if (i < j) {
				src[i++] = src[j];
			}

		}
		src[i] = pivot;
		quickSort(src, l, i - 1);
		quickSort(src, i + 1, r);

	}

	// 归并
	public void mergeSort(int sourceArr[], int targetArr[], int startIndex,
			int endIndex) {
		int i, j, k, midIndex;
		int tempArr[] = new int[100];// 大小可设置
		if (startIndex == endIndex) {
			targetArr[startIndex] = sourceArr[startIndex];
		} else {
			midIndex = (startIndex + endIndex) / 2;
			mergeSort(sourceArr, tempArr, startIndex, midIndex);
			mergeSort(sourceArr, tempArr, midIndex + 1, endIndex);
			for (i = midIndex + 1, j = startIndex; startIndex <= midIndex
					&& i <= endIndex; j++) {
				if (tempArr[startIndex] < tempArr[i]) {
					targetArr[j] = tempArr[startIndex++];
				} else {
					targetArr[j] = tempArr[i++];
				}
			}
			if (startIndex <= midIndex) {
				for (k = 0; k <= midIndex - startIndex; k++) {
					targetArr[j + k] = tempArr[startIndex + k];
				}
			}
			if (i <= endIndex) {
				for (k = 0; k <= endIndex - i; k++) {
					targetArr[j + k] = tempArr[i + k];
				}
			}
		}
	}

}
