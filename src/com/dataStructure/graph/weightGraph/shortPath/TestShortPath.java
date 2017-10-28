package com.dataStructure.graph.weightGraph.shortPath;

import java.util.Random;

import com.dataStructure.graph.weightGraph.Edge;
import com.dataStructure.graph.weightGraph.impl.DenseGraph;
import com.dataStructure.graph.weightGraph.impl.SparseGraph;
import com.dataStructure.graph.weightGraph.util.BigDecimalUtil;
import com.dataStructure.graph.weightGraph.util.ReadGraph;

public class TestShortPath {

	public static void main(String[] str)
	{

		String path = System.getProperty("user.dir")+"/src/com/dataStructure/graph/weightGraph/graphFile/graph1";
		DenseGraph denseGraph = (DenseGraph) ReadGraph.readGraph(path, true);
		SparseGraph sparseGraph = (SparseGraph) ReadGraph.readGraph(path, false);
		
		SingleSourceShortPath denseSingle = new SingleSourceShortPath(denseGraph,0);
//		SingleSourceShortPath sparseSingle = new SingleSourceShortPath(sparseGraph,0);
//
		denseSingle.displayMinPathValue();
		denseSingle.displayMinPath();
//		System.out.println("----------------------------------------------");
//		sparseSingle.displayMinPathValue();
	}
}
