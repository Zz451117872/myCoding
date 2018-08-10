package com.dataStructure.graph;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dataStructure.graph.baseGraph.extend.GraphComponent;
import com.dataStructure.graph.baseGraph.extend.GraphPath;
import com.dataStructure.graph.baseGraph.extend.GraphShortPath;
import com.dataStructure.graph.baseGraph.impl.DenseBaseGraph;
import com.dataStructure.graph.baseGraph.impl.SparseBaseGraph;
import com.dataStructure.graph.util.ReadBaseGraph;



public class TestBaseGraph {

	static Random random = new Random();
	static DenseBaseGraph dg = null;
	static SparseBaseGraph sg = null;
	static String path = System.getProperty("user.dir")+"/src/com/dataStructure/graph/graphFile/baseGraph1";
	
	@BeforeClass
	public static void before()
	{
		dg = (DenseBaseGraph) ReadBaseGraph.readGraph(path, true);				
		sg = (SparseBaseGraph) ReadBaseGraph.readGraph(path, false);				
	}
		
	@Test
	public void graphComponent()
	{
		System.out.println("-graphComponent----------------");
		dg.matrix();
		int vertex = dg.getVertex();
		GraphComponent g = new GraphComponent(dg);
		for(int i=0; i<10; i++)
		{
			int v1 = random.nextInt(vertex);
			int v2 = random.nextInt(vertex);
			if(v1 != v2){
				System.out.println(v1+":"+v2+"->"+g.isConnection(v1, v2));
			}
		}
		System.out.println("Component:"+g.getComponent());
		System.out.println("-------------------------");
	}
	
	@Test
	public void graphShortPath()
	{
		System.out.println("-graphShortPath-----------------------");
		sg.matrix();
		GraphShortPath g1 = new GraphShortPath(sg,0);
		GraphPath g2 = new GraphPath(sg,0);
		int vertex = sg.getVertex();
		for(int i=0; i<10; i++)
		{
			int v = random.nextInt(vertex);
			if(g1.hasPath(v))
			{
				System.out.println("距离:"+g1.shortPath(v));
				g1.showPath(v);
			}
			if(g2.hasPath(v))
			{
				g2.showPath(v);
			}
		}
		System.out.println("-------------------------");
	}
}
