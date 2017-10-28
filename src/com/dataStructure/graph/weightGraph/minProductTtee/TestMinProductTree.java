package com.dataStructure.graph.weightGraph.minProductTtee;

import java.util.List;
import java.util.Random;

import com.dataStructure.graph.weightGraph.Edge;
import com.dataStructure.graph.weightGraph.impl.DenseGraph;
import com.dataStructure.graph.weightGraph.impl.SparseGraph;
import com.dataStructure.graph.weightGraph.util.BigDecimalUtil;
import com.dataStructure.graph.weightGraph.util.MergeSortEdge;

public class TestMinProductTree {
	
	public static void main(String[] str)
	{
		int vertex = 10;
		Random r = new Random();
		DenseGraph denseGraph = new DenseGraph(vertex,false);
		SparseGraph sparseGraph = new SparseGraph(vertex,false);
		
		for(int i=0; i< 3000; i++)
		{
			int v = r.nextInt(vertex);
			int w = r.nextInt(vertex);
			double weight =BigDecimalUtil.retainSeveralDecimals(2, r.nextDouble());
			denseGraph.addEdge(v, w, weight);
			sparseGraph.addEdge(v, w, weight);
		}
		
		
//		Prim densePrim = new Prim(denseGraph);
//		Prim sparsePrim = new Prim(sparseGraph);
//		Kruskal denseKruskal = new Kruskal(denseGraph);
//		Kruskal sparseKruskal = new Kruskal(sparseGraph);
		PrimUpgrade denseprimUpgrade = new PrimUpgrade(denseGraph);
		PrimUpgrade sparseprimUpgrade = new PrimUpgrade(sparseGraph);
		
		System.out.println(denseGraph.getEdge());
		System.out.println("denseprimUpgrade:"+denseprimUpgrade.getMinWeight());
		denseprimUpgrade.displayMinProductTree();
		
		System.out.println("-------------------------------");
		System.out.println(sparseGraph.getEdge());
		System.out.println("sparseprimUpgrade:"+sparseprimUpgrade.getMinWeight());
		sparseprimUpgrade.displayMinProductTree();
	}
}
