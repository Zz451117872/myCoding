package com.dataStructure.graph.entry;

public class GraphNode<T extends Comparable<T>> {
	private Edge<T> edge;
	private GraphNode<T> next;
	
	public GraphNode(Edge<T> edge)
	{
		this.edge = edge;
		this.next = null;
	}

	public void display() 
	{
		GraphNode<T> node = this;
		while( node != null)
		{
			System.out.println(node.getEdge().toString());
			node = node.getNext();
		}
	}

	public boolean contains(int vStart,int vEnd) 
	{
		GraphNode<T> node = this;
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
		GraphNode<T> node = this;
		while(node.getNext() != null)
		{
			node = node.getNext();
		}
		node.setNext(new GraphNode(edge));
	}



	public Edge<T> getEdge() {
		return edge;
	}

	public void setEdge(Edge<T> edge) {
		this.edge = edge;
	}

	public GraphNode<T> getNext() {
		return next;
	}

	public void setNext(GraphNode<T> next) {
		this.next = next;
	}
	
}
