package com.dataStructure.graph.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import com.dataStructure.graph.entry.Edge;
import com.dataStructure.graph.weightGraph.IGraph;
import com.dataStructure.graph.weightGraph.impl.DenseGraph;
import com.dataStructure.graph.weightGraph.impl.SparseGraph;

public class ReadGraph {
	
	public static void writeGraph(IGraph graph,String path)
	{
		File file = new File(path);
		if(!file.exists() || file.isDirectory())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("创建文件失败");
			}
		}
		
		BufferedWriter writer = null;		
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			int V = graph.getVertex();
			int E = graph.getEdge();
			List<Edge<Double>> edges = graph.getEdges();
			String data = V+" "+E;
			writer.write(data);
			writer.newLine();
			if(edges != null && edges.size() > 0)
			{
				for(Edge edge : edges)
				{
					data = edge.getV() +" "+edge.getW()+" "+edge.getWeight();
					writer.write(data);
					writer.newLine();
				}
			}
			writer.flush();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("写入文件异常");
		}finally{
			if(writer != null)
			{
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		 
		
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
				double weight = -1;
				try{
					source = Integer.parseInt(vertexArr[0]);
					dest = Integer.parseInt(vertexArr[1]);
					weight = Double.parseDouble(vertexArr[2]);
				}catch(Exception e){
					System.out.println("解析文件异常");
					return null;
				}
				if(source < 0 || source >= graph.getVertex()) return null;
				if(dest < 0 || dest >= graph.getVertex()) return null;
				if(weight < 0) return null;
				
				graph.addEdge(source, dest,weight);
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
