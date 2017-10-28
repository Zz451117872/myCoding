package com.dataStructure.union;

public class UnionFindSetUpgrade2 implements UnionFindSet{
	/*
	 * 并查集升级版2：对并查集升级版的 并操作进行优化。
	 * 使 子节点个数少的 并入 子节点 多的节点下，有效降低节点的层数
	 */
	private int[] arr;
	private int[] size;
	
	public UnionFindSetUpgrade2(int capatify)
	{
		arr = new int[capatify];
		size = new int[capatify];
		for(int i=0; i<this.arr.length; i++)
		{
			this.arr[i] = i;
			this.size[i] = 1;
		}		
	}
	@Override
	public boolean union(int p, int q) 
	{
		if(isConnection(p,q)) return false;
		
		int pRoot = getRoot(p);
		int qRoot = getRoot(q);
		if(size[pRoot] > size[qRoot])
		{
			arr[qRoot] = arr[pRoot];
			size[pRoot] += size[qRoot];
		}else{
			arr[pRoot] = arr[qRoot];
			size[qRoot] += size[pRoot];
		}
		return true;
	}

	@Override
	public int find(int p) 
	{
		return this.arr[p];
	}

	@Override
	public boolean isConnection(int p, int q) 
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
