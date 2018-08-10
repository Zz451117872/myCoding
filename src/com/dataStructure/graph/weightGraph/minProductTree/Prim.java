package com.dataStructure.graph.weightGraph.minProductTree;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IWeightGraph;
import com.dataStructure.heap.impl.MinHeap;

//prim算法：主要是利用 最小堆 不断弹出 最小权值边
public class Prim<T extends Comparable<T>> {
	
	private IWeightGraph<T> G;							//图
	private boolean[] marked;					//是否被标记了
	private List<Edge<T>> minProductTree;	//最小生成 树
	private MinHeap<Edge<T>> minHeap;		//最小堆 
	private double minWeight ;			//最小生成树 总权值
	private int vertex;
	
	public Prim(IWeightGraph<T> G)
	{
		this.G = G;
		minWeight = 0;
		vertex = G.getVertex();
		marked = new boolean[this.G.getVertex()];
		minHeap = new MinHeap<Edge<T>>(this.G.getEdge());
		minProductTree = new ArrayList<Edge<T>>();
		
		for(int i=0; i < this.G.getVertex(); i++)
		{
			marked[i] = false;
		}
		
		mark(0);	//从0开始标记，如果不是连通图，需要遍历标记
		while(!minHeap.isEmpty())
		{
			Edge<T> minEdge = minHeap.pop();	//最小堆 挤出最小权重 边
			if(!isTransectionEdge(minEdge)) continue; //判断是否是 横切边
			minProductTree.add(minEdge);		//将最小权重 边 加入最小生成树
			this.minWeight += (Double)minEdge.getWeight(); //更新 最小生成树 总权值
			if(minProductTree.size() == this.vertex - 1) return;		//最小边数量 等于 顶点数量减1时，生成完成
			
			if(marked[minEdge.getV()])
			{
				mark(minEdge.getW());
			}else{
				mark(minEdge.getV());
			}
		}		
	}
	
	//打印 最小生成树 的所有边
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
	
	public double getMinWeight()
	{
		return this.minWeight;
	}
<<<<<<< HEAD
	
	//对一个 顶点 加标记
=======
	//对一个 顶点 加票房
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	private void mark(int vertex) 
	{
		if(vertex < 0 || vertex >= this.vertex) return;
		if(marked[vertex]) return;
		
		marked[vertex] = true;	//将标记 顶点 置为 已标记
								//得到该顶点的 邻接边，并去除已 生成最小树的边
		List<Edge<T>> edges = this.G.adjacentEdge(vertex);
		
		if(edges != null)
		{
			for(Edge<T> edge : edges)
			{
				if(isTransectionEdge(edge))
				{
					minHeap.push(edge);		//将 邻接边 加入最小堆 
				}			
			}
		}				
	}
<<<<<<< HEAD
	
	// 判断 是否是横切边，如果两个点都已被标记，则不为横切边
=======
	// 判断 是否是横切边
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	private boolean isTransectionEdge(Edge<T> edge) 
	{
		if(marked[edge.getV()] != marked[edge.getW()])
		{
			return true;
		}
		return false;
	}	
}
