package com.dataStructure.graph.base;

import java.util.Random;

import com.dataStructure.graph.base.extend.GraphComponent;
import com.dataStructure.graph.base.extend.GraphPath;
import com.dataStructure.graph.base.extend.GraphShortPath;
import com.dataStructure.graph.base.impl.DenseGraph;
import com.dataStructure.graph.base.inter.IGraph;
import com.dataStructure.graph.base.util.ReadGraph;

public class TestGraph {

	public static void main(String[] str)
	{
		String graph1Path = System.getProperty("user.dir")+"/src/com/dataStructure/graph/base/graphFile/graph1";
//		IGraph denseGraph = ReadGraph.readGraph(graph1Path, true);
		IGraph sparseGraph = ReadGraph.readGraph(graph1Path, false);
		
		Random r = new Random();
		int source = r.nextInt(sparseGraph.getVertex()); 
		int vertex = r.nextInt(sparseGraph.getVertex());
		testGraphPath(sparseGraph,source,vertex);		
		testGraphShortPath(sparseGraph,source,vertex);
	}

	private static void testGraphShortPath(IGraph denseGraph, int source, int vertex) 
	{
		GraphShortPath GraphShortPath = new GraphShortPath(denseGraph,source);
		System.out.println(GraphShortPath.hasPath(vertex));
		GraphShortPath.showPath(vertex);
		System.out.println("----------------");
	}

	private static void testGraphPath(IGraph denseGraph, int source,int vertex) 
	{
		GraphPath graphPath = new GraphPath(denseGraph,source);
		System.out.println(graphPath.hasPath(vertex));
		graphPath.showPath(vertex);
		System.out.println("----------------");
	}

	private static void testGraphComponent(IGraph denseGraph,int start,int end) 
	{						
		GraphComponent gc = new GraphComponent(denseGraph);
		System.out.println("component:"+gc.getComponent());
		System.out.println(start+":"+end+"->"+gc.isConnection(start, end));		
		System.out.println("------------------------");
	}
}
