package com.arithmetic.dissertation.sort;

import com.arithmetic.dissertation.sort.other.Sort;

public class QulikSort1Ways implements Sort{

	/*
	 * 快排序，1路快排序 对于 大量重复数据 性能低下
	 */
	public  void sort(int[] data,int start,int end ,int[] room)
	{
		if(data == null || data.length<=0) return;
		int mid = partition(data,start,end);
		if(mid != -1)
		{
			int mid_left = mid - 1<start ? start: mid-1;
			int mid_right = mid + 1 > end ? end:mid+1;
			sort(data,start,mid_left,null);
			sort(data,mid_right,end,null);
		}
	}
	
	/*
	 * 将数据重排后，使之满足哨兵左边的数据比哨兵小，哨兵右边的数据比哨兵大，并返回哨兵位置。
	 */
	private  int partition(int[] data, int start, int end) 
	{
		if(end == start)
		{
			return -1;
		}
		int sentinel = data[end];		//哨兵
		int left = start -1;
		int right = start;
		for( ; right <= end-1; right++)
		{
			if(data[right] < sentinel)
			{
				left = left +1;
				change(data,left,right);
			}
		}
		change(data,left+1,right);
		return left+1;
	}

	private  void change(int[] data, int left, int right) {
		int temp = data[left];
		data[left] = data[right];
		data[right] = temp;
	}
}
