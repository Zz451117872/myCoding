package com.arithmetic.dissertation.sort;

import java.util.Random;

import com.arithmetic.dissertation.sort.other.Sort;

public class QuilkSort3Ways implements Sort{
	static Random r = new Random();
	/*
	 * 快速排序：3路快排序，解决2路1路对高重复数据 排序 栈溢出问题。
	 * 如果需要保证其稳定性，则需要损失大量性能。在不保证稳定性的前提下，3路快排序对高重复数据排序性能优秀
	 */
	@Override
	public void sort(int[] data, int start, int end, int[] room) 
	{
		if(end == start) return;
		
		int begin = start;
		int sentinel = data[end];
		int lt = start -1;
		int gt = end+1;
		while(begin < gt)
		{
			if(data[begin] > sentinel)
			{
				swap(data,begin,gt-1);
				gt --;
			}else if(data[begin] < sentinel)
			{
//				int temp = data[begin];
//				for(int i=begin-1; i>lt; i--)
//				{
//					data[i+1] = data[i];
//				}
//				data[lt +1] = temp;
				swap(data,begin,lt+1);
				lt ++;
				begin ++;
			}else{
				begin ++;
			}		
		}
		
		if(lt > start)
		{
			sort(data,start,lt,room);
		}
		if(gt < end)
		{
			sort(data,gt,end,room);
		}
	}

	private void swap(int[] data, int begin, int end) 
	{
		int temp = data[begin];
		data[begin] = data[end];
		data[end] = temp;
	}
}
