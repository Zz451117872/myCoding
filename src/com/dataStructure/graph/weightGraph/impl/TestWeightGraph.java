package com.dataStructure.graph.weightGraph.impl;

import java.util.List;
import java.util.Random;

public class TestWeightGraph {
	
	public static void main(String[] str)
	{
		int vertex = 20;
		Random r = new Random();
		DenseGraph denseGraph = new DenseGraph(vertex,false);
		SparseGraph sparseGraph = new SparseGraph(vertex,false);
		
		for(int i=0; i<50; i++)
		{
			int v = r.nextInt(vertex);
			int w = r.nextInt(vertex);
			double weight = r.nextDouble();
			denseGraph.addEdge(v,w,weight);
			sparseGraph.addEdge(v,w,weight);
		}
		
		System.out.println("denseGraph:"+denseGraph.getEdge());
		System.out.println("sparseGraph:"+sparseGraph.getEdge());
		for(int i=0; i<40; i++)
		{
			int v = r.nextInt(vertex);
			int w = r.nextInt(vertex);
			System.out.println("denseGraph:"+v+" : "+w+"="+denseGraph.hasEdge(v, w));
			System.out.println("sparseGraph:"+v+" : "+w+"="+sparseGraph.hasEdge(v, w));
			
			
		}
	}
}
