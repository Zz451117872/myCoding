package com.arithmetic.dissertation.sort;

import com.arithmetic.dissertation.sort.other.Sort;

public class MergeSortTopDowm implements Sort{
	/*
	 * 归并排序，至顶向下。性能非常好，但需要 额外空间，且是不稳定排序。
	 */
	public  void sort(int[] data,int start ,int end,int[] arr)
	{
		int mid = (end + start)/2;
		int st1 = start;
		int en1= mid;
		int st2 = mid +1;
		int en2 = end;
		int index = start;
		//如果还可以分割，则继续分割
		if(en1 > st1)
		{
			sort(data,st1,en1,arr);
		}
		if(en2 > st2)
		{
			sort(data,st2,en2,arr);
		}
		//如何不可以分割，则合并2个有序的子集
		while(st1 <= en1 && st2 <= en2)
		{
			if(data[st1] <= data[st2])
			{
				arr[index] = data[st1];
				st1++;
				index++;
			}else{
				arr[index] = data[st2];
				st2++;
				index++;
			}
		}
		//处理合并逻辑后剩余的项
		while(en1 >= st1)
		{
			arr[index] = data[st1];
			st1++;
			index++;
		}
		//处理合并逻辑后剩余的项
		while(en2 >= st2)
		{
			arr[index] = data[st2];
			st2++;
			index++;
		}
		//合并结束后将数据写回
		for(int i=start; i<=end; i++)
		{
			data[i] = arr[i];
		}
	}
}
