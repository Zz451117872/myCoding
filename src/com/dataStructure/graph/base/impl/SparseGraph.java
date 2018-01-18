package com.dataStructure.graph.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.base.IGraph;

//稀疏图，实现方式为 邻接表
public class SparseGraph implements IGraph{
	private int vertex;//顶点
	private int edge;//边
	private Node[] graph;//边
	private boolean directed;//是否有向
	
	public SparseGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.directed = directed;
		this.edge = 0;
		graph = new Node[this.vertex];	
		for(int i=0; i<this.vertex; i++)
		{
			graph[i] = null;
		}
	}
	//添加一条边
	public void addEdge(int vStart,int vEnd)
	{
		if(vStart < 0 || vStart >= this.vertex) return;
		if(vEnd < 0 || vEnd >= this.vertex) return;
		if(hasEdge(vStart,vEnd)) return;
		
		if(this.graph[vStart] == null)
		{
			this.graph[vStart] = new Node(vEnd);
		}else{
			this.graph[vStart].addNode(vEnd);
		}	
		
		if(vStart != vEnd && !this.directed)
		{
			if(this.graph[vEnd] == null)
			{
				this.graph[vEnd] = new Node(vStart);
			}else{
				this.graph[vEnd].addNode(vStart);
			}			
		}
		this.edge++;
	}
	//判断两个顶点之间是否有边
	public boolean hasEdge(int vStart,int vEnd)
	{
		if(this.graph[vStart] == null) return false;
		
		return this.graph[vStart].contains(vEnd);
	}
	//获取一个顶点的邻接点（即与该顶点有边相连的顶点）
	public List<Integer> adjacentEdge(int vertex)
	{
		List<Integer> vertexs = new ArrayList<Integer>();
		Adjacent iteator = new Adjacent(vertex,this);
		while(!iteator.end())
		{
			if(iteator.get() >= 0)
			{
				vertexs.add(iteator.get());
			}
			iteator.next();
		}
		return vertexs;
	}
	//打印矩阵信息	
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
		
		public int get()
		{		
			if(index != null)
			{
				return index.getData();
			}
			return -1;
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
		private int data; //顶点值
		private Node next;
		
		public Node(int data)
		{
			this.data = data;
			this.next = null;
		}

		public void display() 
		{
			Node node = this;
			while( node != null)
			{
				System.out.print(node.getData()+" ");
				node = node.getNext();
			}
			System.out.println("");
		}

		public boolean contains(int vEnd) 
		{
			Node node = this;
			while( node != null)
			{
				if(node.getData() == vEnd)
				{
					return true;
				}
				node = node.getNext();
			}
			return false;
		}

		public void addNode(int vEnd) 
		{
			Node node = this;
			while(node.getNext() != null)
			{
				node = node.getNext();
			}
			node.setNext(new Node(vEnd));
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}
}
