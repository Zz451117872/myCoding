package com.dataStructure.graph.baseGraph;

import java.util.List;

public interface IBaseGraph {
	void addEdge(int vStart,int vEnd);   //
	boolean hasEdge(int vStart,int vEnd);//
	void matrix();	
	int getVertex();
	List<Integer> adjacentEdge(int v);
}
