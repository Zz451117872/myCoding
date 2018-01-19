package com.dataStructure.graph.weightGraph.shortPath;

import java.util.ArrayList;
import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IWeightGraph;

/*
 * 单源最短路径：
 * 完全可以不用索引堆 ，为什么视频中强调用索引堆？
 */
public class SingleSourceShortPath<T extends Comparable<T>> {
	private IWeightGraph<T> G;
	List<Edge<T>> edges; //横切边集合 ？为什么要有这个集合？因为只有是横切边才有需要mark而未mark的顶点
	private int source;		// 从哪里开始寻路
	private double[] minPathValue;//最短路径值
	private int vertex;			//
	private List<Integer>[] from;//到达该顶点的最短路径
	private boolean[] marked;	//这个顶点是否已标记，是则证明这个顶点的最短路径已确定
	
	public int getVertex()
	{
		return this.vertex;
	}
	//打印最短路径值
	public void displayMinPathValue()
	{
		for(int i=0; i<minPathValue.length; i++)
		{
			System.out.println(source+" 到 "+i+" 的最短距离："+minPathValue[i]);
		}
	}
	
	public void displayMinPath()
	{
		if(from == null) return;
		for(int i=0; i<from.length; i++)
		{
			List<Integer> vertexs = from[i];
			if(vertexs != null && vertexs.size()>0)
			{
				System.out.println(source+"到"+i+"的最短路径：");
				for(Integer vertex : vertexs)
				{
					System.out.print(vertex+" -> ");
				}
				System.out.println("");
			}
		}
	}
	
	public SingleSourceShortPath(IWeightGraph<T> G,int source)
	{
		this.G = G;
		this.vertex = G.getVertex();
		if(source < 0 || source >= this.vertex)
		{
			this.source = 0;
		}else{
			this.source = source;
		}
		edges = new ArrayList<Edge<T>>();
		minPathValue = new double[this.vertex];
		from = new ArrayList[this.vertex];
		marked = new boolean[this.vertex];
		
		for(int i=0; i<this.vertex ; i++)
		{
			marked[i] = false;
			from[i] = new ArrayList<Integer>();;
			minPathValue[i] =-1;
		}
		minPathValue[source] = 0;	//把源的 最短路径值 置为0
		from[source].add(source);	//把源的 最短路径置为自身
		mark(source);	//对源进行标记动作				
		while(!edges.isEmpty() && edges.size() > 0)
		{
			int recentlyVertex = getRecentlyDistance();//获得离源点最近的顶点
			mark(recentlyVertex);		//标记它
		}
	}
	
	//标记动作，核心处理逻辑
	private void mark(int vertex) 
	{
		if(marked[vertex]) return;
		marked[vertex] = true;		//标记它，并获得该顶点的邻接边		
		List<Edge<T>> edges = this.G.adjacentEdge(vertex);
		if(edges == null || edges.isEmpty()) return;
		
		for(Edge<T> edge : edges)
		{
			if(isCrossEdge(edge))//如果是横切边 则加入，否则剔除
			{
				int index = edge.getOther(vertex);//通过边的一个顶点得到边的另一个顶点
				if(minPathValue[index] == -1)
				{			//如果最短路径值 为-1，则未开包，直接放值
					minPathValue[index] = minPathValue[vertex] + (Double)(edge.getWeight());
				}else{	//苦已开包，则需判断更新
					double newWeight = minPathValue[vertex] + (Double)(edge.getWeight());
					if(newWeight < minPathValue[index])
					{
						minPathValue[index] = newWeight;
					}
				}
				this.edges.add(edge);	
				if(from[index] == null || from[index].isEmpty())
				{	//最短路径 ，若未开包，则直接放值
					from[index].addAll(from[vertex]);
					from[index].add(index);
				}else{//反之 需要清除之前路径，重新来过
					from[index].clear();
					from[index].addAll(from[vertex]);
					from[index].add(index);
				}
			}else{	
				this.edges.remove(edge);
			}
		}

	}
	// 从最短路径值  数组里获得哪个顶点 距离源点 最近
	public int getRecentlyDistance()
	{
		int recently = -1;
		double standard = Double.MAX_VALUE;
		for(int i=0; i<this.minPathValue.length; i++)
		{
			if(this.minPathValue[i] == -1) continue;
			if(marked[i]) continue;
			if(this.minPathValue[i] < standard)
			{
				recently = i;
				standard = this.minPathValue[i];
			}
		}
		return recently;
	}
	
	//判断横切边
	private boolean isCrossEdge(Edge<T> edge) 
	{				//边的两个顶点，一个是已标记的一个是未标记的，就是横切边
		if(marked[edge.getV()] != marked[edge.getW()])
		{
			return true;
		}
		return false;
	}
}
