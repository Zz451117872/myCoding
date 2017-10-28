package com.dataStructure.graph.weightGraph.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.weightGraph.Edge;
import com.dataStructure.graph.weightGraph.inter.IGraph;



//稠密图：实现方式为 邻接矩阵
public class DenseGraph implements IGraph{
	private int vertex;
	private int edge;
	private boolean directed;
	private Edge<Double>[][] graph;
	private List<Edge<Double>> edges;
	
	public DenseGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.edge = 0;
		this.directed = directed;
		graph = new Edge[this.vertex][this.vertex];
		edges = new ArrayList<Edge<Double>>();
	}
	
	public void  addEdge(int vStart,int vEnd,double weight)
	{
		if(vStart < 0 || vStart > this.vertex) return;
		if(vEnd < 0 || vEnd > this.vertex) return;
		
		if(hasEdge(vStart,vEnd)) return;
		
		Edge<Double> edge = new Edge<Double>(vStart,vEnd,weight);
		Edge<Double> reverEdge = new Edge<Double>(vEnd,vStart,weight);
		
		this.graph[vStart][vEnd] = edge;
		edges.add(edge);
		
		if(vStart != vEnd && !this.directed)
		{
			this.graph[vEnd][vStart] = reverEdge;
		}
		this.edge ++;
	}
	
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
	
	public boolean hasEdge(int vStart,int vEnd)
	{
		return this.graph[vStart][vEnd] != null;
	}
	
	public List<Edge<Double>> adjacentEdge(int vertex)
	{		
		List<Edge<Double>> edges = new ArrayList<Edge<Double>>();
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
	


	public List<Edge<Double>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<Double>> edges) {
		this.edges = edges;
	}



	class AdjIterator{
		private DenseGraph G;
		private int index;
		private int vertex;
		
		public AdjIterator(int vertex ,DenseGraph G)
		{
			this.G = G;
			this.vertex = vertex;
			this.index = 0;
		}
		
		public Edge<Double> get()
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
