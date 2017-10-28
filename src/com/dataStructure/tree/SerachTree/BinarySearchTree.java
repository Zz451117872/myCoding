package com.dataStructure.tree.SerachTree;

import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTree {
	private Node treeRoot;
	
	public BinarySearchTree()
	{
		treeRoot = null;
	}
	
	
	
	//添加节点
	public void addData(int data)
	{
		Node node = new Node(data,null,null);
		if(this.treeRoot == null)
		{
			this.treeRoot = node;
		}else{
			this.treeRoot.addNode(node);
		}
		if(imbalance())  //添加数据后 检查树 是否平衡
		{
			System.out.println("当前树是不平衡的，需要重构！");
			rebulid();
		}
	}
	
	//删除节点
	public boolean deleteData(int data)
	{
		if(this.treeRoot == null)
		{
			System.out.println("没有树，不能删除！");
			return false;
		}else{
			if(this.treeRoot.getCount() == 1 && data == this.treeRoot.getData())
			{   		//如果删除的节点是根节点，且该树只有一个节点，则直接删除该树
				this.treeRoot = null;
				return true;
			}
			boolean result =  this.treeRoot.deleteNode(null,data);  //删除节点，传入父节点null
			if(result )
			{
				recount(); //如果删除成功，递归重计 树的节点数
				if(imbalance())//如果当前树不平衡，重构。
				{
					rebulid();
				}
			}
			return result;
		}
	}
	
	//对当前树的节点数进行重计
	private void recount() 
	{
		if(this.treeRoot == null)
		{
			return;
		}else{
			this.treeRoot.recount();
		}
	}

	//得到当前树的节点数
	public int getSize()
	{
		return this.treeRoot.getCount();
	}
	
	/*
	 * 非递归先序遍历...真是简单。。。
	 */
	public void preorderByNoRecursion()
	{
		if(this.treeRoot == null) return;
		Node head = this.treeRoot;
		Stack<Node> workspace = new Stack<Node>();
		workspace.push(head);
		while(!workspace.isEmpty())
		{
			Node node = workspace.pop();
			System.out.print(node.getData() +"->");
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
	public void hierarchyTraverse()
	{
		if(this.treeRoot == null) return;
		Node head = this.treeRoot;
		LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<Node>();
		try {
			queue.put(head);
			while(!queue.isEmpty())
			{
				Node node = queue.poll();
				System.out.print(node.getData() + "->");
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
	
	//前序遍历
	public void preorder()
	{
		if(this.treeRoot == null)
		{
			return;
		}else{
			this.treeRoot.preorder();
		}
		System.out.println("");
	}
	
	//中序遍历
	public void inorder()
	{
		if(this.treeRoot == null)
		{
			return;
		}else{
			this.treeRoot.inorder();
		}
		System.out.println("");
	}
	
	//后序遍历
	public void postorder()
	{
		if(this.treeRoot == null)
		{
			return;
		}else{
			this.treeRoot.postorder();
		}
		System.out.println("");
	}
	
	/*
	 * 查找当前树 第 几大 的节点值。
	 */
	public int seleteThMax(int th)
	{
		if(this.treeRoot == null) return -1;
		return this.treeRoot.selectThMax(th);
	}
	
	/*
	 * 重构树，当树不平衡时，则需要重构树；
	 */
	private void rebulid() 
	{
		System.out.println("正在秘密重构当前树！");
		int midIndex = this.treeRoot.getCount() / 2;
		Node root = new Node(seleteThMax(midIndex),null,null); //得到中位数
		deleteData(root.getData());			//删除中位数
		copyTree(this.treeRoot,root);		//将老树的数据 复制给 新树
		this.treeRoot = null;
		this.treeRoot = root;
		System.out.println("重构已完成！");
	}
	
	/*
	 * 复制树，将一个颗树的所有节点 复制到 另一颗树。
	 */
	private void copyTree(Node treeRoot, Node root) 
	{
		if(treeRoot.getLeft() != null)
		{
			copyTree(treeRoot.getLeft(),root);
		}
		if(treeRoot.getRight() != null)
		{
			copyTree(treeRoot.getRight(),root);
		}
		root.addNode(new Node(treeRoot.getData(),null,null));
	}

	/*
	 * 检查当前树是否平衡，当左右子树的层级相差大于 1时 则不平衡，反之平衡。
	 */
	private boolean imbalance() {
		if(this.treeRoot == null) return false;
			int leftHierarchy = 0;		//左子树层级
			int rightHierarchy = 0;	//右子树层级
			if(this.treeRoot.getLeft() != null)
			{
				leftHierarchy = this.treeRoot.getLeft().getHierarchy();
			}
			if(this.treeRoot.getRight() != null)
			{
				rightHierarchy = this.treeRoot.getRight().getHierarchy();
			}
			if(Math.abs(rightHierarchy - leftHierarchy) >1)
			{			//如果左右子树层级 之差 大于1，则判定不平衡
				return true;
			}
			return false;
		}

	public static void main(String[] str)
	{
		Random r = new Random();
		BinarySearchTree tree = new BinarySearchTree();
		int[] data = {21,33,12,44,24,10,9,53,25,29,76,4};
		for(int i=0; i<data.length; i++)
		{
			tree.addData(data[i]);
		}
		
		System.out.println("先序遍历");
		System.out.println("----------------------------------------");
		tree.preorder();
		System.out.println("-----------------------------------------");
		System.out.println("先序非递归遍历");
		System.out.println("----------------------------------------");
		tree.preorderByNoRecursion();
		System.out.println("-----------------------------------------");
		System.out.println("层级遍历");
		System.out.println("----------------------------------------");
		tree.hierarchyTraverse();
		System.out.println("-----------------------------------------");
//		System.out.println("中序遍历");
//		System.out.println("-----------------------------------------");
//		tree.inorder();				
//		System.out.println("-----------------------------------------");
//		System.out.println("后序遍历");
//		System.out.println("-----------------------------------------");
//		tree.postorder();
//		System.out.println("-----------------------------------------");	
		
//		int th = 2;
//		System.out.println("第"+th+"大元素是："+tree.seleteThMax(th));
//		
//		
//		boolean result = tree.deleteData(tree.seleteThMax(th));
//		if(result){
//			System.out.println("删除成功");
//			System.out.println("有元素："+tree.getSize());
//		}else{
//			System.out.println("删除失败");
//			System.out.println("有元素："+tree.getSize());
//		}
//		System.out.println("后序遍历");
//		System.out.println("-----------------------------------------");
//		tree.postorder();
//		System.out.println("-----------------------------------------");	
		
		
		
	}
}
