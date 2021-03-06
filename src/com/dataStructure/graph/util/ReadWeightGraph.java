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
import com.dataStructure.graph.weightGraph.IWeightGraph;
import com.dataStructure.graph.weightGraph.impl.DenseWeightGraph;
import com.dataStructure.graph.weightGraph.impl.SparseWeightGraph;

public class ReadWeightGraph<T extends Comparable<T>> {
	
	public  void writeGraph(IWeightGraph<T> graph,String path)
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
			List<Edge<T>> edges = graph.getEdges();
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
	
	public   IWeightGraph readGraph(String path, boolean dense)
	{
		File file = new File(path);
		if(!file.exists() || file.isDirectory())
		{
			System.out.println(path+" ERROR");
			return null;
		}
		BufferedReader reader = null;
		IWeightGraph<T> graph = null;
		
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
					graph = new DenseWeightGraph(vertex,false);
				}else{
					graph = new SparseWeightGraph(vertex,false);
				}			
			}
			
			String edgeStr = reader.readLine();
			while(!"".equals(edgeStr) && null != edgeStr)
			{
				String[] vertexArr = edgeStr.split(" ");
				int source = -1;
				int dest = -1;
				T weight = null;
				try{
					source = Integer.parseInt(vertexArr[0]);
					dest = Integer.parseInt(vertexArr[1]);
					weight = getT(vertexArr[2].trim());
				}catch(Exception e){
					System.out.println("解析文件异常");
					return null;
				}
				if(source < 0 || source >= graph.getVertex()) return null;
				if(dest < 0 || dest >= graph.getVertex()) return null;
				if(weight == null) return null;
				
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

	private T getT(String str) {
		T t = (T) new Double(str);
		return t;
	}
}
