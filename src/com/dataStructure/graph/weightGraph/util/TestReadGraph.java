package com.dataStructure.graph.weightGraph.util;

import com.dataStructure.graph.weightGraph.inter.IGraph;

public class TestReadGraph {

	public static void main(String[] str)
	{
		String readPath = System.getProperty("user.dir")+"/src/com/dataStructure/graph/weightGraph/graphFile/graph1";
		String writerPath = System.getProperty("user.dir")+"/src/com/dataStructure/graph/weightGraph/graphFile/graph2";
		
		IGraph graph = ReadGraph.readGraph(readPath, true);
	
		ReadGraph.writeGraph(graph, writerPath);
	}
}
