package com.dataStructure.graph.baseGraph.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import com.dataStructure.graph.baseGraph.IBaseGraph;

/*
<<<<<<< HEAD
 * 单源最短路径
 */
public class GraphShortPath {

	private IBaseGraph G;		//图
=======
 * 单源最智路径
 */
public class GraphShortPath {

	private IBaseGraph G;			//图
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	private boolean[] visited;	//该顶点是否被访问
	private int[] from;			//顶点的踪迹
	private int[] interval ;	//源距离该顶点的最短距离
	private int source;			//源
	
	public GraphShortPath(IBaseGraph G,int source)
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
		
		bds(this.source); 
	}
<<<<<<< HEAD
	
=======
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	//广度优先
	private void bds(int vertex) 
	{
		if(vertex < 0 || vertex >= this.G.getVertex()) return;
				
		interval[vertex] = 0;
		
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		queue.add(vertex);  //将顶点压入队列
		while(!queue.isEmpty())
		{
			int point = queue.poll();	//将顶点弹出队列
			visited[point] = true;//设置该顶点的访问属性为true
			List<Integer> adjVertexs = this.G.adjacentEdge(point);//获取该顶点的邻接点
			if(adjVertexs == null || adjVertexs.size() < 1) return;
			
			for(Integer adjVertex : adjVertexs)
			{
				if(!visited[adjVertex])
				{
					//更新该顶点距离源点的距离 为 该顶点的上一个顶点的距离值加1
					interval[adjVertex] = interval[point] +1;
					from[adjVertex] = point;//更新该顶点的足迹
					try {
						queue.put(adjVertex);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

<<<<<<< HEAD
	//判断目标点 与 源点 是否有路径
=======
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	public boolean hasPath(int vertex)
	{
		return interval[vertex] != -1;
	}
	
<<<<<<< HEAD
	//返回源点 到 目标点 经过的点
=======
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
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
	
<<<<<<< HEAD
	//返回 源点 到 目标点 的最短路径值
=======
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
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
