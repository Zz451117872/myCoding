package com.arithmetic.dissertation.sort.impl;

import com.arithmetic.dissertation.sort.GeneralSort;
import com.dataStructure.heap.impl.MaxHeap;

public class GeneralSortImpl implements GeneralSort{

	/*
	 * 选择排序,性能低，不稳定
	 */
	@Override
	public <T extends Comparable<T>> void selectSort(T[] arr, int start, int end) {
		for(int i=start; i < end; i++)
		{
			for(int j=i+1; j <= end; j++)
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
	public <T extends Comparable<T>> void bubbleSort(T[] arr, int start, int end) {
		for(int i=start; i < end; i++)
		{
			int flag =1;
			for(int j=start; j < end-i; j++)
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
	public <T extends Comparable<T>> void insertSort(T[] arr, int start, int end) {
		if(arr == null || arr.length == 0 || start < 0 || end > arr.length-1) return;
		for(int i= start+1; i <= end; i++)
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
		
		int mid = (end + start)/2;
		int st1 = start;
		int en1= mid;
		int st2 = mid +1;
		int en2 = end;
		int index = start;
		//如果还可以分割，则继续分割
		if(en1 > st1)
		{
			mergeSortByRecursion(arr,st1,en1,arr);
		}
		if(en2 > st2)
		{
			mergeSortByRecursion(arr,st2,en2,arr);
		}
		//如何不可以分割，则合并2个有序的子集
		while(st1 <= en1 && st2 <= en2)
		{
			if(arr[st1].compareTo(arr[st2]) <= 0)
			{
				room[index] = arr[st1];
				st1++;
				index++;
			}else{
				room[index] = arr[st2];
				st2++;
				index++;
			}
		}
		//处理合并逻辑后剩余的项
		while(en1 >= st1)
		{
			room[index] = arr[st1];
			st1++;
			index++;
		}
		//处理合并逻辑后剩余的项
		while(en2 >= st2)
		{
			room[index] = arr[st2];
			st2++;
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
	 * 快排序，1路快排序 对于 大量重复数据 性能低下
	 */
	@Override
	public <T extends Comparable<T>> void qulikSort1Ways(T[] arr, int start, int end) {
		if(arr == null || arr.length<=0) return;
		int mid = partition(arr,start,end);
		if(mid != -1)
		{
			int mid_left = mid - 1<start ? start: mid-1;
			int mid_right = mid + 1 > end ? end:mid+1;
			qulikSort1Ways(arr,start,mid_left);
			qulikSort1Ways(arr,mid_right,end);
		}
	}
	
	/*
	 * 将数据重排后，使之满足哨兵左边的数据比哨兵小，哨兵右边的数据比哨兵大，并返回哨兵位置。
	 */
	private <T extends Comparable<T>>  int partition(T[] arr, int start, int end) 
	{
		if(end == start)
		{
			return -1;
		}
		T sentinel = arr[end];		//哨兵
		int left = start -1;
		int right = start;
		for( ; right <= end-1; right++)
		{
			if(arr[right].compareTo(sentinel) < 0)
			{
				left = left +1;
				change(arr,left,right);
			}
		}
		change(arr,left+1,right);
		return left+1;
	}

	private <T extends Comparable<T>>  void change(T[] arr, int left, int right) {
		T temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
	
	/*
	 * 快排序,双路快排序能解决 1路快排序中 对于大量重复数据 性能低下的问题
	 * 快排序是不稳定的，不需要额外的空间。
	 */
	@Override
	public <T extends Comparable<T>> void qulikSort2Ways(T[] arr, int start, int end) {
		if(arr == null || arr.length<=0) return;
		if(end == start) return;
		
		T sentinel = arr[end];		//哨兵
		int left = start;
		int right = end;
		while(left < right)
		{
			while(left < right && arr[left].compareTo(sentinel) <= 0)
			{	
				left ++;
			}
			arr[right] = arr[left];
			while(left < right && arr[right].compareTo(sentinel) >= 0)
			{
				right --;
			}
			arr[left] = arr[right];
		}
		arr[left] = sentinel;
		//循环结束后，数列被分成两段，哨兵左边比哨兵小，哨兵右边比哨兵大
		if(left > start)
		{
			qulikSort2Ways(arr,start,left-1);
		}
		if(right < end)
		{
			qulikSort2Ways(arr,right+1,end);
		}		
	}

	/*
	 * 快速排序：3路快排序，解决2路1路对高重复数据 排序 栈溢出问题。
	 * 如果需要保证其稳定性，则需要损失大量性能。在不保证稳定性的前提下，3路快排序对高重复数据排序性能优秀
	 */
	@Override
	public <T extends Comparable<T>> void qulikSort3Ways(T[] arr, int start, int end , T[] room) {
		if(end == start) return;
		
		int begin = start;
		T sentinel = arr[end];
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
			qulikSort3Ways(arr,start,lt,room);
		}
		if(gt < end)
		{
			qulikSort3Ways(arr,gt,end,room);
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
