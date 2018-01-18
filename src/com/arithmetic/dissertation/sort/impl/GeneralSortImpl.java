package com.arithmetic.dissertation.sort.impl;

import com.arithmetic.dissertation.sort.GeneralSort;
import com.dataStructure.heap.impl.MaxHeap;

public class GeneralSortImpl implements GeneralSort{

	/*
	 * 选择排序,性能低，不稳定
	 */
	@Override
	public <T extends Comparable<T>> void selectSort(T[] arr) {
		if(arr == null || arr.length <= 1) return;
		for(int i=0; i < arr.length-1; i++)
		{
			for(int j=i+1; j <= arr.length-1; j++)
			{
				if(arr[i].compareTo(arr[j]) > 0)
				{
					change(arr,i,j);
				}
			}
		}
	}
	
	/*
	 * 冒泡排序，性能低，稳定
	 */
	@Override
	public <T extends Comparable<T>> void bubbleSort(T[] arr) {
		if(arr == null || arr.length <= 1) return;
		for(int i=0; i < arr.length-1; i++)
		{
			int flag =1;
			for(int j=0; j < arr.length -1-i; j++)
			{
				if(arr[j].compareTo(arr[j+1]) > 0)
				{
					change(arr,j,j+1);
					flag = 0;
				}
			}
			if(flag == 1) break;
		}		
	}
	/*
	 * 插入排序,对于逆顺对较少，数列近似有序的前提下，插入排序性能非常好。
	 * 插入排序 是稳定的排序，且不需要额外的空间
	 */
	@Override
	public <T extends Comparable<T>> void insertSort(T[] arr) {
		if(arr == null || arr.length <= 1) return;
		for(int i= 1; i <= arr.length-1; i++)//第一个元素已是有序，所以从第二个元素开始
		{
			T temp = arr[i];
			int flag = i-1;
			while(flag>=0 && temp.compareTo(arr[flag]) < 0)
			{
				arr[flag+1] = arr[flag];
				flag = flag-1;
			}
			arr[flag+1] = temp;			
		}
	}

	/*
	 * 归并排序，至顶向下。性能非常好，但需要 额外空间，且是不稳定排序。
	 */
	@Override
	public <T extends Comparable<T>> void mergeSortByRecursion(T[] arr, int start, int end, T[] room) {
		if(arr == null || arr.length <= 1 ||  arr.length != room.length)  return;
		if(start == end) return;
		
		int mid = (end + start)/2;
		int left_start = start;//将数据按中点分成两块
		int left_end = mid;
		int right_start = mid +1;
		int right_end = end;
		int index = start;
		//递归求左右子问题的解
		if(left_end > left_start)
		{
			mergeSortByRecursion(arr,left_start,left_end,arr);
		}
		if(right_end > right_start)
		{
			mergeSortByRecursion(arr,right_start,right_end,arr);
		}
		//如何不可以分割，则合并2个有序的子集
		while(left_start <= left_end && right_start <= right_end)
		{
			if(arr[left_start].compareTo(arr[right_start]) <= 0)
			{
				room[index] = arr[left_start];
				left_start++;
				index++;
			}else{
				room[index] = arr[right_start];
				right_start++;
				index++;
			}
		}
		//处理合并逻辑后剩余的项
		while(left_end >= left_start)
		{
			room[index] = arr[left_start];
			left_start++;
			index++;
		}
		//处理合并逻辑后剩余的项
		while(right_end >= right_start)
		{
			room[index] = arr[right_start];
			right_start++;
			index++;
		}
		//合并结束后将数据写回
		for(int i=start; i<=end; i++)
		{
			arr[i] = room[i];
		}
	}

	/*
	 * 归并排序，至底向上,好复杂
	 */
	@Override
	public <T extends Comparable<T>> void mergeSortByRecurrence(T[] arr, int start, int end, T[] room) {
		for(int step=0; Math.pow(2, step)<=arr.length*2; step++)
		{
			int size = (int) Math.pow(2, step);			
			for(int num=start; num<=end; num+=size)
			{				
				int end1 = num+size - 1;
				end1 = end1 > end ? end:end1;
				doSortByNoRecursion(arr,num,end1,room,size);
			}
		}
	}
	
	private <T extends Comparable<T>>  void doSortByNoRecursion(T[] arr, int start, int end, T[] room, int size)
	{
		int step = (int)Math.ceil(size/2.0);
		if(end <= start) return;
		if((end-start) < step) return;
		int st1 = start;
		int en1 = start + step - 1;
		int st2 = (en1+1)>=end ? end:en1+1;
		int en2 = end;
		int index = start;
		
		doSort(arr,room,st1,en1,st2,en2);
	}
	
	public <T extends Comparable<T>>   void doSort(T[] arr,T[] room,int start1,int end1,int start2,int end2)
	{
		int index = start1;
		int start = start1;
		int end = end2;
		while(start1 <= end1 && start2 <= end2)
		{
			if(arr[start1].compareTo(arr[start2]) <= 0)
			{
				room[index] = arr[start1];
				start1++;
				index++;
			}else{
				room[index] = arr[start2];
				start2++;
				index++;				
			}
		}
		//处理合并逻辑后剩余的项
		while(end1 >= start1)
		{
			room[index] = arr[start1];
			start1++;
			index++;
		}
		//处理合并逻辑后剩余的项
		while(end2 >= start2)
		{
			room[index] = arr[start2];
			start2++;
			index++;
		}
		//合并结束后将数据写回
		for(int i=start; i<=end; i++)
		{
			arr[i] = room[i];
		}
	}

	/*
	 * 快排1路 乱序数据排序性能好 
	 */
	@Override
	public <T extends Comparable<T>> void qulikSort1Ways(T[] arr, int start, int end) {
		if(arr == null || arr.length<= 1 || start < 0 || end > arr.length-1 || start == end) return;
		
		int mid = partition(arr,start,end);//计算哨兵位置
		if(mid != -1)//如果能得到有效的哨兵位置,则递归排序左右子数组
		{
			int mid_left = mid - 1<start ? start: mid-1;
			int mid_right = mid + 1 > end ? end:mid+1;
			
			if(mid - start == 1 && end - mid == 1) return; //即出现 3,4,5，哨兵是4时，可直接返回
			if(mid_left > start){
				qulikSort1Ways(arr,start,mid_left);
			}
			if(end > mid_right){
				qulikSort1Ways(arr,mid_right,end);
			}
		}
	}
	
	/*
	 * 将数据重排后，使之满足哨兵左边的数据比哨兵小，哨兵右边的数据比哨兵大，并返回哨兵位置。
	 * 该分割算法使用一路快排算法
	 */
	private <T extends Comparable<T>>  int partition(T[] arr, int start, int end) 
	{
		if(end == start)
		{
			return -1;
		}
		int index = (end + start)/2;
		T sentinel = arr[index];//以中间元素为哨兵
		arr[index] = arr[end];
		int left = start -1;
		int right = start;
		for( ; right <= end-1; right++)
		{				//从左至右循环，若比哨兵大则不做操作，若比哨兵小则左右索引的值			
			if(arr[right].compareTo(sentinel) <= 0)
			{
				left = left +1;
				change(arr,left,right);
			}
		}
		//change(arr,left+1,right);
		arr[end] = arr[left+1];
		arr[left+1] = sentinel;
				
		return left+1;
	}

	private <T extends Comparable<T>>  void change(T[] arr, int left, int right) {
		if(left == right) return;
		T temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
	
	/*
	 * 快排2路
	 */
	@Override
	public <T extends Comparable<T>> void qulikSort2Ways(T[] arr, int start, int end) {
		if(arr == null || arr.length<= 1) return;
		if(start <0 || end > arr.length-1 || end == start) return;
		
		T sentinel = arr[(end+start)/2];		//使用中间元素做哨兵，对近似有序数据和大量重复数据的排序性能大大提高
		arr[(end+start)/2] = arr[end];
		int left = start;
		int right = end;
		boolean repeat = true;
		while(left < right)
		{
			while(left < right && arr[left].compareTo(sentinel) <= 0)
			{	
				if(arr[left].compareTo(sentinel) < 0)
				{
					repeat = false;
				}
				left ++;
			}
			arr[right] = arr[left];
			while(left < right && arr[right].compareTo(sentinel) >= 0)
			{
				if(arr[right].compareTo(sentinel) > 0)
				{
					repeat = false;
				}
				right --;
			}
			arr[left] = arr[right];
		}
		if(repeat) return;
		arr[left] = sentinel;
		//循环结束后，数列被分成两段，哨兵左边比哨兵小，哨兵右边比哨兵大
		if(left - start == 1 && end - right == 1) return;
		if(left-1 > start)
		{
			qulikSort2Ways(arr,start,left-1);
		}
		if(right+1 < end)
		{
			qulikSort2Ways(arr,right+1,end);
		}		
	}

	/*
	 * 快排3路 大量重复数据排序性能好
	 */
	@Override
	public <T extends Comparable<T>> void qulikSort3Ways(T[] arr, int start, int end ) {
		if(end == start) return;
		
		int begin = start;
		T sentinel = arr[(end+start)/2];
		int lt = start -1;
		int gt = end+1;
		while(begin < gt)
		{
			if(arr[begin].compareTo(sentinel) > 0)
			{
				change(arr,begin,gt-1);
				gt --;
			}else if(arr[begin].compareTo(sentinel) < 0)
			{
//				int temp = data[begin];
//				for(int i=begin-1; i>lt; i--)
//				{
//					data[i+1] = data[i];
//				}
//				data[lt +1] = temp;
				change(arr,begin,lt+1);
				lt ++;
				begin ++;
			}else{
				begin ++;
			}		
		}
		
		if(lt > start)
		{
			qulikSort3Ways(arr,start,lt);
		}
		if(gt < end)
		{
			qulikSort3Ways(arr,gt,end);
		}
	}

	/*
	 * 堆排序，性能好,但不稳定
	 */
	@Override
	public <T extends Comparable<T>> void heapSort(T[] arr) {
		if(arr == null || arr.length <= 1) return;
		MaxHeap.sort(arr);
	}
	



}
