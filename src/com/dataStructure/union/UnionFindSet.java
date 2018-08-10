package com.dataStructure.union;

public interface UnionFindSet {
	//合并 两点
	public boolean union(int p,int q);
	public int find(int p);
	//判断两点是否连接
	public boolean isConnection(int p,int q);
}
