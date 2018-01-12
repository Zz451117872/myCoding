package com.dataStructure.tree.binary;

public class RBNode<T extends Comparable<T>> {
	public T key = null;
	public RBNode<T> left = null;
	public RBNode<T> right = null;
	public RBNode<T> parent = null;
	public String color = null;
	
	public RBNode(){}
	public RBNode(String color)
	{
		this.color = color;
	}
	public RBNode(T key)
	{
		this.key = key;
		this.color = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	public RBNode(T key,String color,RBNode parent)
	{
		this.key = key;
		this.color = color;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}
}
