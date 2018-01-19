package com.dataStructure.graph.weightGraph.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.entry.WeightGraphNode;
import com.dataStructure.graph.weightGraph.IWeightGraph;


//稀疏图，实现方式为 邻接表
public class SparseWeightGraph<T extends Comparable<T>> implements IWeightGraph<T>{
	private int vertex;//顶点数
	private int edge;//边数
	private WeightGraphNode<T>[] graph;//图
	private boolean directed;//是否有向
	private List<Edge<T>> edges;//边集合
	
	public SparseWeightGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.directed = directed;
		this.edge = 0;
		graph = new WeightGraphNode[this.vertex];	
		edges = new ArrayList<Edge<T>>();
	}
	//在两个顶点间添加一条带权边
	public void addEdge(int vStart,int vEnd,T weight)
	{
		if(vStart < 0 || vStart >= this.vertex) return;
		if(vEnd < 0 || vEnd >= this.vertex) return;
		
		if(hasEdge(vStart,vEnd)) return;
		
		Edge<T> edge = new Edge<T>(vStart,vEnd,weight);
		Edge<T> reverEdge = new Edge<T>(vEnd,vStart,weight);
		if(this.graph[vStart] == null)
		{
			this.graph[vStart] = new WeightGraphNode<T>(edge);
		}else{
			this.graph[vStart].addNode(edge);
		}	
		edges.add(edge);
		if(vStart != vEnd && !this.directed)
		{
			if(this.graph[vEnd] == null)
			{
				this.graph[vEnd] = new WeightGraphNode<T>(reverEdge);
			}else{
				this.graph[vEnd].addNode(reverEdge);
			}			
		}
		this.edge++;
	}
	//判断两个顶点间是否存在边
	public boolean hasEdge(int vStart,int vEnd)
	{
		if(this.graph[vStart] == null) return false;
		
		return this.graph[vStart].contains(vStart,vEnd);
	}
	//获取指定顶点的所有邻接边
	public List<Edge<T>> adjacentEdge(int vertex)
	{
		List<Edge<T>> edges = new ArrayList<Edge<T>>();
		Adjacent iteator = new Adjacent(vertex,this);
		while(!iteator.end())
		{
			if(iteator.get() != null)
			{
				edges.add(iteator.get());
			}
			iteator.next();
		}
		return edges;
	}
	//打印图信息
	public void matrix()
	{
		for(int i=0; i<this.vertex; i++)
		{
			this.graph[i].display();
		}
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getEdge() {
		return edge;
	}

	public void setEdge(int edge) {
		this.edge = edge;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<T>> edges) {
		this.edges = edges;
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	class Adjacent{
		private SparseWeightGraph<T> G;
		private int vertex;
		private WeightGraphNode<T> index;
		
		public Adjacent(int vertex,SparseWeightGraph<T> G)
		{
			this.vertex = vertex;
			this.G = G;
			this.index = this.G.graph[this.vertex];
		}
		
		public Edge<T> get()
		{		
			if(index != null)
			{
				return index.getEdge();
			}
			return null;
		}
		
		public void next()
		{
			this.index = index.getNext();
		}
		
		public boolean end()
		{
			return this.index == null;
		}
	}
}
