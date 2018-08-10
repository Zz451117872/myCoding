package com.dataStructure.tree.binary;

public class RedBlockTree<T extends Comparable<T>> {
	public static final String Block = "block";
	public static final String Red = "red";
	
	public RBNode<T> root = null;
	public RBNode<T> NIL = new RBNode<T>(Red);
	
	public void inorder(RBNode<T> node)
	{
		if(node != NIL && node != null)
		{
			inorder(node.left);
			System.out.print(node.key+":"+node.color+"  ");
			inorder(node.right);
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
		while(node.left != null && node.left != NIL)
		{
			node = node.left;
		}
		return node;
	}
	
	public RBNode<T> maximum(RBNode<T> head)
	{
		if(head == NIL || head == null) return head;
		RBNode<T> node = head;
		while(node.right != null &&  node.right != NIL)
		{
			node  = node.right;
		}
		return node;
	}
	
	public RBNode<T> successor(RBNode<T> head)
	{
		if(head == null || this.maximum(this.root) == head) return head;
		if(head.right != null && head.right != NIL)
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
		if(head == null || this.minimum(this.root) == head) return head;
		if(head.left != null &&  head.left != NIL)
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
	//左旋，好难 啊啊啊啊. 
	public boolean leftRotate(RedBlockTree<T> tree,RBNode<T> node)
	{
		if(node == null || tree.root == null) return false;
		if(node.right == NIL) return false;
		
		RBNode<T> node_right = node.right;
		node.right = node_right.left;
		if(node_right.left != null && node_right.left != NIL)
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
	//右旋
	public boolean  rightRotate(RedBlockTree<T> tree,RBNode<T> node)
	{
		if(node == null || tree.root == null) return false;
		if(node.left == NIL) return false;
		
		RBNode<T> node_left = node.left;
		node.left = node_left.right;
		if(node_left.right != null && node_left.right != NIL)
		{
			node_left .right.parent = node_left ;
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
		if(node == null) return false;
		if(this.search(tree.root,node.key) != null) return false;
		
		RBNode<T> point = null;
		RBNode<T> head = tree.root;
		while(head != NIL && head != null)
		{
			point = head;
			if(head.key.compareTo(node.key) > 0)
			{
				head = head.left;
			}else{
				head = head.right;
			}
		}
		if(point == null)
		{
			tree.root = node;
			node.parent = null;
		}else{
			if(point.key.compareTo(node.key) > 0)
		 	{
		 		point.left = node;
		 	}else{
			 	point.right = node;
		 	}
			node.parent = point;
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
				if(uncle != NIL && uncle.color.equals(Red) && node.parent.parent != tree.root)
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
				if(uncle != NIL && uncle.color.equals(Red))
				{
					uncle.color = Block;
					node.parent.color = Block;				
					node.parent.parent.color = Red;
					node = node.parent.parent;
				}else if(node == node.parent.right){
					node = node.parent;
					leftRotate(tree, node);
				}
				else{
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
		if(dest == null || source == null) return false;
		if(source == NIL || dest == NIL)
		{
			System.out.println("NIL 不能迁移");
			return false;
		}
		if(dest.parent == null)
		{
			this.root = source;
			source.parent = null;
			return true;
		}
		RBNode<T> point = this.search(tree.root,dest.key);
		if(point == null) 
			{
				System.out.println("被迁移的结点 不存在");
				return false;		
			}
		if(dest.parent.left == dest)
		{
			dest.parent.left = source;
		}else if(dest.parent.right == dest)
		{
			dest.parent.right = source;
		}
		source.parent = dest.parent;
		dest.left = null;
		dest.right = null;
		dest.parent = null;
		return true;
	}
	
	public void delete(RedBlockTree<T> tree, RBNode<T> node)
	{
		
	}
	
	public void delete_fixup(RedBlockTree<T> tree, RBNode<T> node)
	{
		
	}
	
}
