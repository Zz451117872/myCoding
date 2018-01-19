package com.dataStructure.graph.weightGraph.shortPath;

import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IWeightGraph;
import com.dataStructure.heap.impl.IndexMinHeap;

public class SingleSourceShortPath2 {
	
	private IWeightGraph<Double> G;
	private int source;	
	private double[] minPathValue;
	private int vertex;	
	private boolean[] marked;
	private Edge<Double>[] from;
	
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
		minPathValue[this.source] = 0;
				
		IndexMinHeap<Double> heap = new IndexMinHeap<Double>(this.vertex);
		heap.push(minPathValue[this.source], this.source);
		while(!heap.isEmpty())
		{
			int index = heap.popIndex();
			marked[index] = true;
			
			List<Edge<Double>> edges = G.adjacentEdge(index);
			if(edges != null && !edges.isEmpty())
			{
				for(int i=0; i<edges.size(); i++)
				{
					Edge<Double> edge = edges.get(i);
					int w = edge.getOther(index);
					if(!marked[w])
					{
						if(from[w] == null || (minPathValue[index]+edge.getWeight()) < minPathValue[w])
						{
							minPathValue[w] = minPathValue[index]+edge.getWeight();
							from[w] = edge;
							if(heap.contains(w))
							{
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
