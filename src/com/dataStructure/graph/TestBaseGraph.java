package com.dataStructure.graph;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dataStructure.graph.baseGraph.extend.GraphComponent;
import com.dataStructure.graph.baseGraph.extend.GraphShortPath;
import com.dataStructure.graph.baseGraph.impl.DenseGraph;
import com.dataStructure.graph.baseGraph.impl.SparseGraph;



public class TestBaseGraph {

	static Random random = new Random();
	static DenseGraph dg = null;
	static SparseGraph sg = null;
	
	@BeforeClass
	public static void before()
	{
		dg = new DenseGraph(10,false);
		int vertex = dg.getVertex();
		for(int i=0; i<dg.getVertex()*2; i++)
		{
			dg.addEdge(random.nextInt(vertex), random.nextInt(vertex));
		}
		
		sg = new SparseGraph(10,false);
		vertex = sg.getVertex();
		for(int i=0; i<sg.getVertex()*2; i++)
		{
			sg.addEdge(random.nextInt(vertex), random.nextInt(vertex));
		}
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
		GraphShortPath g = new GraphShortPath(sg,0);
		int vertex = sg.getVertex();
		for(int i=0; i<10; i++)
		{
			int v = random.nextInt(vertex);
			if(g.hasPath(v))
			{
				System.out.println("距离:"+g.shortPath(v));
				g.showPath(v);
			}
		}
		System.out.println("-------------------------");
	}
}
