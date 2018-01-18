package com.dataStructure.graph.base.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.dataStructure.graph.base.IGraph;

//
public class GraphPath {

	private IGraph G;			//
	private boolean[] visited;	//
	private int[] from;			//
	private int[] id;			//
	private int source;			//
	private int component;		//
	
	public GraphPath(IGraph G,int source)
	{
		this.G = G;
		this.source = source;
		this.component = 0;
		visited = new boolean[this.G.getVertex()];
		id = new int[this.G.getVertex()];
		from = new int[this.G.getVertex()];
		for(int i=0; i<this.G.getVertex(); i++)
		{
			visited[i] = false;
			id[i] = -1;
			from[i] = -1;
		}
		dps(this.source); //
	}
	
	private void dps(int vertex)
	{
		if(visited[vertex]) return;
		visited[vertex] = true;
		id[vertex] = this.component; //
		//
		List<Integer> vertexs = this.G.adjacentEdge(vertex);
		for(Integer adjVertex : vertexs)
		{		
			if(!visited[adjVertex])
			{
				from[adjVertex] = vertex;	//
				dps(adjVertex);
			}
		}
	}
	//
	public boolean hasPath(int vertex)
	{
		return id[this.source] == id[vertex];
	}
	//
	public List<Integer> path(int vertex)
	{
		if(!hasPath(vertex)) return null;
		
		Stack<Integer> vertexStack = new Stack<Integer>();
		int point = vertex;
		while(from[point] != -1)
		{
			vertexStack.push(point);
			point = from[point];
		}
		vertexStack.push(point);
			
		List<Integer> sourceToVertex = new ArrayList<Integer>();
		while(!vertexStack.isEmpty())
		{
			sourceToVertex.add(vertexStack.pop());
		}
		return sourceToVertex;
	}
	//
	public void showPath(int vertex)
	{
		System.out.println(this.source+"到"+vertex+"路径为：");
		List<Integer> sourceToVertex = path(vertex);
		if(sourceToVertex != null)
		{
			for(Integer middle : sourceToVertex)
			{
				System.out.print(middle+"->");
			}
		}
		System.out.println("");
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getComponent() {
		return component;
	}

	public void setComponent(int component) {
		this.component = component;
	}
	
	
	
}
