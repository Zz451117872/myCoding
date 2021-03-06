package com.dataStructure.graph.baseGraph.extend;

import java.util.List;

import com.dataStructure.graph.baseGraph.IBaseGraph;

/*
 * 图的连接分量
 */
public class GraphComponent {

	private IBaseGraph G;			//图
	private boolean[] visited;	//该顶点是否被访问
	private int[] id;			//该顶点对应的连通分量值
	private int component;		//连通分量
	
	public GraphComponent(IBaseGraph G)
	{
		this.G = G;
		visited = new boolean[G.getVertex()];
		id = new int[G.getVertex()];
		for(int i=0; i<visited.length; i++)
		{
			visited[i] = false;
			id[i] = -1;
		}
		//遍历图中所有顶点		
		for(int i=0; i<visited.length; i++)
		{
			if(!visited[i])
			{								
				dps(i); 
				this.component ++;
			}			
		}
	}
<<<<<<< HEAD
	//深度优先遍历，在同一个连通图内，则连通分量值一至
=======
	//深度优先遍历
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	private void dps(int vertex)
	{
		if(visited[vertex]) return;
		visited[vertex] = true;
		id[vertex] = this.component; 
		//获取邻接点
		List<Integer> adjVertexs = this.G.adjacentEdge(vertex);
		for(Integer adjVertex : adjVertexs)
		{			
			if(!visited[adjVertex])
			{				
				dps(adjVertex);
			}			
		}
	}
	//判断两个顶点是否是可连接的
	public boolean isConnection(int vStart,int vEnd)
	{
		return id[vStart] == id[vEnd];
	}

	public int getComponent() {
		return component;
	}

	public void setComponent(int component) {
		this.component = component;
	}
}
