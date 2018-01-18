package com.dataStructure.graph.base.extend;

import java.util.List;

import com.dataStructure.graph.base.IGraph;

//
public class GraphComponent {

	private IGraph G;			//
	private boolean[] visited;	//
	private int[] id;			//
	private int component;		//
	
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
				
		for(int i=0; i<visited.length; i++)
		{
			if(!visited[i])
			{								
				dps(i); 
				this.component ++;
			}			
		}
	}
	
	private void dps(int vertex)
	{
		if(visited[vertex]) return;
		visited[vertex] = true;
		id[vertex] = this.component; 
		//
		List<Integer> adjVertexs = this.G.adjacentEdge(vertex);
		for(Integer adjVertex : adjVertexs)
		{			
			if(!visited[adjVertex])
			{				
				dps(adjVertex);
			}			
		}
	}
	
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
