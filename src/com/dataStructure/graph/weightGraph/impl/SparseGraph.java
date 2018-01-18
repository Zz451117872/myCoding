package com.dataStructure.graph.weightGraph.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.entry.GraphNode;
import com.dataStructure.graph.weightGraph.IGraph;


//稀疏图，实现方式为 邻接表
public class SparseGraph<T extends Comparable<T>> implements IGraph<T>{
	private int vertex;//顶点数
	private int edge;//边数
	private GraphNode<T>[] graph;//图
	private boolean directed;//是否有向
	private List<Edge<T>> edges;//边集合
	
	public SparseGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.directed = directed;
		this.edge = 0;
		graph = new GraphNode[this.vertex];	
		edges = new ArrayList<Edge<T>>();
	}
	
	public void addEdge(int vStart,int vEnd,T weight)
	{
		if(vStart < 0 || vStart > this.vertex) return;
		if(vEnd < 0 || vEnd > this.vertex) return;
		
		if(hasEdge(vStart,vEnd)) return;
		
		Edge<T> edge = new Edge<T>(vStart,vEnd,weight);
		Edge<T> reverEdge = new Edge<T>(vEnd,vStart,weight);
		if(this.graph[vStart] == null)
		{
			this.graph[vStart] = new GraphNode<T>(edge);
		}else{
			this.graph[vStart].addNode(edge);
		}	
		edges.add(edge);
		if(vStart != vEnd && !this.directed)
		{
			if(this.graph[vEnd] == null)
			{
				this.graph[vEnd] = new GraphNode<T>(reverEdge);
			}else{
				this.graph[vEnd].addNode(reverEdge);
			}			
		}
		this.edge++;
	}
	
	public boolean hasEdge(int vStart,int vEnd)
	{
		if(this.graph[vStart] == null) return false;
		
		return this.graph[vStart].contains(vStart,vEnd);
	}
	
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
		private SparseGraph G;
		private int vertex;
		private GraphNode<T> index;
		
		public Adjacent(int vertex,SparseGraph G)
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
