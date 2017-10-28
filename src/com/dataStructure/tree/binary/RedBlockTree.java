package com.dataStructure.tree.binary;

public class RedBlockTree<T extends Comparable<T>> {
	public static final String Block = "block";
	public static final String Red = "red";
	
	public RBNode<T> root = null;
	public RBNode<T> NIL = new RBNode<T>(Block);
	
	public void inorder(RBNode<T> node)
	{
		if(node != NIL && node != null)
		{
			inorder(node.left);
			System.out.print(node.key+":"+node.color+"  ");
			inorder(node.right);
		}
	}
	//平衡检查
	public void balanceCheck(RedBlockTree<T> tree)
	{
		if(tree.root == null) return;
		RBNode<T> head = tree.root;
		int left = 0;
		int right = 0;
		if(head.left != NIL)
		{
			left = layer(head.left);
		}
		if(head.right != NIL)
		{
			right = layer(head.right);
		}
		System.out.println("left:"+left+"  right:"+right);
	}
	//层高计算
	private int layer(RBNode<T> node) 
	{
		if(node == NIL || node == null) return 0;
		if(node.left == NIL && node.right == NIL) return 1;
		if(node.left == NIL) return 1+layer(node.right);
		if(node.right == NIL) return 1+layer(node.left);
		
		if(layer(node.left) > layer(node.right))
		{
			return 1+layer(node.left);
		}else{
			return 1+layer(node.right);
		}
	}

	public RBNode<T> search(RBNode<T> head,T key)
	{
		if(head == null || head == NIL) return null;
		
		RBNode<T> node = head;
		if(node.key.compareTo(key) == 0)  return node;
		
		if(node.key.compareTo(key) > 0)
		{
			return search(node.left,key);
		}else{
			return search(node.right,key);
		}
	}
	
	public RBNode<T> minimum(RBNode<T> head)
	{
		if(head == NIL || head == null) return head;
		RBNode<T> node = head;
		while(node.left != NIL)
		{
			node = node.left;
		}
		return node;
	}
	
	public RBNode<T> maximum(RBNode<T> head)
	{
		if(head == NIL || head == null) return head;
		RBNode<T> node = head;
		while(node.right != NIL)
		{
			node  = node.right;
		}
		return node;
	}
	
	public RBNode<T> successor(RBNode<T> head)
	{
		if(head == null || this.maximum(this.root) == head) 
			return head;
		if(head.right != NIL)
		{
			return this.minimum(head.right);
		}else{
			RBNode<T> parent = head.parent;
			while(parent.right == head)
			{
				head = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	
	public RBNode<T> predecessor(RBNode<T> head)
	{
		if(head == null || this.minimum(this.root) == head) 
			return head;
		if(head.left != NIL)
		{
			return this.maximum(head.left);
		}else{
			RBNode<T> parent = head.parent;
			while(parent.left == head)
			{
				head = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	//左旋：是结点 与 右子树 的互动 
	public boolean leftRotate(RedBlockTree<T> tree,RBNode<T> node)
	{
		if(node == null || tree.root == null) return false;
		
		if(node.right == NIL) return false;
		
		RBNode<T> node_right = node.right;
		node.right = node_right.left;
		if(node_right.left != NIL)
		{
			node_right.left.parent = node;
		}	
		node_right.parent = node.parent;
		if(node.parent == null)
		{
			tree.root = node_right;
			node_right.parent = null;
		}else if(node == node.parent.left)
		{
			node.parent.left = node_right;
		}else{
			node.parent.right = node_right;
		}
		node_right.left = node;
		node.parent = node_right;
		return true;
	}
	//右旋：结点与 左子树的互动，左子树层级降低
	public boolean  rightRotate(RedBlockTree<T> tree,RBNode<T> node)
	{
		if(node == null || tree.root == null) return false;
		if(node.left == NIL) return false;
		
		RBNode<T> node_left = node.left;
		node.left = node_left.right;
		if(node_left.right != NIL)
		{
			node_left .right.parent = node ;
		}
		
		node_left .parent = node.parent;
		if(node.parent == null)
		{
			tree.root = node_left ;
			node_left.parent = null;
		}else if(node == node.parent.left)
		{
			node.parent.left = node_left ;
		}else{
			node.parent.right = node_left ;
		}
		node_left .right = node;
		node.parent = node_left ;
		return true;
	}
	
	public boolean insert(RedBlockTree<T> tree,RBNode<T> node)
	{
		if(node == null || node == NIL) return false;
		if(this.search(tree.root,node.key) != null) return false;
		
		RBNode<T> insertPoint = null;
		RBNode<T> head = tree.root;
		while(head != NIL && head != null)
		{
			insertPoint = head;
			if(head.key.compareTo(node.key) > 0)
			{
				head = head.left;
			}else{
				head = head.right;
			}
		}
		if(insertPoint == null) //空树
		{
			tree.root = node;
			node.parent = null;
		}else{
			if(insertPoint.key.compareTo(node.key) > 0)
		 	{
				insertPoint.left = node;
		 	}else{
		 		insertPoint.right = node;
		 	}
			node.parent = insertPoint;
		}		
		node.left = NIL;
		node.right = NIL;
		node.color = Red;
		insert_fixup(tree,node);
		return true;
	}
	
	public void insert_fixup(RedBlockTree<T> tree,RBNode<T> node)
	{
		if( node == null || node == NIL ) return;
		
		if(tree.root.left == NIL && tree.root.right == NIL)
		{		
			tree.root.color = Block;
			return;
		}
		
		while(node.parent != null &&  node.parent.color.equals(Red))
		{
			if( node.parent == node.parent.parent.left)
			{
				RBNode<T> uncle = node.parent.parent.right;
				if(uncle.color.equals(Red))
				{
					uncle.color = Block;
					node.parent.color = Block;				
					node.parent.parent.color = Red;
					node = node.parent.parent;
				}else if(node == node.parent.right){
					node = node.parent;
					leftRotate(tree, node);
				}else{			
					node.parent.color = Block;
					node.parent.parent.color = Red;				
					rightRotate(tree, node.parent.parent);		
				}				
			}else{				
				RBNode<T> uncle = node.parent.parent.left;
				
				if(uncle.color.equals(Red))
				{
					uncle.color = Block;
					node.parent.color = Block;				
					node.parent.parent.color = Red;
					node = node.parent.parent;
				}else if(node == node.parent.left){
					node = node.parent;
					rightRotate(tree, node);
				}else{
					node.parent.color = Block;
					node.parent.parent.color = Red;				
					leftRotate(tree, node.parent.parent); 
				}
			}
		}
		tree.root.color = Block;
	}
	
	public boolean transplant(RedBlockTree<T> tree,RBNode<T> dest,RBNode<T> source)
	{
		if(dest == null || dest == NIL) return false;
		
		if(dest.parent == null)
		{
			this.root = source;
			if(source != null && source != NIL) 
				source.parent = null;
			return true;
		}
		
		if(this.search(tree.root,dest.key) == null) return false;
		
		if(dest.parent.left == dest)
		{
			dest.parent.left = source;
		}else if(dest.parent.right == dest)
		{
			dest.parent.right = source;
		}
		if(source != NIL && source != null) 
			source.parent = dest.parent;		
		return true;
	}
	
	public boolean delete(RedBlockTree<T> tree, RBNode<T> node)
	{
		if(node == NIL || node == null ) return false;	
		if(tree.root == null || tree.root == NIL) return false;
		
		if(this.search(tree.root, node.key) == null) return false;
		
		RBNode<T> successor = node;
		String successorColor = successor.color;
		RBNode<T> track = node;
		
		if(node.left == NIL)
		{
			track = node.right;
			return transplant(tree,node,node.right);
		}else if(node.right == NIL)
		{
			track = node.left;
			return transplant(tree,node,node.left);
		}else{
			successor = this.minimum(node.right);//得到要删除节点的右子树最小值
			successorColor = successor.color;
			track = successor.right;
			
			if(successor.parent == node)
			{
				track.parent = successor;
			}else{
				transplant(tree,successor,successor.right);//将右子树最小值从树中删除
				successor.right = node.right;//当右子树最小值 的父节点 不是要删除的节点时，需要把要删除的节点的右子树保留下来
				successor.right.parent = successor;
			}
			transplant(tree,node,successor);//用右子树最小值 将 要删除的值 替换掉
			successor.left = node.left;//把要删除节点的左子树保留下来
			successor.left.parent = successor;
			successor.color = node.color;
		}
		
		if(tree.root == null || tree.root == NIL) return true;
		if(successorColor == Block) 
			delete_fixup(tree,track);
		return true;
	}
	
	public void delete_fixup(RedBlockTree<T> tree, RBNode<T> node)
	{
		if(node == NIL) return;
		while(node != tree.root && node.color.equals(Block))
		{
			if(node == node.parent.left)
			{
				RBNode<T> brother = node.parent.right;
				if(brother == NIL) return;
				
				if(brother.color.equals(Red))
				{
					brother.color = Block;
					node.parent.color = Red;
					leftRotate(tree,node.parent);
					brother = node.parent.right;					
				}else if(brother.left.color.equals(Block)
						&& brother.right.color.equals(Block))
				{
					brother.color = Red;
					node = node.parent;
				}else if(brother.right.color.equals(Block)
						&& brother.left.color.equals(Red))
				{
					brother.left.color = Block;
					brother.color = Red;
					rightRotate(tree,brother);
					brother = node.parent.right;
				}else if(brother.right.color.equals(Red))
				{
					brother.color = node.parent.color;
					node.parent.color = Block;
					leftRotate(tree,node.parent);
					node = tree.root;
				}
			}else if( node == node.parent.right){
				RBNode<T> brother = node.parent.left;
				if(brother == NIL) return;
				
				if(brother.color.equals(Red))
				{
					brother.color = Block;
					node.parent.color = Red;
					rightRotate(tree,node.parent);
					brother = node.parent.left;					
				}else if(brother.left.color.equals(Block)
						&& brother.right.color.equals(Block))
				{
					brother.color = Red;
					node = node.parent;
				}else if(brother.right.color.equals(Red)
						&& brother.left.color.equals(Block))
				{
					brother.right.color = Block;
					brother.color = Red;
					leftRotate(tree,brother);
					brother = node.parent.left;
				}else if(brother.left.color.equals(Red)){
					brother.color = node.parent.color;
					node.parent.color = Block;
					rightRotate(tree,node.parent);
					node = tree.root;
				}
			}
			node.color = Block;
		}
	}
	
}

class RBNode<T extends Comparable<T>>{
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
