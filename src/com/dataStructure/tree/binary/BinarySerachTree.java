package com.dataStructure.tree.binary;

public class BinarySerachTree<T extends Comparable<T>> {
	public Node<T> root = null;	
	
	//中序遍历
	public void inorder(Node<T> node)
	{
		if(node != null)
		{
			inorder(node.left);
			System.out.print(node.key+"  ");
			inorder(node.right);
		}
	}
	//查看结点
	public Node<T> search(Node<T> head,T key)
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
	public Node<T> minimum(Node<T> head)
	{
		if(head == null || head.left == null ) 
			return head;
		Node<T> node = head;
		while(node.left != null)
		{
			node = node.left;
		}
		return node;
	}
	//最大值
	public Node<T> maximum(Node<T> head)
	{
		if(head == null || head.right == null) return head;
		Node<T> node = head;
		while(node.right != null)
		{
			node = node.right;
		}
		return node;
	}
	//中序遍历的后继
	public Node<T> successor(Node<T> head)
	{
		if(head == null) return null;
		
		Node<T> node = head;
		if(this.maximum(this.root) == node) return null;
				
		if(node.right != null)
		{
			return minimum(node.right);
		}else{
			Node<T> parent = node.parent;
			while(parent.right == node)
			{
				node = parent;
				parent = parent.parent;
			}
			return parent;
		}		
	}
	//中序遍历的前继
	public Node<T> predecessor(Node<T> head)
	{
		if(head == null ) return null;
		
		Node<T> node = head;
		if(this.minimum(this.root) == node) return null;
		
		if(node.left != null)
		{
			return maximum(node.left);
		}else{			
			Node<T> parent = node.parent;
			while(parent.left == node)
			{
				parent = parent.parent;
				node = parent;
			}
			return parent;
		}
	}
	//插入结点
	public boolean insert(BinarySerachTree<T> tree,Node<T> node)
	{
		if(node == null) return false;		
		if(this.search(tree.root, node.key) != null) return false;
		
		Node<T> insertPoint = null;
		Node<T> head = tree.root;
		while(head != null)
		{
			insertPoint = head;
			if(node.key.compareTo(head.key) > 0)
			{
				head = head.right;
			}else{
				head = head.left;
			}
		}
		if(insertPoint == null)  //树为空
		{
			tree.root = node;
			node.parent = null;
			return true;
		}
		if(node.key.compareTo(insertPoint.key) > 0)
		{
			insertPoint.right = node;
		}else{
			insertPoint.left = node;
		}
		node.parent = insertPoint;
		return true;
	}
	//迁移结点
	public boolean transplant(BinarySerachTree<T> tree,Node<T> dest,Node<T> source)
	{	//source是可以为空的，为空时等于就是删除掉目标节点
		if(dest == null) return false;
		if(dest.parent == null)
		{
			tree.root = source;
			if(source != null) source.parent = null;
			return true;
		}		
		if(this.search(tree.root,dest.key)== null)
			return false;
		
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
	public boolean delete(BinarySerachTree<T> tree,Node<T> node)
	{
		if( node == null) return false;
		//如果这个结点不存在于这颗树中，返回
		if(this.search(tree.root, node.key) == null) return false;
		
		if(node.left == null && node.right == null)
		{	//如果删除的节点是 叶节点
			if(node == node.parent.left)
			{
				node.parent.left = null;
			}else{
				node.parent.right = null;
			}
			node.parent = null;
			return true;
		}else if(node.left == null && node.right != null)//
		{	//删除的节点 左子树为空
			return this.transplant(tree, node, node.right);
		}else if(node.right == null && node.left != null)
		{	//删除的节点 右子树为空
			return this.transplant(tree, node,node.left);
		}else{
			//删除的节点 左右子树不为空
			Node<T> right_min = this.minimum(node.right);
			if(right_min.parent != node)
			{	//如果后备节点 的父节点 不是 要删除的节点，需要设置其右子树
				transplant(tree, right_min, right_min.right);
				right_min.right = node.right;
				right_min.right.parent = right_min;
			}
			transplant(tree, node, right_min);
			right_min.left = node.left;
			right_min.left.parent = right_min;
			return true;
		}
	}
}

class Node<T extends Comparable<T>>{
	public T key;
	public Node<T> left;
	public Node<T> right;
	public Node<T> parent;
	
	public Node(){}
	
	public Node(T key)
	{
		this.key = key;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public Node(Node parent,T key)
	{
		this.parent = parent;
		this.key = key;
		this.left = null;
		this.right = null;
	}
	
}
