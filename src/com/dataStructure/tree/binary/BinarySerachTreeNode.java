package com.dataStructure.tree.binary;

import java.io.Serializable;

public class BinarySerachTreeNode<T extends Comparable<T>> implements Serializable {
	public T key;
	public BinarySerachTreeNode<T> left;
	public BinarySerachTreeNode<T> right;
	public BinarySerachTreeNode<T> parent;
	public int count = 1;
	
	//计算当前树 有多少层
	public int getHierarchy()
	{		
		return getLog2(2,this.count);
	}
	
	private int getLog2(int base,int count) {
		
		return (int) (Math.ceil(Math.log(count) / Math.log(base) ) );
	}
	
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

	
	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public BinarySerachTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinarySerachTreeNode<T> left) {
		this.left = left;
	}

	public BinarySerachTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinarySerachTreeNode<T> right) {
		this.right = right;
	}

	public BinarySerachTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(BinarySerachTreeNode<T> parent) {
		this.parent = parent;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
