package com.dataStructure.graph.weightGraph.minProductTree;

import java.util.ArrayList;
import java.util.List;

import com.arithmetic.dissertation.sort.GeneralSort;
import com.arithmetic.dissertation.sort.impl.GeneralSortImpl;
import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IWeightGraph;
import com.dataStructure.union.UnionFindSetUpgrade2;

//kruskal算法：主要是利用并查集 判断 当加入某条边后是否会产生 环
public class Kruskal<T extends Comparable<T>> {

	private IWeightGraph<T> G;			//连通图
	private boolean[] marked;	//是否已标记
	private List<Edge<T>> minProductTree;
	UnionFindSetUpgrade2 union;	//并查集
	private double minWeight;
	private int vertex;
	
	@SuppressWarnings("unchecked")
	public Kruskal(IWeightGraph<T> G)
	{
		this.G = G;											//连通图
		this.vertex = G.getVertex();						//顶点数
		this.minWeight = 0;									//最小生成树权值总和
		marked = new boolean[this.vertex];					//是否标记
		minProductTree = new ArrayList<Edge<T>>();		//最小生成树
		union = new UnionFindSetUpgrade2(this.vertex);		//并查集
		for(int i=0; i<this.vertex ; i++)
		{
			marked[i] = false;
		}
		//对所有的 带权边 进行排序
		Edge<T>[] edges = new Edge[this.G.getEdge()];
		this.G.getEdges().toArray(edges);
		GeneralSort sorter = new GeneralSortImpl();
		sorter.mergeSortByRecursion(edges, 0, edges.length-1, new Edge[edges.length]);
		//从权值最小 边开始遍历
		for(int i=0; i<edges.length; i++)
		{
			Edge<T> edge = edges[i];
			if(!isLoop(edge))	//权值最小，且不会造成环，则该边是最小生成树
			{
				if(!marked[edge.getV()]) marked[edge.getV()] = true;
				if(!marked[edge.getW()]) marked[edge.getW()] = true;
				
				minProductTree.add(edge);	//加入最小生成树
				this.minWeight += (Double)edge.getWeight();	//更新权值
				if(minProductTree.size() == this.vertex -1) return;
			}
		}
	}

	public double getMinWeight()
	{
		return this.minWeight;
	}
	
	public void displayMinProductTree()
	{
		if( minProductTree != null && minProductTree.size() > 0)
		{
			for(Edge<T> edge : minProductTree)
			{
				System.out.println(edge.toString());
			}
		}
	}
	
	//判断 新增的边 是否会造成环
	private boolean isLoop(Edge<T> edge) 
	{
		List<Integer> vertexs = getMarkedVertex();//得到所有已标记的顶点
		if(vertexs == null || vertexs.size() < 1) return false;
		
		if(union.isConnection(edge.getV(), edge.getW()))
		{		//利用并查集判断 两顶点 是否 有环
			return true;
		}
		union.union(edge.getV(), edge.getW());
		return false;
	}
	//获得 所有的已标记的顶点
	private List<Integer> getMarkedVertex() 
	{
		List<Integer> markedVertex = new ArrayList<Integer>();
		for(int i=0; i<this.marked.length; i++)
		{
			if(marked[i])
			{
				markedVertex.add(i);
			}
		}
		return markedVertex;
	}

}
