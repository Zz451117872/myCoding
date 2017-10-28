package com.dataStructure.union;

public class UnionFindSetUpgrade implements UnionFindSet{
	/*
	 * 并查集 升级版：相连的点 存储 父节点 的键值，根节点 相同则 相连。
	 * 
	 */
	private int[] arr;
	
	public UnionFindSetUpgrade(int capatify)
	{
		arr = new int[capatify];
		for(int i=0; i< arr.length; i++)
		{
			arr[i] = i;
		}
	}
	
	public boolean union(int p,int q)
	{
		if(isConnection(p,q)) return false;
		
		int root = getRoot(p);
		arr[root] = getRoot(q);
		return true;
	}
	
	public int find(int p)
	{
		return this.arr[p];
	}
	
	public boolean isConnection(int p,int q)
	{
		return getRoot(p) == getRoot(q) ;
	}

	private int getRoot(int p) 
	{
		int root = p;
		while(arr[root] != root)
		{
			root = arr[root];
		}
		return root;
	}
}
