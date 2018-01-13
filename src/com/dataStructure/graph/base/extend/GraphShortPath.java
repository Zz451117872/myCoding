package com.dataStructure.graph.base.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import com.dataStructure.graph.base.inter.IGraph;

public class GraphShortPath {

	private IGraph G;			//图
	private boolean[] visited;	//该顶点是否被访问
	private int[] from;			//该顶点从哪来
	private int[] interval ;	//该顶点距离源的距离
	private int source;			//源
	
	public GraphShortPath(IGraph G,int source)
	{
		this.G = G;
		this.source = source;
		visited = new boolean[this.G.getVertex()];
		from = new int[this.G.getVertex()];
		interval = new int[this.G.getVertex()];
		for(int i=0; i<this.G.getVertex(); i++)
		{
			visited[i] = false;
			from[i] = -1;
			interval[i] = -1;
		}
		
		bds(this.source);  //广度优先遍历
	}
	
	private void bds(int vertex) 
	{
		if(vertex < 0 || vertex > this.G.getVertex()) return;
				
		interval[vertex] = 0;
		
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		queue.add(vertex);  //把源 顶点 加入队列
		while(!queue.isEmpty())
		{
			int point = queue.poll();	//从队列中取出顶点，然后把该顶点的 邻接顶点 加入队列
			visited[point] = true;//当出队时，将访问状态置为TRUE
			List<Integer> adjVertexs = this.G.adjacentEdge(point);
			if(adjVertexs == null || adjVertexs.size() < 1) return;
			
			for(Integer adjVertex : adjVertexs)
			{
				if(!visited[adjVertex])
				{
					//在入队前，更新该顶点 是从哪来的，距离源 的距离
					interval[adjVertex] = interval[point] +1;
					from[adjVertex] = point;
					try {
						queue.put(adjVertex);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

	public boolean hasPath(int vertex)
	{
		return interval[vertex] != -1;
	}
	
	public List<Integer> path(int vertex)
	{
		if(!hasPath(vertex)) return null;
		
		Stack<Integer> stack = new Stack<Integer>();
		int point = vertex;
		while(from[point] != -1)
		{
			stack.push(point);
			point = from[point];
		}
		stack.push(point);
		
		List<Integer> sourceToVertex = new ArrayList<Integer>();
		while(!stack.isEmpty())
		{
			sourceToVertex.add(stack.pop());
		}
		return sourceToVertex;
	}
	
	public int shortPath(int vertex)
	{
		if(!hasPath(vertex)) return -1;
		return interval[vertex];
	}
	
	public void showPath(int vertex)
	{
		System.out.println(this.source+"到"+vertex+"的最短路径："+shortPath(vertex));
		List<Integer> sourceToVertex = path(vertex);
		if(sourceToVertex != null)
		{
			for(Integer point : sourceToVertex)
			{
				System.out.print(point+" ->");
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
	
	
	
}
