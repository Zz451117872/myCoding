package com.dataStructure.graph.weightGraph;

public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>>{
	private int v;
	private int w;
	private T weight;
	private boolean directed;
	
	public Edge(int v,int w,T weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
		this.directed = false;
	}

	
	@Override
	public String toString() {
		return this.v+" : "+this.w+" : "+this.weight;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Edge)) return false;
		if(this == obj) return true;
		
		Edge edge = (Edge)obj;
		if((this.v == edge.v && this.w == edge.w) || (this.w == edge.v && this.v == edge.w))
		{
			return true;
		}
		return false;
	}


	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public T getWeight() {
		return weight;
	}

	public void setWeight(T weight) {
		this.weight = weight;
	}
	
	public int getOther(int vertex)
	{
		if(vertex == this.v || vertex == this.w)
		{
			return vertex == this.v ? this.w : this.v;
		}
		return -100;
	}


	@Override
	public int compareTo(Edge edge) {
		return this.weight.compareTo((T) edge.weight);
	}
}
