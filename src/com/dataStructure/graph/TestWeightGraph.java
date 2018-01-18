package com.dataStructure.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dataStructure.graph.util.ReadGraph;
import com.dataStructure.graph.weightGraph.IGraph;
import com.dataStructure.graph.weightGraph.shortPath.SingleSourceShortPath;

public class TestWeightGraph {

	static String path = System.getProperty("user.dir")+"/src/com/dataStructure/graph/weightGraph/graphFile/graph1";
	static ReadGraph<Double> readGraph = null;
	static IGraph<Double> g = null;
	
	@BeforeClass
	public static void before()
	{
		readGraph = new ReadGraph<Double>();
	    g = readGraph.readGraph(path, false);
		g.matrix();
	}
	
	@Test
	public void singleSourceShortPath()
	{
		SingleSourceShortPath<Double> G =new  SingleSourceShortPath<Double>(g,0);
		G.displayMinPathValue();
		G.displayMinPath();
	}
	
	@Test
	public void minProductTree()
	{
		
	}
}
