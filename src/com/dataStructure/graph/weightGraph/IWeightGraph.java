package com.dataStructure.graph.weightGraph;

import java.util.List;

import com.dataStructure.graph.entry.Edge;

public interface IWeightGraph<T extends Comparable<T>> {
	//在2个顶点间添加一条带权边
	void addEdge(int vStart,int vEnd,T weight);
	//判断2个顶点是否有边
	boolean hasEdge(int vStart,int vEnd);
	//打印图信息
	void matrix();	
	int getVertex();
	int getEdge();
	List<Edge<T>> getEdges();
	List<Edge<T>> adjacentEdge(int vertex);
}
