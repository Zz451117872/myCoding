package com.dataStructure.graph.base.inter;

import java.util.List;

public interface IGraph {
	void addEdge(int vStart,int vEnd);   //添加一条边
	boolean hasEdge(int vStart,int vEnd);//两个项点之间是否有边
	List<Integer> adjacentEdge(int vertex);//与该顶点邻接的顶点
	int getVertex();						//顶点数
	int getEdge();							//边数
	void matrix();							//打印矩阵信息
	boolean isDirected();					//是否有向
}
