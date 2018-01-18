package com.dataStructure.graph.weightGraph;


public interface IGraph<T extends Comparable<T>> {
	//在2个顶点间添加一条带权边
	void addEdge(int vStart,int vEnd,T weight);
	//判断2个顶点是否有边
	boolean hasEdge(int vStart,int vEnd);
	//打印图信息
	void matrix();	
	int getVertex();
}
