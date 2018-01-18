package com.dataStructure.graph.base.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import com.dataStructure.graph.base.IGraph;

public class GraphShortPath {

	private IGraph G;			//ͼ
	private boolean[] visited;	//�ö����Ƿ񱻷���
	private int[] from;			//�ö��������
	private int[] interval ;	//�ö������Դ�ľ���
	private int source;			//Դ
	
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
		
		bds(this.source);  //������ȱ���
	}
	
	private void bds(int vertex) 
	{
		if(vertex < 0 || vertex > this.G.getVertex()) return;
				
		interval[vertex] = 0;
		
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		queue.add(vertex);  //��Դ ���� �������
		while(!queue.isEmpty())
		{
			int point = queue.poll();	//�Ӷ�����ȡ�����㣬Ȼ��Ѹö���� �ڽӶ��� �������
			visited[point] = true;//������ʱ��������״̬��ΪTRUE
			List<Integer> adjVertexs = this.G.adjacentEdge(point);
			if(adjVertexs == null || adjVertexs.size() < 1) return;
			
			for(Integer adjVertex : adjVertexs)
			{
				if(!visited[adjVertex])
				{
					//�����ǰ�����¸ö��� �Ǵ������ģ�����Դ �ľ���
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
		System.out.println(this.source+"��"+vertex+"�����·����"+shortPath(vertex));
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
