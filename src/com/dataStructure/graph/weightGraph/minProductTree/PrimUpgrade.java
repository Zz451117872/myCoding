package com.dataStructure.graph.weightGraph.minProductTree;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IWeightGraph;
import com.dataStructure.heap.impl.IndexMinHeap;

//prim算法升级版：主要是利用 索引堆 可以对指定 下标的数据进行修改
public class PrimUpgrade<T extends Comparable<T>> {
	
	private IWeightGraph<T> G;			//连通图
	private boolean[] marked;	//是否已标记
	private double minWeight;	//最小总权值
	IndexMinHeap<Edge<T>> indexMinHeap;	//最小索引堆 
	private List<Edge<T>> minProductTree;	//最小生成树
	private int vertex;
	
	public PrimUpgrade(IWeightGraph<T> G)
	{
		this.G = G;
		this.vertex = G.getVertex();
		this.minWeight = 0;
		marked = new boolean[this.vertex];
		indexMinHeap = new IndexMinHeap<Edge<T>>(this.vertex);
		minProductTree = new ArrayList<Edge<T>>();
		for(int i=0; i<this.vertex; i++)
		{
			marked[i] = false;
		}
		mark(0);
		while(!indexMinHeap.isEmpty())
		{
			Edge<T> edge = indexMinHeap.pop();
			if(!isCrossEdge(edge)) continue;
			minProductTree.add(edge);
			
			this.minWeight += (Double)edge.getWeight();
			if(minProductTree.size() == this.vertex -1) return;
			
			if(marked[edge.getV()])
			{
				mark(edge.getW());
			}else{
				mark(edge.getV());
			}
		}
	}

	//对顶点 执行 标记动作
	private void mark(int vertex) 
	{
		if(vertex < 0 || vertex >= this.vertex) return;
		if(marked[vertex]) return;
		marked[vertex] = true;		//标记它
		List<Edge<T>> edges = this.G.adjacentEdge(vertex);	//得到该顶点的邻接边
		
		if(edges == null || edges.size() < 1) return;
		for(Edge<T> edge : edges)
		{
			if(isCrossEdge(edge))	// 判断是否是横切边
			{
				int index = edge.getOther(vertex);		//得到该边的另一个 顶点
													//以该顶点为下标，查看堆 中是否已有值，有则判断是否更新，无则直接压入
				Edge<T> crossEdge = (Edge<T>) indexMinHeap.get(index);
				if(crossEdge == null)
				{
					indexMinHeap.push(edge,index);
				}else{
					if(edge.compareTo(crossEdge) < 0)
					{
						indexMinHeap.update(edge, index);
					}
				}
			}
		}
	}

	private boolean isCrossEdge(Edge<T> edge) 
	{
		if(marked[edge.getV()] != marked[edge.getW()])
		{
			return true;
		}
		return false;
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

}
