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

        int d[] = { 234, 23, 3, 32, 456, 45, 576, 876, 233, 2, 78, 445, 340, 90 };
        sort.HeapSort(d);
        System.out.println(Arrays.toString(d));

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
		int i, j, temp;
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

	// 快速  时间复杂度 (nlogn)
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

	// 归并   时间复杂度 (nlogn)
	public void mergeSort(int sourceArr[], int targetArr[], int left, int right) {

		if(left<right){
			int mid = (left+right)/2;
			mergeSort(sourceArr,targetArr,left,mid);//左边归并排序 使得左子序列有序
			mergeSort(sourceArr,targetArr,mid+1,right);//右边归并排序 使得右子序列有序

			int i = left;//左序列指针
			int j = mid+1;//右序列指针
			int t = 0;
			while (i<=mid && j<=right){
				if(sourceArr[i]<=sourceArr[j]){
					targetArr[t++] = sourceArr[i++];
				}else {
					targetArr[t++] = sourceArr[j++];
				}
			}
			while(i<=mid){//将左边剩余元素填充进temp中
				targetArr[t++] = sourceArr[i++];
			}
			while(j<=right){//将右序列剩余元素填充进temp中
				targetArr[t++] = sourceArr[j++];
			}
			t = 0;
			//将temp中的元素全部拷贝到原数组中
			while(left <= right){
				sourceArr[left++] = targetArr[t++];
			}
		}
	}
    
    /**
     * 堆是具有以下性质的完全二叉树：
     * 每个结点的值都大于或等于其左右孩子结点的值,称为大顶堆；
     * 或者每个结点的值都小于或等于其左右孩子结点的值,称为小顶堆
     * **/
    //堆排序 时间复杂度 (nlogn)
    public void HeapSort(int sourceArr[]){
        //1.构建大顶堆
        for(int i=sourceArr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上,从右至左调整结构
            adjustHeap(sourceArr,i,sourceArr.length);
        }
        //2.交换首尾节点,尾节点为最大值
        for(int j=sourceArr.length-1;j>0;j--){
            swap(sourceArr,0,j);//尾节点最大
            adjustHeap(sourceArr,0,j);//除尾节点,从头节点(因为不满足) 重新堆调整
        }
    }

    /**
     * 调整成大顶堆
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int []arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始,也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点,k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点,将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}

