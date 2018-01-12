package com.arithmetic.dissertation.sort;

import java.util.Random;

import com.arithmetic.dissertation.sort.other.Sort;

public class QulikSort2Ways implements Sort{
	/*
	 * 快排序,双路快排序能解决 1路快排序中 对于大量重复数据 性能低下的问题
	 * 快排序是不稳定的，不需要额外的空间。
	 */
	public  void sort(int[] data,int start,int end,int[] room)
	{
		if(data == null || data.length<=0) return;
		if(end == start) return;
		
		int sentinel = data[end];		//哨兵
		int left = start;
		int right = end;
		while(left < right)
		{
			while(left < right && data[left] <= sentinel)
			{	
				left ++;
			}
			data[right] = data[left];
			while(left < right && data[right] >= sentinel)
			{
				right --;
			}
			data[left] = data[right];
		}
		data[left] = sentinel;
		//循环结束后，数列被分成两段，哨兵左边比哨兵小，哨兵右边比哨兵大
		if(left > start)
		{
			sort(data,start,left-1,null);
		}
		if(right < end)
		{
			sort(data,right+1,end,null);
		}		
	}

	private boolean checkRepeat(int[] data, int start, int end) 
	{
		int temp = data[end];
		for(int i=start; i< end; i++)
		{
			if(data[i] != temp)
			{
				return false;
			}
		}
		System.out.println(end - start+":"+data[start]);
		return true;
	}
}
