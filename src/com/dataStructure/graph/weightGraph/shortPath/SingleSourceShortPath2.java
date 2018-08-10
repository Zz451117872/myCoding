package com.dataStructure.graph.weightGraph.shortPath;

import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IWeightGraph;
import com.dataStructure.heap.impl.IndexMinHeap;
/*
 * 迪杰斯特拉 单源最短路径算法 不能存在负权边； 
 */
public class SingleSourceShortPath2 {
	
	private IWeightGraph<Double> G;//图
	private int source;	//源
	private double[] minPathValue; //最短路径值
	private int vertex;	//顶点数
	private boolean[] marked;//顶点距离源的最短路径是否已被确定
	private Edge<Double>[] from;//记录顶点的踪迹
	
	public SingleSourceShortPath2(IWeightGraph<Double> G , int vertex)
	{
		this.G = G;
		this.source = vertex;
		this.vertex = G.getVertex();
		minPathValue = new double[this.vertex];
		marked = new boolean[this.vertex];
		from = new Edge[this.vertex];
		
		for(int i=0; i<this.vertex; i++)
		{
			marked[i] = false;
			from[i] = null;
			minPathValue[i] = -1;
		}
		minPathValue[this.source] = 0; //源距离源的最短距离为0
				//最小索引堆
		IndexMinHeap<Double> heap = new IndexMinHeap<Double>(this.vertex);
		heap.push(minPathValue[this.source], this.source);//将源的最源距离压入堆
		while(!heap.isEmpty())
		{
			int index = heap.popIndex(); //弹出堆中权值最小的索引
			marked[index] = true;//从堆中弹出的索引，即可确定该顶点距离源点的最小距离
			
			List<Edge<Double>> edges = G.adjacentEdge(index);//获取邻接边
			if(edges != null && !edges.isEmpty())
			{
				for(int i=0; i<edges.size(); i++)
				{
					Edge<Double> edge = edges.get(i);
					int w = edge.getOther(index);
					if(!marked[w]) //如果该顶点距离源的最短距离还没有被确定
					{		//如果该顶点还没有被访问 或者 最短距离可以更新 ，则更行松驰操作
						if(from[w] == null || (minPathValue[index]+edge.getWeight()) < minPathValue[w])
						{
							minPathValue[w] = minPathValue[index]+edge.getWeight();
							from[w] = edge;
							if(heap.contains(w))
							{		//如果堆中包含该顶点，则更新该顶点的权值，否则将该顶点插入堆
								heap.update(edge.getWeight(), w);
							}else{
								heap.push(edge.getWeight(), w);
							}
						}
					}
				}
			}
		}
	}

	public void printMinPath()
	{
		for(int i=0; i<this.vertex; i++)
		{
			if(hasPath(i))
			{
				System.out.println(this.source+"到"+i+"的距离："+minPathValue[i]);
				printPath(i);
				System.out.println("");
			}
		}
	}
	
	private void printPath(int i) {
		Edge<Double> edge = from[i];
		if(edge != null)
		{
			int vertex = edge.getOther(i);
			if(from[vertex] != null){
				printPath(vertex);
				System.out.print("->"+i);
			}else{
				System.out.print(vertex+"->"+i);
			}
			
		}
	}

	public boolean hasPath(int vertex)
	{
		return minPathValue[vertex] != -1;
	}
}
