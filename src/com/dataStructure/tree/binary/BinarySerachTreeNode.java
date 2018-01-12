package com.dataStructure.tree.binary;

import java.io.Serializable;

public class BinarySerachTreeNode<T extends Comparable<T>> implements Serializable {
	public T key;
	public BinarySerachTreeNode<T> left;
	public BinarySerachTreeNode<T> right;
	public BinarySerachTreeNode<T> parent;
	
	public BinarySerachTreeNode(){}
	
	public BinarySerachTreeNode(T key)
	{
		this.key = key;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public BinarySerachTreeNode(BinarySerachTreeNode parent,T key)
	{
		this.parent = parent;
		this.key = key;
		this.left = null;
		this.right = null;
	}
}
