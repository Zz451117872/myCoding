package com.dataStructure.tree.binary;

import java.io.Serializable;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySerachTree<T extends Comparable<T>> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public BinarySerachTreeNode<T> root = null;	
	
	//中序遍历
	public void inorder(BinarySerachTreeNode<T> BinarySerachTreeNode)
	{
		if(BinarySerachTreeNode != null)
		{
			if(BinarySerachTreeNode.left != null){
				inorder(BinarySerachTreeNode.left);
			}
			System.out.print(BinarySerachTreeNode.key+" ");
			if(BinarySerachTreeNode.right != null){
				inorder(BinarySerachTreeNode.right);
			}
		}else{
			System.out.println("this tree is empty!!!");
		}
	}
	
	//先序非递归遍历
	public void preorderByNoRecursion(BinarySerachTreeNode<T> root)
	{
		if(root == null) return;
		Stack<BinarySerachTreeNode<T>> workspace = new Stack<BinarySerachTreeNode<T>>();
		workspace.push(root);
		while(!workspace.isEmpty())
		{
			BinarySerachTreeNode<T> node = workspace.pop();
			System.out.print(node.getKey() +"->");
			if(node.getRight() != null)
			{
				workspace.push(node.getRight());
			}
			if(node.getLeft() != null)
			{
				workspace.push(node.getLeft());
			}
		}
		System.out.println("");
	}
	
	/*
	 * 层级遍历
	 */
	public void hierarchyTraverse(BinarySerachTreeNode<T> root)
	{
		if(root == null) return;
		LinkedBlockingQueue<BinarySerachTreeNode<T>> queue = new LinkedBlockingQueue<BinarySerachTreeNode<T>>();
		try {
			queue.put(root);
			while(!queue.isEmpty())
			{
				BinarySerachTreeNode<T> node = queue.poll();
				System.out.print(node.getKey() + "->");
				if(node.getLeft() != null)
				{
					queue.put(node.getLeft());
				}
				if(node.getRight() != null)
				{
					queue.put(node.getRight());
				}				
			}
			System.out.println("");
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	
	//查找第几大元素
	public T getMaxTh(BinarySerachTreeNode<T> root, int th)
	{
		if(root == null) return null;
		if(th < 1) {
			System.out.println("th 参数错误！！");
			return null;
		}
		if(th > root.getCount()) {
			System.out.println("没有第"+th+"大元素，范围超出！！");
			return null;
		}
		
		int leftNodeCount = root.getLeft() == null ? 0 : root.getLeft().getCount();
		if(leftNodeCount > th-1)
		{
			return getMaxTh(root.getLeft(),th);
		}else if(leftNodeCount == th-1){
			return root.getKey();
		}else{
			return getMaxTh(root.getRight(), th-leftNodeCount-1);
		}
	}
	
	//判断当前树是否平衡
	private boolean isImbalance() {		
		if(root == null) return false;
			int leftHierarchy = 0;		//左子树层级
			int rightHierarchy = 0;	//右子树层级
			if(root.getLeft() != null)
			{
				leftHierarchy = root.getLeft().getHierarchy();
			}
			if(root.getRight() != null)
			{
				rightHierarchy = root.getRight().getHierarchy();
			}
			if(Math.abs(rightHierarchy - leftHierarchy) >1)
			{			//如果左右子树层级 之差 大于1，则判定不平衡
				return true;
			}
			return false;
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
	 * 插入节点
	 */
	public void insert(T data)
	{
		BinarySerachTreeNode<T> BinarySerachTreeNode = new BinarySerachTreeNode<T>(data);
		//判断是否树中已存在该元素
		if(this.search(this.root, BinarySerachTreeNode.key) != null) return;
		BinarySerachTreeNode<T> insertPoint = null; //插入节点位置
		BinarySerachTreeNode<T> head = this.root;	
		while(head != null)		//从树根节点 向下循环，找到合适的插入点
		{
			insertPoint = head;
			insertPoint.setCount(insertPoint.getCount() + 1);//所经过的插入点，节点数量都加1
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
		
//		if(isImbalance())
//		{
//			System.out.println("不平衡，需要重构");
//		}
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
