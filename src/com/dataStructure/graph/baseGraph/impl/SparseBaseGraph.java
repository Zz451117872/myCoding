package com.dataStructure.graph.baseGraph.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.baseGraph.IBaseGraph;
import com.dataStructure.graph.entry.BaseGraphNode;

//稀疏图，实现方式为 邻接表
public class SparseBaseGraph implements IBaseGraph{
	private int vertex;//顶点
	private int edge;//边
	private BaseGraphNode[] graph;//图
	private boolean directed;//是否有向
	
	public SparseBaseGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.directed = directed;
		this.edge = 0;
		graph = new BaseGraphNode[this.vertex];	
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
			this.graph[vStart] = new BaseGraphNode(vEnd);
		}else{
			this.graph[vStart].addNode(vEnd);
		}	
		
		if(vStart != vEnd && !this.directed)
		{
			if(this.graph[vEnd] == null)
			{
				this.graph[vEnd] = new BaseGraphNode(vStart);
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
			BaseGraphNode node = graph[i];
			if(node != null){
				node.display();
			}
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
		private SparseBaseGraph G;
		private int vertex;
		private BaseGraphNode index;
		
		public Adjacent(int vertex,SparseBaseGraph G)
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
	
}
