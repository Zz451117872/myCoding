package com.dataStructure.graph.weightGraph.minProductTtee;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.weightGraph.Edge;
import com.dataStructure.graph.weightGraph.inter.IGraph;
import com.dataStructure.heap.impl.IndexMinHeap;

//prim算法升级版：主要是利用 索引堆 可以对指定 下标的数据进行修改
public class PrimUpgrade {
	
	private IGraph G;			//连通图
	private boolean[] marked;	//是否已标记
	private double minWeight;	//最小总权值
	IndexMinHeap<Edge<Double>> indexMinHeap;	//最小索引堆 
	private List<Edge<Double>> minProductTree;	//最小生成树
	private int vertex;
	
	public PrimUpgrade(IGraph G)
	{
		this.G = G;
		this.vertex = G.getVertex();
		this.minWeight = 0;
		marked = new boolean[this.vertex];
		indexMinHeap = new IndexMinHeap<Edge<Double>>(this.vertex);
		minProductTree = new ArrayList<Edge<Double>>();
		for(int i=0; i<this.vertex; i++)
		{
			marked[i] = false;
		}
		mark(0);
		while(!indexMinHeap.isEmpty())
		{
			Edge<Double> edge = indexMinHeap.pop();
			if(!isCrossEdge(edge)) continue;
			minProductTree.add(edge);
			
			this.minWeight += edge.getWeight();
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
		List<Edge<Double>> edges = this.G.adjacentEdge(vertex);	//得到该顶点的邻接边
		
		if(edges == null || edges.size() < 1) return;
		for(Edge<Double> edge : edges)
		{
			if(isCrossEdge(edge))	// 判断是否是横切边
			{
				int index = edge.getOther(vertex);		//得到该边的另一个 顶点
													//以该顶点为下标，查看堆 中是否已有值，有则判断是否更新，无则直接压入
				Edge<Double> crossEdge = (Edge<Double>) indexMinHeap.getDataByIndex(index);
				if(crossEdge == null)
				{
					indexMinHeap.push(edge,index);
				}else{
					if(edge.compareTo(crossEdge) < 0)
					{
						indexMinHeap.change(edge, index);
					}
				}
			}
		}
	}

	private boolean isCrossEdge(Edge edge) 
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
				for(Edge<Double> edge : minProductTree)
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
