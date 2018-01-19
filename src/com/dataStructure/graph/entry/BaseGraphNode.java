package com.dataStructure.graph.entry;


public class BaseGraphNode {
	private int data; //顶点值
	private BaseGraphNode next;
	
	public BaseGraphNode(int data)
	{
		this.data = data;
		this.next = null;
	}

	public void display() 
	{
		BaseGraphNode node = this;
		while( node != null)
		{
			System.out.print(node.getData()+" ");
			node = node.getNext();
		}
		System.out.println("");
	}

	public boolean contains(int vEnd) 
	{
		BaseGraphNode node = this;
		while( node != null)
		{
			if(node.getData() == vEnd)
			{
				return true;
			}
			node = node.getNext();
		}
		return false;
	}

	public void addNode(int vEnd) 
	{
		BaseGraphNode node = this;
		while(node.getNext() != null)
		{
			node = node.getNext();
		}
		node.setNext(new BaseGraphNode(vEnd));
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BaseGraphNode getNext() {
		return next;
	}

	public void setNext(BaseGraphNode next) {
		this.next = next;
	}
}
