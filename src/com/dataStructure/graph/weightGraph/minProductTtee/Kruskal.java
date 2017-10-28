package com.dataStructure.graph.weightGraph.minProductTtee;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.weightGraph.Edge;
import com.dataStructure.graph.weightGraph.inter.IGraph;
import com.dataStructure.graph.weightGraph.util.MergeSortEdge;
import com.dataStructure.union.UnionFindSetUpgrade2;

//kruskal算法：主要是利用并查集 判断 当加入某条边后是否会产生 环
public class Kruskal {

	private IGraph G;			//连通图
	private boolean[] marked;	//是否已标记
	private List<Edge<Double>> minProductTree;
	UnionFindSetUpgrade2 union;	//并查集
	private double minWeight;
	private int vertex;
	
	public Kruskal(IGraph G)
	{
		this.G = G;											//连通图
		this.vertex = G.getVertex();						//顶点数
		this.minWeight = 0;									//最小生成树权值总和
		marked = new boolean[this.vertex];					//是否标记
		minProductTree = new ArrayList<Edge<Double>>();		//最小生成树
		union = new UnionFindSetUpgrade2(this.vertex);		//并查集
		for(int i=0; i<this.vertex ; i++)
		{
			marked[i] = false;
		}
		//对所有的 带权边 进行排序
		Edge<Double>[] edges = new Edge[this.G.getEdge()];
		this.G.getEdges().toArray(edges);
		MergeSortEdge.sort(edges, 0, edges.length-1, new Edge[edges.length]);
		//从权值最小 边开始遍历
		for(int i=0; i<edges.length; i++)
		{
			Edge<Double> edge = edges[i];
			if(!isLoop(edge))	//权值最小，且不会造成环，则该边是最小生成树
			{
				if(!marked[edge.getV()]) marked[edge.getV()] = true;
				if(!marked[edge.getW()]) marked[edge.getW()] = true;
				
				minProductTree.add(edge);	//加入最小生成树
				this.minWeight += edge.getWeight();	//更新权值
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
			for(Edge<Double> edge : minProductTree)
			{
				System.out.println(edge.toString());
			}
		}
	}
	
	//判断 新增的边 是否会造成环
	private boolean isLoop(Edge<Double> edge) 
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
