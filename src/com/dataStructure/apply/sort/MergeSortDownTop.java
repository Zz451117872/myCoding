package com.dataStructure.apply.sort;

import com.dataStructure.apply.sort.other.Sort;

public class MergeSortDownTop implements Sort{
	/*
	 * 归并排序，至底向上
	 */
	
	public  void sort(int[] data,int start ,int end , int[] arr)
	{
		for(int step=0; Math.pow(2, step)<=data.length*2; step++)
		{
			int size = (int) Math.pow(2, step);			
			for(int num=start; num<=end; num+=size)
			{				
				int end1 = num+size - 1;
				end1 = end1 > end ? end:end1;
				doSortByNoRecursion(data,num,end1,arr,size);
			}
		}
	}


	private  void doSortByNoRecursion(int[] data, int start, int end,int[] arr,int size)
	{
		int step = (int)Math.ceil(size/2.0);
		if(end <= start) return;
		if((end-start) < step) return;
		int st1 = start;
		int en1 = start + step - 1;
		int st2 = (en1+1)>=end ? end:en1+1;
		int en2 = end;
		int index = start;
		
		doSort(data,arr,st1,en1,st2,en2);
	}
	
	public  void doSort(int[] data,int[] arr,int start1,int end1,int start2,int end2)
	{
		int index = start1;
		int start = start1;
		int end = end2;
		while(start1 <= end1 && start2 <= end2)
		{
			if(data[start1] <= data[start2])
			{
				arr[index] = data[start1];
				start1++;
				index++;
			}else{
				arr[index] = data[start2];
				start2++;
				index++;				
			}
		}
		//处理合并逻辑后剩余的项
		while(end1 >= start1)
		{
			arr[index] = data[start1];
			start1++;
			index++;
		}
		//处理合并逻辑后剩余的项
		while(end2 >= start2)
		{
			arr[index] = data[start2];
			start2++;
			index++;
		}
		//合并结束后将数据写回
		for(int i=start; i<=end; i++)
		{
			data[i] = arr[i];
		}
	}
}
