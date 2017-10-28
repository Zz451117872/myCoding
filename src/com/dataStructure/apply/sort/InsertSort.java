package com.dataStructure.apply.sort;

import com.dataStructure.apply.sort.other.Sort;

public class InsertSort implements Sort{
	/*
	 * 插入排序,对于逆顺对较少，数列近似有序的前提下，插入排序性能非常好。
	 * 插入排序 是稳定的排序，且不需要额外的空间
	 */
	public  void sort(int[] data,int start ,int end,int[] room)
	{
		for(int i=1; i<data.length; i++)
		{
//			System.out.println(i);
			int temp = data[i];
			int flag = i-1;
			while(flag>=0 && temp<data[flag])
			{
				data[flag+1] = data[flag];
				flag = flag-1;
			}
			data[flag+1] = temp;			
		}
	}
}
