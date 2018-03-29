package com.arithmetic.dissertation.sort.impl;

import java.util.Random;

public class TableSort {
	
	/*
	 * 表排序：间接排序，不直接移动排序对象，通过对象的key值完成排序
	 */
	public  void sort(int[] data,int[] table)
	{		
		for(int i=0; i<data.length-1; i++)
		{
			for(int k=0; k<data.length-1-i; k++)
			{
				if(data[table[k]] > data[table[k+1]])
				{
					swap(table,k,k+1);
				}
			}
		}
	}
	public static void main(String[] str)
	{
		int[] data = {23,12,53,22,34,124,87,94,21,44};
		int[] table = new int[data.length];
		for(int i=0; i<table.length; i++)
		{
			table[i] = i;
		}
		TableSort ts = new TableSort();
		ts.sort(data, table);
		for(int i=0; i<data.length; i++)
		{
			System.out.print(data[table[i]] +" ->");
		}
		System.out.println("");
	}
	private  void swap(int[] table, int k, int i) {
		int temp = table[k];
		table[k] = table[i];
		table[i] = temp;
	}
}
