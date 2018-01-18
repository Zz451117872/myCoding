package com.dataStructure.graph.weightGraph.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IGraph;



//稠密图：实现方式为 邻接矩阵
public class DenseGraph<T extends Comparable<T>> implements IGraph<T>{
	private int vertex; //顶点数
	private int edge;//边数
	private boolean directed;//是否有向
	private Edge<T>[][] graph;//图
	private List<Edge<T>> edges;//所有边的集合
	
	public DenseGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.edge = 0;
		this.directed = directed;
		graph = new Edge[this.vertex][this.vertex];
		edges = new ArrayList<Edge<T>>();
	}
	//在两点之间添加一条带权边
	public void  addEdge(int vStart,int vEnd,T weight)
	{
		if(vStart < 0 || vStart >= this.vertex) return;
		if(vEnd < 0 || vEnd >= this.vertex) return;
		
		if(hasEdge(vStart,vEnd)) return;
		
		Edge<T> edge = new Edge<T>(vStart,vEnd,weight);
		Edge<T> reverEdge = new Edge<T>(vEnd,vStart,weight);
		
		this.graph[vStart][vEnd] = edge;
		edges.add(edge);
		
		if(vStart != vEnd && !this.directed)
		{
			this.graph[vEnd][vStart] = reverEdge;
		}
		this.edge ++;
	}
	//打印图信息
	public void matrix()
	{
		System.out.println("--------------------------");
		for(int i=0; i<this.vertex; i++)
		{
			for(int k=0; k<this.vertex ; k++)
			{
				if(this.graph[i][k] != null)
				{
					System.out.print(1+" ");
				}else{
					System.out.print(0+" ");
				}				
			}
			System.out.println("");
		}
		System.out.println("--------------------------");
	}
	//判断两点之间是否有边
	public boolean hasEdge(int vStart,int vEnd)
	{
		return this.graph[vStart][vEnd] != null;
	}
	//获取指定节点的邻接边
	public List<Edge<T>> adjacentEdge(int vertex)
	{		
		List<Edge<T>> edges = new ArrayList<Edge<T>>();
		AdjIterator adj = new AdjIterator(vertex,this);
		while(!adj.end())
		{
			if(adj.get() != null)
			{
				edges.add(adj.get());
			}
			adj.next();
		}
		return edges;
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

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}
	
	public List<Edge<T>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<T>> edges) {
		this.edges = edges;
	}

	class AdjIterator{
		private DenseGraph<T> G;
		private int index;
		private int vertex;
		
		public AdjIterator(int vertex ,DenseGraph<T> G)
		{
			this.G = G;
			this.vertex = vertex;
			this.index = 0;
		}
		
		public Edge<T> get()
		{
			if(this.G.graph[this.vertex][index] != null)
			{
				return this.G.graph[this.vertex][index];
			}
			return null;
		}
		
		public void next()
		{
			this.index ++;
		}
		
		public boolean end()
		{
			return this.index >= this.G.graph[this.vertex].length;
		}
	}	
}
