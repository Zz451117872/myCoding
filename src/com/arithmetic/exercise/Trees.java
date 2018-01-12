package com.arithmetic.exercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dataStructure.tree.binary.BinarySerachTree;
import com.dataStructure.tree.binary.BinarySerachTreeNode;

public class Trees {
	
//		String path = System.getProperty("user.dir")+"/src/com/dataStructure/question/tree/tree.txt";
	
	//区间搜索
	public static List<Integer> range(BinarySerachTree<Integer> tree,int start,int end)
	{
		if(tree.root == null) return null;
		List<Integer> ranges = new ArrayList<Integer>();
		inorder(tree.root,start,end,ranges);
		return ranges;
	}
	
	//中序区间搜索
	private static void inorder(BinarySerachTreeNode<Integer> root, int start, int end, List<Integer> ranges) 
	{
		if(root == null) return;
		if(root.key > end)
		{
			inorder(root.left,start,end,ranges);
		}else if(root.key < start)
		{
			inorder(root.right,start,end,ranges);
		}else{
			inorderGt(root.left,start,ranges);
			ranges.add(root.key);
			inorderLt(root.right,end,ranges);
		}
	}

	//中序小于搜索
	private static void inorderLt(BinarySerachTreeNode<Integer> root, int end, List<Integer> ranges) 
	{
		if(root == null ) return;
		if(root.key <= end)
		{
			inorderInsert(root.left, ranges);
			ranges.add(root.key);
			inorderLt(root.right,end,ranges);
		}else{
			inorderLt(root.left,end,ranges);
		}
	}

	//中序大于搜索
	private static void inorderGt(BinarySerachTreeNode<Integer> root, int start, List<Integer> ranges) 
	{
		if(root == null) return;
		if(root.key >= start)
		{
			inorderGt(root.left,start,ranges);
			ranges.add(root.key);
			inorderInsert(root.right,ranges);
		}else{
			inorderGt(root.right,start,ranges);
		}
	}

	//中序遍历存值
	private static void inorderInsert(BinarySerachTreeNode<Integer> root, List<Integer> ranges) 
	{
		if(root == null) return;
		if(root.left != null)
		{
			inorderInsert(root.left,ranges);
		}
		ranges.add(root.key);
		if(root.right != null)
		{
			inorderInsert(root.right,ranges);
		}
	}

	//树序列化
	public  static  void serialize(BinarySerachTree<Integer> tree, String path)
	{
		File file = new File(path);
		if(!file.exists()) return;
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(tree);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(oos != null)
			{
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//树反序列化
	public static BinarySerachTree<Integer> deserialize(String path)
	{
		File file = new File(path);
		if(!file.exists()) return null;
		ObjectInputStream ois = null;
		try{
			ois = new ObjectInputStream(new FileInputStream(file));
			BinarySerachTree<Integer> tree = (BinarySerachTree<Integer>) ois.readObject();
			return tree;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ois != null)
			{
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	/*
	 * 已知先序遍历 和 中序遍历，求后序遍历结果
	 * 先序遍历 主要作用是 得到根节点的值
	 * 中序遍历 主要作用是 定位根节点，分割左右子树
	 */
	
	public static int[] preorder = {21,12,10,9,4,24,44,33,25,29,53,76};
	public static int[] inorder = {4,9,10,12,21,24,25,29,33,44,53,76};
	public static void handler(int prex,int prey,int inx,int iny,int postx,int posty,int[] postorder)
	{		
		try{
			int root = preorder[prex];		//得到根节点的值
			int rootIndex = getIndex(inorder,root);		//在中序遍历中定位根节点位置
			postorder[posty] = root;		//将 根节点的值 写入 后序遍历指定位置
			if(rootIndex != -1)
			{			// 以根节点为界，分割左右子树，得到左右子树的 中序区间	
				int leftInx = inx;	//左子树 中序区间
				int leftIny = rootIndex -1 < inx ? inx :rootIndex -1;
				int rightInx = rootIndex +1 >iny ?iny:rootIndex +1;  //右子树 中序区间
				int rightIny = iny;
											
				if(leftIny >= leftInx && leftIny <rootIndex)
				{		//如果有左子树，通过中序区间计算 先序区间 与 后序区间，并递归调用
					int leftPrex = prex+1;			// 左子树  先序区间
					int leftPrey = prex + (leftIny - leftInx+1);
					int leftPostx = postx;		//左子树 后序区间
					int leftPosty = postx+(leftIny - leftInx+1) -1;
					handler(leftPrex,leftPrey,leftInx,leftIny,leftPostx,leftPosty,postorder);
				}
				if(rightIny >= rightInx && rightInx > rootIndex)
				{		//如果有右子树，通过中序区间计算 先序区间 与 后序区间，并递归调用
					int rightPrex = prey - (rightIny - rightInx + 1) +1;		//右子树 先序区间
					int rightPrey = prey;
					int rightPostx = posty-(rightIny - rightInx + 1);			//右子树 后序区间
					int rightPosty = posty-1;
					handler(rightPrex,rightPrey,rightInx,rightIny,rightPostx,rightPosty,postorder);
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("出错了！");
		}						
	}
		
	private static int getIndex(int[] inorder, int root) {
			if(inorder == null || inorder.length <1) return -1;
			
			for(int i=0; i<inorder.length; i++)
			{
				if(inorder[i] == root)
				{
					return i;
				}
			}
			return -1;
		}

		
}
