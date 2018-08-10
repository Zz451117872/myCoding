package com.dataStructure.union;

public class UnionFindSetUpgrade5 implements UnionFindSet{
	/*
	 * 并查集升级版3：对并查集升级版2的 并操作进行优化。
	 * 使 子节点层级低的 并入 子节点 层级高的节点下，有效降低节点的层数
	 */
	private int[] arr;
	private int[] rank;
	
	public UnionFindSetUpgrade5(int capatify)
	{
		arr = new int[capatify];
		rank = new int[capatify];
		for(int i=0; i<this.arr.length; i++)
		{
			this.arr[i] = i;
			this.rank[i] = 1;
		}
	}
	@Override
	public boolean union(int p, int q) 
	{
		if(isConnection(p,q)) return false;
		
		int pRoot = getRoot(p);
		int qRoot = getRoot(q);
		if(rank[pRoot] > rank[qRoot])
		{
			arr[qRoot] = pRoot;
		}else if(rank[pRoot] < rank[qRoot]){
			arr[pRoot] = qRoot;
		}else{
			arr[qRoot] = pRoot;
			rank[pRoot] += 1;
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
		return getRoot(p) == getRoot(q);
	}

	private int getRoot(int p) 
	{
		int root = p;
		while(arr[root] != root)
		{
			arr[root] = arr[arr[root]];//路径压缩
			root = arr[root];
		}
		return root;
	}

}
