package com.dataStructure.graph.baseGraph.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.dataStructure.graph.baseGraph.IBaseGraph;

//
public class GraphPath {

<<<<<<< HEAD
	private IBaseGraph G;		//图
=======
	private IBaseGraph G;			//图
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	private boolean[] visited;	//该顶点是否被访问
	private int[] from;			//顶点的上一个顶点
	private int[] id;			//保存顶点的踪迹
	private int source;			//源
	private int component;		//连通分量
	
	public GraphPath(IBaseGraph G,int source)
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
		dps(this.source); 
	}
	//深度优先
	private void dps(int vertex)
	{
		if(visited[vertex]) return;
		visited[vertex] = true;
		id[vertex] = this.component; //更新连通分量
		//获取顶点的邻接点
		List<Integer> vertexs = this.G.adjacentEdge(vertex);
		for(Integer adjVertex : vertexs)
		{		
			if(!visited[adjVertex])
			{
				from[adjVertex] = vertex;	//更新踪迹
				dps(adjVertex);
			}
		}
	}
	//判断源与指定顶点是否有路径
	public boolean hasPath(int vertex)
	{
		return id[this.source] == id[vertex];
	}
	//获取源与指定顶点的路径
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
	//打印路径
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
