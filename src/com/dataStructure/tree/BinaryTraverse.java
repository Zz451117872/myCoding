package com.dataStructure.tree;


public class BinaryTraverse {
	/*
	 * 已知先序遍历 和 中序遍历，求后序遍历结果
	 * 先序遍历 主要作用是 得到根节点的值
	 * 中序遍历 主要作用是 定位根节点，分割左右子树
	 */
	
	public static int[] preorder = {21,12,10,9,4,24,44,33,25,29,53,76};
	public static int[] inorder = {4,9,10,12,21,24,25,29,33,44,53,76};
	public static void main(String[] str)
	{	
		int[] postorder = new int[preorder.length];	
		handler(0,preorder.length-1,0,inorder.length-1,0,postorder.length-1,postorder);
				
		for(int i=0; i<postorder.length; i++)
		{
			System.out.print(postorder[i] + "->");
		}
		System.out.println("");
	}

	/*
	 * 需要的入参：
	 * 先序区间，中序区间，后序区间
	 */
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
