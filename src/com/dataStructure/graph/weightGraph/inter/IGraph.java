package com.dataStructure.graph.weightGraph.inter;

import java.util.List;

import com.dataStructure.graph.weightGraph.Edge;

public interface IGraph {
	void addEdge(int vStart,int vEnd,double weight);   //
	boolean hasEdge(int vStart,int vEnd);//
	List<Edge<Double>> adjacentEdge(int vertex);//
	int getVertex();						//
	int getEdge();							//
	void matrix();							//
	boolean isDirected();
	List<Edge<Double>> getEdges();
	//
}
