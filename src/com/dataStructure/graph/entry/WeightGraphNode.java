package com.dataStructure.graph.entry;

public class WeightGraphNode<T extends Comparable<T>> {
	private Edge<T> edge;
	private WeightGraphNode<T> next;
	
	public WeightGraphNode(Edge<T> edge)
	{
		this.edge = edge;
		this.next = null;
	}

	public void display() 
	{
		WeightGraphNode<T> node = this;
		while( node != null)
		{
			System.out.println(node.getEdge().toString());
			node = node.getNext();
		}
	}

	public boolean contains(int vStart,int vEnd) 
	{
		WeightGraphNode<T> node = this;
		while( node != null)
		{
			if(node.getEdge().getOther(vStart) == vEnd)
			{
				return true;
			}
			node = node.getNext();
		}
		return false;
	}

	public void addNode(Edge<T> edge) 
	{
		WeightGraphNode<T> node = this;
		while(node.getNext() != null)
		{
			node = node.getNext();
		}
		node.setNext(new WeightGraphNode(edge));
	}



	public Edge<T> getEdge() {
		return edge;
	}

	public void setEdge(Edge<T> edge) {
		this.edge = edge;
	}

	public WeightGraphNode<T> getNext() {
		return next;
	}

	public void setNext(WeightGraphNode<T> next) {
		this.next = next;
	}
	
}
