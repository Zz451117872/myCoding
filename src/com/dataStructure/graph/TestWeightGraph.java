package com.dataStructure.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dataStructure.graph.util.ReadWeightGraph;
import com.dataStructure.graph.weightGraph.IWeightGraph;
import com.dataStructure.graph.weightGraph.shortPath.SingleSourceShortPath;
import com.dataStructure.graph.weightGraph.shortPath.SingleSourceShortPath2;

public class TestWeightGraph {

	static String path = System.getProperty("user.dir")+"/src/com/dataStructure/graph/graphFile/weightGraph1";
	static ReadWeightGraph<Double> readGraph = null;
	static IWeightGraph<Double> g = null;
	
	@BeforeClass
	public static void before()
	{
		readGraph = new ReadWeightGraph<Double>();
	    g = readGraph.readGraph(path, false);
		g.matrix();
	}
	
	@Test
	public void singleSourceShortPath()
	{
		SingleSourceShortPath<Double> G =new  SingleSourceShortPath<Double>(g,0);
		G.displayMinPathValue();
		//G.displayMinPath();
		System.out.println("===========");
		SingleSourceShortPath2 G2 = new SingleSourceShortPath2(g,0);
		G2.printMinPath();
	}
	
	@Test
	public void minProductTree()
	{
		
	}
}
