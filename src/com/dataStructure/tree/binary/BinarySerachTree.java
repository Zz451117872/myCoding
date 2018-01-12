package com.dataStructure.tree.binary;

import java.io.Serializable;

public class BinarySerachTree<T extends Comparable<T>> implements Serializable{
	
	public BinarySerachTreeNode<T> root = null;	
	
	//中序遍历
	public void inorder(BinarySerachTreeNode BinarySerachTreeNode)
	{
		if(BinarySerachTreeNode != null)
		{
			inorder(BinarySerachTreeNode.left);
			System.out.print(BinarySerachTreeNode.key+"  ");
			inorder(BinarySerachTreeNode.right);
		}
	}
	//查看结点
	public BinarySerachTreeNode<T> search(BinarySerachTreeNode<T> head,T key)
	{
		if(key == null) return null;
		if(head == null || head.key.compareTo(key) == 0)
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
	//最小值
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
	//最大值
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
	//中序遍历的后继
	public BinarySerachTreeNode<T> successor(BinarySerachTreeNode<T> head)
	{
		if(head == null) return null;
		BinarySerachTreeNode<T> BinarySerachTreeNode = head;
		if(BinarySerachTreeNode.right != null)
		{
			return minimum(BinarySerachTreeNode.right);
		}else{
			if(this.maximum(this.root) == BinarySerachTreeNode) return null;
			BinarySerachTreeNode<T> parent = BinarySerachTreeNode.parent;
			while(parent.right == BinarySerachTreeNode)
			{
				BinarySerachTreeNode = parent;
				parent = parent.parent;
			}
			return parent;
		}		
	}
	//中序遍历的前继
	public BinarySerachTreeNode<T> predecessor(BinarySerachTreeNode<T> head)
	{
		if(head == null ) return null;
		BinarySerachTreeNode<T> BinarySerachTreeNode = head;
		if(BinarySerachTreeNode.left != null)
		{
			return maximum(BinarySerachTreeNode.left);
		}else{
			if(this.minimum(this.root) == BinarySerachTreeNode) return null;
			BinarySerachTreeNode<T> parent = BinarySerachTreeNode.parent;
			while(parent.left == BinarySerachTreeNode)
			{
				parent = parent.parent;
				BinarySerachTreeNode = parent;
			}
			return parent;
		}
	}
	
	public void add(BinarySerachTree<T> tree, T data)
	{
		BinarySerachTreeNode<T> BinarySerachTreeNode = new BinarySerachTreeNode<T>(data);
		insert(tree,BinarySerachTreeNode);
	}
	//插入结点
	public void insert(BinarySerachTree<T> tree,BinarySerachTreeNode<T> BinarySerachTreeNode)
	{
		if(BinarySerachTreeNode == null) return;
		if(this.search(tree.root, BinarySerachTreeNode.key) != null) return;
		BinarySerachTreeNode<T> y = null;
		BinarySerachTreeNode<T> x = tree.root;
		while(x != null)
		{
			y = x;
			if(BinarySerachTreeNode.key.compareTo(x.key) > 0)
			{
				x = x.right;
			}else{
				x = x.left;
			}
		}
		if(y == null)  
		{
			tree.root = BinarySerachTreeNode;
			BinarySerachTreeNode.parent = null;
			return;
		}
		if(BinarySerachTreeNode.key.compareTo(y.key) > 0)
		{
			y.right = BinarySerachTreeNode;
		}else{
			y.left = BinarySerachTreeNode;
		}
		BinarySerachTreeNode.parent = y;
		return;
	}
	//迁移结点
	public boolean transplant(BinarySerachTree<T> tree,BinarySerachTreeNode<T> dest,BinarySerachTreeNode<T> source)
	{
		if(dest == null || source == null) return false;
		if(dest.parent == null)
		{
			tree.root = source;
			source.parent = null;
			return true;
		}		
		BinarySerachTreeNode<T> BinarySerachTreeNode = this.search(tree.root,dest.key);
		if(BinarySerachTreeNode == null) return false;
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
	//删除结点
	public boolean delete(BinarySerachTree<T> tree,BinarySerachTreeNode<T> BinarySerachTreeNode)
	{
		if( BinarySerachTreeNode == null) return false;
		if(this.search(tree.root, BinarySerachTreeNode.key) == null) return false;
		if(BinarySerachTreeNode.left == null)
		{
			return this.transplant(tree, BinarySerachTreeNode, BinarySerachTreeNode.right);
		}else if(BinarySerachTreeNode.right == null)
		{
			return this.transplant(tree, BinarySerachTreeNode,BinarySerachTreeNode.left);
		}else{
			BinarySerachTreeNode<T> right_min = this.minimum(BinarySerachTreeNode.right);
			if(right_min.parent != BinarySerachTreeNode)
			{
				transplant(tree, right_min, right_min.right);
				right_min.right = BinarySerachTreeNode.right;
				right_min.right.parent = right_min;
			}
			transplant(tree, BinarySerachTreeNode, right_min);
			right_min.left = BinarySerachTreeNode.left;
			right_min.left.parent = right_min;
			return true;
		}
	}
}
