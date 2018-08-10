package com.dataStructure.union;

public class UnionFindSetBase implements UnionFindSet{
	/*
	 * 并查集 基础版：相连的点 存储相同的键值 
	 * 提供 并，查 操作
	 */
	private int[] arr;
	
	public UnionFindSetBase(int capacity)
	{
		arr = new int[capacity];
		for(int i=0; i<arr.length; i++)
		{
			arr[i] = i;
		}
	}
	
	public void display()
	{
		for(int i=0; i<arr.length; i++)
		{
			System.out.print(arr[i]+"->");
		}
		System.out.println("");
	}
	
	public boolean union(int p,int q)
	{
		if( this.isConnection(p, q)) return false;
		
		int root = arr[p];
		for(int i=0; i<this.arr.length; i++)
		{
			if(arr[i] == root)
			{
				arr[i] = arr[q];
			}
		}
		return true;
	}
	
	public int find(int p)
	{
		return this.arr[p];
	}
	
	public boolean isConnection(int p,int q)
	{
		return arr[p] == arr[q];
	}
}
