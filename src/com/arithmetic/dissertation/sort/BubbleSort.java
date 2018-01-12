package com.arithmetic.dissertation.sort;

import com.arithmetic.dissertation.sort.other.Sort;

public class BubbleSort implements Sort{
	/*
	 * 冒泡排序，性能较差
	 */
	public  void sort(int[] data,int start, int end,int[] room)
	{
		int length = data.length;
		for(int i=0; i<length-1; i++)
		{
			int flag =1;
			for(int j=0; j<length-1-i; j++)
			{
				if(data[j] > data[j+1])
				{
					swap(data,j,j+1);
					flag = 0;
				}
			}
			if(flag == 1) break;
		}
	}
	private  void swap(int[] data,int i, int j) 
	{
		int temp = data[i];
		data[i]=data[j];
		data[j]=temp;
	}
}
