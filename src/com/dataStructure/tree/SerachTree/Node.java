package com.dataStructure.tree.SerachTree;

import java.util.Stack;

public class Node {
	private int data;
	private Node left;
	private Node right;
	private int count = 1;	
	private boolean isLeft ;
	
	//计算当前树 有多少层
	public int getHierarchy()
	{		
		return getLog2(2,this.count);
	}
	
	private int getLog2(int base,int count) {
		
		return (int) (Math.ceil(Math.log(count) / Math.log(base) ) );
	}

	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	
	
	public void setCount(int count) {
		this.count = count;
	}

	public Node(int data, Node left, Node right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public Node(){}
	
	//得到该树的节点数
	public int getCount()
	{
		return this.count;
	}
	
	//添加节点
	public void addNode(Node node) {
		if(node == null) return;
		if(this.data < node.data)
		{
			if(this.right == null)
			{
				this.right = node;
				node.isLeft = false;
			}else{
				this.right.addNode(node);
			}
		}else{
			if(this.left == null)
			{
				this.left = node;
				node.isLeft = true;
			}else{
				this.left.addNode(node);
			}
		}
		this.count ++;
	}
	
	
	/*
	 * 通过 节点的数据 来删除节点，如果要删除的是根节点，且总节点数为1，已在调用该方法前处理。
	 */
	public boolean deleteNode(Node parent,int data) 
	{
		if(this.data == data)// 要删除的节点
		{
			if(this.left == null && this.right == null) //如果要删除的节点是叶子节点，则直接删除
			{
				if(this.isLeft) //判断当前节点 是否为 左节点。
				{
					parent.setLeft(null);
				}else{
					parent.setRight(null);
				}
				return true;
			}else{  //当前节点非叶子节点
			
				if(this.right != null)
				{
					return deleteLeaf(this,this.right); //递归调用，删除最后一个叶子节点。
				}
				if(this.left != null)
				{
					return deleteLeaf(this,this.left);
				}
				return false;
			}
		}else if(this.data < data) // 要删除节点的数据 比 当前节点的数据大，递归调用右子树删除该节点
		{
			if(this.right != null)
			{
				return this.right.deleteNode(this,data);
			}else{
				return false;
			}
		}else if(this.data > data)//要删除的节点的数据 比 当前节点的数据小，递归调用左子树删除该节点
		{
			if(this.left != null)
			{
				return this.left.deleteNode(this,data);
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/*
	 * 递归调用，删除最后一个叶子节点。
	 */
	private boolean deleteLeaf(Node parent,Node leaf) 
	{
		parent.setData(leaf.data);  //先把 要删除节点的数据 传递给 父节点
		if(leaf.left == null && leaf.right == null)
		{			//如果是叶子节点，则可以直接删除，重计该树节点数
			if(leaf.isLeft)
			{
				parent.setLeft(null);
			}else{
				parent.setRight(null);
			}
			return true;
		}else{			
			if(leaf.right != null)
			{
				return deleteLeaf(leaf,leaf.right);
			}
			if(leaf.left != null)
			{
				return deleteLeaf(leaf,leaf.left);
			}
			return false;
		}
	}

	//先序遍历
	public void preorder()
	{
		System.out.print(this.data+"->");
		if(this.left != null)
		{
			this.left.preorder();
		}
		if(this.right != null)
		{
			this.right.preorder();
		}
	}
	
	//中序遍历
	public void inorder() 
	{
		
		if(this.left != null)
		{
			this.left.inorder();
		}
		System.out.print(this.data+"->");
		if(this.right != null)
		{
			this.right.inorder();
		}
	}
	
	//后序遍历
	public void postorder() 
	{
		
		if(this.left != null)
		{
			this.left.postorder();
		}
		if(this.right != null)
		{
			this.right.postorder();
		}
		System.out.print(this.data+"->");
	}

	//查找当前树的 第几 大节点值
	public int selectThMax(int th) {		
		int smaller = 1;
		if(this.left != null) smaller = this.left.count + 1;	
		if(th == smaller)
		{
			return this.getData();
		}else if(th > smaller)
		{
			return this.right.selectThMax(th-smaller);
		}else{
			return this.left.selectThMax(th);
		}
	}

	//对当前树的节点数 重新审计
	public void recount() 
	{
		this.setCount(1);
		if(this.left != null)
		{
			this.left.recount();
			this.count += this.left.count;
		}
		if(this.right != null)
		{
			this.right.recount();
			this.count += this.right.count;
		}
	}
}
