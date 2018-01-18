package com.dataStructure.graph.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.base.IGraph;

//稠密图：实现方式为 邻接矩阵
public class DenseGraph implements IGraph{
	private int vertex;//顶点
	private int edge;//边
	private boolean directed;//是否有向
	private boolean[][] graph;//图
	
	public DenseGraph(int vertex,boolean directed)
	{
		this.vertex = vertex;
		this.edge = 0;
		this.directed = directed;
		graph = new boolean[this.vertex][this.vertex];
		for(int i=0; i<this.vertex; i++)
		{
			for(int k=0; k<this.vertex; k++)
			{
				graph[i][k]= false;
			}
		}
	}
	
	/*
	 * 添加一条边
	 */
	public void  addEdge(int vStart,int vEnd)
	{
		if(vStart < 0 || vStart >= this.vertex) return;
		if(vEnd < 0 || vEnd >= this.vertex) return;
		if(hasEdge(vStart,vEnd)) return;
		
		this.graph[vStart][vEnd] = true;
		if(vStart != vEnd && !this.directed)
		{
			this.graph[vEnd][vStart] = true;
		}
		this.edge ++;
	}
	/*
	 * 打印图信息
	 */
	public void matrix()
	{
		System.out.println("--------------------------");
		for(int i=0; i<this.vertex; i++)
		{
			for(int k=0; k<this.vertex ; k++)
			{
				if(this.graph[i][k])
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
	//判断是否有边
	public boolean hasEdge(int vStart,int vEnd)
	{
		return this.graph[vStart][vEnd];
	}
	/*
	 * 获取一个顶点的 邻接点（即与该顶点有边的顶点）
	 */
	public List<Integer> adjacentEdge(int vertex)
	{		
		List<Integer> vertexs = new ArrayList<Integer>();
		AdjIterator adj = new AdjIterator(vertex,this);
		while(!adj.end())
		{
			if(adj.get() >= 0)
			{
				vertexs.add(adj.get());
			}
			adj.next();
		}
		return vertexs;
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
		
		public int get()
		{
			if(this.G.graph[this.vertex][index])
			{
				return index;
			}
			return -1;
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
