package com.dataStructure.union;

public interface UnionFindSet {
	public boolean union(int p,int q);
	public int find(int p);
	public boolean isConnection(int p,int q);
}
