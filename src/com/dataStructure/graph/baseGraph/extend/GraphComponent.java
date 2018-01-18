package com.dataStructure.graph.baseGraph.extend;

import java.util.List;

import com.dataStructure.graph.baseGraph.IGraph;

/*
 * 图的连接分量
 */
public class GraphComponent {

	private IGraph G;			//图
	private boolean[] visited;	//该顶点是否被访问
	private int[] id;			//该顶点对应的连通分量值
	private int component;		//连通分量
	
	public GraphComponent(IGraph G)
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
	//深度优先遍历
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
