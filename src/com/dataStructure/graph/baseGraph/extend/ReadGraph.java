package com.dataStructure.graph.baseGraph.extend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.dataStructure.graph.baseGraph.IGraph;
import com.dataStructure.graph.baseGraph.impl.DenseGraph;
import com.dataStructure.graph.baseGraph.impl.SparseGraph;

public class ReadGraph {
	public static void writeGraph(IGraph graph,String path)
	{
		
	}
	
	public static IGraph readGraph(String path,boolean dense)
	{
		File file = new File(path);
		if(!file.exists() || file.isDirectory())
		{
			System.out.println(path+" ERROR");
			return null;
		}
		BufferedReader reader = null;
		IGraph graph = null;
		
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String vertexAndEdgeStr = reader.readLine();
			if(!"".equals(vertexAndEdgeStr) && null != vertexAndEdgeStr)
			{
				String[] vertexAndEdge = vertexAndEdgeStr.split(" ");
				int vertex = 0;
				try{
					vertex = Integer.parseInt(vertexAndEdge[0]);
				}catch(Exception e){
					vertex = 0;
				}				 
				if(vertex <= 0) return null;
				
				if(dense)
				{
					graph = new DenseGraph(vertex,false);
				}else{
					graph = new SparseGraph(vertex,false);
				}			
			}
			
			String edgeStr = reader.readLine();
			while(!"".equals(edgeStr) && null != edgeStr)
			{
				String[] vertexArr = edgeStr.split(" ");
				int source = -1;
				int dest = -1;
				try{
					source = Integer.parseInt(vertexArr[0]);
					dest = Integer.parseInt(vertexArr[1]);
				}catch(Exception e){
					System.out.println("�����ļ��쳣");
					return null;
				}
				if(source < 0 || source >= graph.getVertex()) return null;
				if(dest < 0 || dest >= graph.getVertex()) return null;
				
				graph.addEdge(source, dest);
				edgeStr = reader.readLine();
			}
			return graph;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			if(reader != null)
			{
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		 
	}
}
