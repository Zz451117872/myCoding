package com.dataStructure.tree.binary;

import java.io.Serializable;

public class BinarySerachTree<T extends Comparable<T>> implements Serializable{
	
	public BinarySerachTreeNode<T> root = null;	
	
	//中序遍历
	public void inorder()
	{
		if(this.root != null){
			doInorder(this.root);
			System.out.println("");
		}else{
			System.out.println("empty!!!");
		}
	}
	
	private void doInorder(BinarySerachTreeNode<T> BinarySerachTreeNode)
	{
		if(BinarySerachTreeNode != null)
		{
			doInorder(BinarySerachTreeNode.left);
			System.out.print(BinarySerachTreeNode.key+" ");
			doInorder(BinarySerachTreeNode.right);
		}
	}
	
	/*
	 * 根据key值 查找节点,需要使用到递归的方法，需要传入节点参数，以表示从哪个节点开始查
	 */
	public BinarySerachTreeNode<T> search(BinarySerachTreeNode<T> head,T key)
	{
		if(head == null || key == null) return null;
		if(head.key.compareTo(key) == 0)
		{
			return head;
		}
		if(key.compareTo(head.key) > 0)
		{
			return search(head.right,key);
		}else{
			return search(head.left,key);
		}
	}
	/*
	 * 查找指定树的最小值
	 */
	public BinarySerachTreeNode<T> minimum(BinarySerachTreeNode<T> head)
	{
		if(head == null || head.left == null ) return head;
		BinarySerachTreeNode<T> BinarySerachTreeNode = head;
		while(BinarySerachTreeNode.left != null)
		{
			BinarySerachTreeNode = BinarySerachTreeNode.left;
		}
		return BinarySerachTreeNode;
	}
	/*
	 * 查找指定树的最大值
	 */
	public BinarySerachTreeNode<T> maximum(BinarySerachTreeNode<T> head)
	{
		if(head == null || head.right == null) return head;
		BinarySerachTreeNode<T> BinarySerachTreeNode = head;
		while(BinarySerachTreeNode.right != null)
		{
			BinarySerachTreeNode = BinarySerachTreeNode.right;
		}
		return BinarySerachTreeNode;
	}
	/*
	 * 查找给定结点的中序后继节点
	 */
	public BinarySerachTreeNode<T> successor(BinarySerachTreeNode<T> head)
	{
		if(head == null) return null;
		BinarySerachTreeNode<T> BinarySerachTreeNode = head;
		if(BinarySerachTreeNode.right != null)
		{			//如果右子树不为空，则后继节点为 右子树的最小值节点
			return minimum(BinarySerachTreeNode.right);
		}else{				//如果当前结点是该树的最大值结点，则当前结点没有后继节点
			if(this.maximum(this.root) == BinarySerachTreeNode) return null;
			BinarySerachTreeNode<T> parent = BinarySerachTreeNode.parent;
				//得到当前结点的父结点 直到 该父结点的右结点 不是当前结点，返回该父节点		
			while(parent.right == BinarySerachTreeNode)
			{
				BinarySerachTreeNode = parent;
				parent = parent.parent;
			}
			return parent;
		}		
	}
	/*
	 * 查询给定结点的中序前驱结点
	 */
	public BinarySerachTreeNode<T> predecessor(BinarySerachTreeNode<T> head)
	{
		if(head == null ) return null;
		BinarySerachTreeNode<T> BinarySerachTreeNode = head;
		if(BinarySerachTreeNode.left != null)
		{		//如果当前结点的左子树不为空，则返回左子树的最大值
			return maximum(BinarySerachTreeNode.left);
		}else{		//如果当前结点是 该树的最小值，则该结点没有前驱结点
			if(this.minimum(this.root) == BinarySerachTreeNode) return null;
			BinarySerachTreeNode<T> parent = BinarySerachTreeNode.parent;
				//得到该结点的父结点 直到 该父结点的左子树 不是当前结点，并返回该父结点
			while(parent.left == BinarySerachTreeNode)
			{
				parent = parent.parent;
				BinarySerachTreeNode = parent;
			}
			return parent;
		}
	}
	/*
	 * 新增节点
	 */
	public void add(T data)
	{
		BinarySerachTreeNode<T> BinarySerachTreeNode = new BinarySerachTreeNode<T>(data);
		insert(BinarySerachTreeNode);
	}
	/*
	 * 插入节点
	 */
	public void insert(BinarySerachTreeNode<T> BinarySerachTreeNode)
	{
		if(BinarySerachTreeNode == null) return;
		if(this.search(this.root, BinarySerachTreeNode.key) != null) return;
		BinarySerachTreeNode<T> insertPoint = null; //插入节点位置
		BinarySerachTreeNode<T> head = this.root;	
		while(head != null)		//从树根节点 向下循环，找到合适的插入点
		{
			insertPoint = head;
			if(BinarySerachTreeNode.key.compareTo(head.key) > 0)
			{
				head = head.right;
			}else{
				head = head.left;
			}
		}
		if(insertPoint == null)  //当插入点为空时，表示该树是空树，直接插入节点
		{
			this.root = BinarySerachTreeNode;
			BinarySerachTreeNode.parent = null;
			return;
		}					//待插入结点的值 大于 插入点的值，则放右边，反之放左边
		if(BinarySerachTreeNode.key.compareTo(insertPoint.key) > 0)
		{
			insertPoint.right = BinarySerachTreeNode;
		}else{
			insertPoint.left = BinarySerachTreeNode;
		}
		BinarySerachTreeNode.parent = insertPoint;
		return;
	}
	/*
	 * 迁移结点：用一个指定结点去覆盖另一个结点
	 * dest: 被覆盖的结点
	 * source: 指定结点
	 */
	public boolean transplant(BinarySerachTreeNode<T> dest,BinarySerachTreeNode<T> source)
	{
		if(dest == null) return false;
											//判断被覆盖结点是否在树中
		BinarySerachTreeNode<T> BinarySerachTreeNode = this.search(this.root,dest.key);
		if(BinarySerachTreeNode == null) return false;
		if(dest.parent == null)	//如果被覆盖结点是根结点，则变更根结点
		{
			this.root = source;
			if(source != null){
				source.parent = null;
			}
			return true;
		}														
						//如果被覆盖结点是其父结点的右结点，则更新其父结点的右结点为 指定结点
		if(dest.parent.right == dest)
		{					
			dest.parent.right = source;
		}else if(dest.parent.left == dest)
		{
			dest.parent.left = source;
		}
		if(source != null) source.parent = dest.parent;
		return true;
	}
	/*
	 * 删除节点
	 */
	public boolean delete(T key)
	{
		if( key == null) return false;				//判断待删除的结点是否在树中
		BinarySerachTreeNode<T> currentNode = this.search(this.root, key);
		if(currentNode == null) return false;
		if(currentNode.left == null)
		{						//如果待删除的左节点为空，则用其右节点迁移 其待删除结点
			return this.transplant(currentNode, currentNode.right);
		}else if(currentNode.right == null)
		{
			return this.transplant(currentNode,currentNode.left);
		}else{			//若待删除结点的左右结点都不为空，则用其右子树的最小值为替换待删除结点
			BinarySerachTreeNode<T> right_min = this.minimum(currentNode.right);
			if(right_min.parent != currentNode)
			{
				transplant(right_min, right_min.right);
				right_min.right = currentNode.right;
				right_min.right.parent = right_min;
			}
			transplant(currentNode, right_min);
			right_min.left = currentNode.left;
			right_min.left.parent = right_min;
			return true;
		}
	}
}
