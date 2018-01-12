package com.arithmetic.dissertation.sort.other;

import java.util.Random;

public class RandomSelect {

	public  int random_select(int[] data,int start,int end,int index)
	{
		if(data == null || data.length < 1) return 0;
		if(end == start ) return data[start];
		
		int mid = partition(data,start,end);
		
		int count = mid - start +1;
		
		if(index == count)
		{
			return data[mid];
		}else if(index < count)
		{
			mid = mid - 1< start ? start: mid -1;
			return random_select(data,start,mid,index);
		}else{
			mid = mid + 1 > end ? end: mid+1;
			return random_select(data,mid,end,index-count);
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
		int sentinel = data[end];
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
