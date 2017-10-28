package com.dataStructure.graph.weightGraph.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.weightGraph.Edge;
import com.dataStructure.graph.weightGraph.inter.IGraph;


//稀疏图，实现方式为 邻接表
public class SparseGraph implements IGraph{
	private int vertex;
	private int edge;
	private Node[] graph;
	private boolean directed;
	private List<Edge<Double>> edges;
	
	public SparseGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.directed = directed;
		this.edge = 0;
		graph = new Node[this.vertex];	
		edges = new ArrayList<Edge<Double>>();
	}
	
	public void addEdge(int vStart,int vEnd,double weight)
	{
		if(vStart < 0 || vStart > this.vertex) return;
		if(vEnd < 0 || vEnd > this.vertex) return;
		
		if(hasEdge(vStart,vEnd)) return;
		
		Edge<Double> edge = new Edge<Double>(vStart,vEnd,weight);
		Edge<Double> reverEdge = new Edge<Double>(vEnd,vStart,weight);
		if(this.graph[vStart] == null)
		{
			this.graph[vStart] = new Node(edge);
		}else{
			this.graph[vStart].addNode(edge);
		}	
		edges.add(edge);
		if(vStart != vEnd && !this.directed)
		{
			if(this.graph[vEnd] == null)
			{
				this.graph[vEnd] = new Node(reverEdge);
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
	
	public List<Edge<Double>> adjacentEdge(int vertex)
	{
		List<Edge<Double>> edges = new ArrayList<Edge<Double>>();
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













	public List<Edge<Double>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<Double>> edges) {
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
		private Node index;
		
		public Adjacent(int vertex,SparseGraph G)
		{
			this.vertex = vertex;
			this.G = G;
			this.index = this.G.graph[this.vertex];
		}
		
		public Edge<Double> get()
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
	
	
	
	
	
	
	
	
	
	
	
	class Node{
		private Edge<Double> edge;
		private Node next;
		
		public Node(Edge edge)
		{
			this.edge = edge;
			this.next = null;
		}

		public void display() 
		{
			Node node = this;
			while( node != null)
			{
				System.out.println(node.getEdge().toString());
				node = node.getNext();
			}
		}

		public boolean contains(int vStart,int vEnd) 
		{
			Node node = this;
			while( node != null)
			{
				if(node.getEdge().getOther(vStart) == vEnd)
				{
					return true;
				}
				node = node.getNext();
			}
			return false;
		}

		public void addNode(Edge edge) 
		{
			Node node = this;
			while(node.getNext() != null)
			{
				node = node.getNext();
			}
			node.setNext(new Node(edge));
		}

	

		public Edge<Double> getEdge() {
			return edge;
		}

		public void setEdge(Edge<Double> edge) {
			this.edge = edge;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}
}
