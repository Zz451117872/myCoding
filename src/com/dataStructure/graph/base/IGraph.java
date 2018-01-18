package com.dataStructure.graph.base;

import java.util.List;

public interface IGraph {
	void addEdge(int vStart,int vEnd);   //
	boolean hasEdge(int vStart,int vEnd);//
	List<Integer> adjacentEdge(int vertex);//
	int getVertex();						//
	int getEdge();							//
	void matrix();							//
	boolean isDirected();					//
}
