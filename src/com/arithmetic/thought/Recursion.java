package com.arithmetic.thought;

import java.util.ArrayList;
/*
 * 递归算法
 */
public class Recursion {

	/**
	 * 给定一个没有重复数字的集合，求所有子集，子集元素非降序排列
	 * @param data
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> getSubList(ArrayList<Integer> data)
	{
		if(data == null || data.size() < 1) return null;
		ArrayList<ArrayList<Integer>> total = new ArrayList<ArrayList<Integer>>();
		if(data.size() == 1)
		{
			total.add(data);
			total.add(new ArrayList<Integer>());
			return total;
		}
		int last = data.get( data.size() - 1 );
		ArrayList<ArrayList<Integer>> part = getSubList(getArrayList(data, data.size()-1));
		if(part != null)
		{
			for(int i=0; i<part.size(); i++)
			{
				total.add(part.get(i));
				ArrayList<Integer> temp = getArrayList(part.get(i), -1);
				temp.add(last);
				total.add(temp);
			}
		}
		return total;
	}

	/**
	 * 给定一个有重复数字的集合，求所有子集，子集元素非降序排列	
	 * @param data
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> getSubListRepeat(ArrayList<Integer> data)
	{
		if(data == null || data.size() < 1) return null;
		ArrayList<ArrayList<Integer>> total = new ArrayList<ArrayList<Integer>>();
		if(data.size() == 1)
		{
			total.add(data);
			total.add(new ArrayList<Integer>());
			return total;
		}
		int last = data.get(data.size()-1);
		ArrayList<ArrayList<Integer>> part = getSubList(getArrayList(data, data.size()-1));
		if(part != null)
		{
			for(int i=0; i<part.size(); i++)
			{
				if(!total.contains(part.get(i)))	//处理重复问题
				{
					total.add(part.get(i));
				}
				ArrayList<Integer> temp = getArrayList(part.get(i), -1);
				temp.add(last);
				if(!total.contains(temp))			//处理重复问题
				{
					total.add(temp);
				}
			}
		}
		return total;
	}
	
	/**
	 * 给定一个不重复的集合，对其全排列，所有数字排列组合
	 * @param digits
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> arrange(ArrayList<Integer> digits)
	{
		if(digits == null ||  digits.size() < 1) return null;
		
		ArrayList<ArrayList<Integer>> total = new ArrayList<ArrayList<Integer>>();
		if(digits.size() == 1)
		{
			total.add(digits);
			return total;
		}
		for(int i=0; i<digits.size(); i++)
		{
			int temp = digits.get(i);
			ArrayList<ArrayList<Integer>> partail = arrange(getArrayList(digits,i));
			if(partail != null)
			{
				for(int j=0; j<partail.size(); j++)
				{
					partail.get(j).add(temp);
					total.add(partail.get(j));
				}
			}
		}
		return total;
	}
	
	/**
	 * 从输入集合中 取出不为index的所有 成员
	 * @param digits
	 * @param index
	 * @return
	 */
	private static ArrayList<Integer> getArrayList(ArrayList<Integer> digits, int index) 
	{
		if(digits == null) return null;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i=0; i<digits.size(); i++)
		{
			if(i != index)
			{
				temp.add(digits.get(i));
			}
		}
		return temp;
	}

	/**
	 * 给定一个有重复数字的集合，对其全排序 
	 * @param digits
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> arrangeRepeat(ArrayList<Integer> digits)
	{
		if(digits == null ||  digits.size() < 1) return null;
		
		ArrayList<ArrayList<Integer>> total = new ArrayList<ArrayList<Integer>>();
		if(digits.size() == 1)
		{
			total.add(digits);
			return total;
		}
		for(int i=0; i<digits.size(); i++)
		{
			int temp = digits.get(i);
			ArrayList<ArrayList<Integer>> partail = arrange(getArrayList(digits,i));
			if(partail != null)
			{
				for(int j=0; j<partail.size(); j++)
				{
					partail.get(j).add(temp);
					if(!total.contains(partail.get(j)))	//判断 重复
					{
						total.add(partail.get(j));
					}
				}
			}
		}
		return total;
	}	

	/*
	 * 104.求二叉树的最大深度
	 * 111.求二叉树的最小深度
	 * 226.反转一颗二叉树
	 * 100.给定二颗二叉树，判断是否相等
	 * 101.给定一颗二叉树，判断 左右子树是否对称
	 * 222.给定一颗完全二叉树，求节点个数
	 * 
	 * 112.给定一颗二叉树与sum，判断是否存在一条从根到叶子节点的路径 使得该路径上所有结点的和为sum
	 * 404.求一颗二叉树的左叶子 节点的和
	 * 257.给定一颗二叉树，返回所有从根节点到叶子节点的路径的字符串
	 * 113.给定一颗二叉树，返回所有从根节点到叶子节点的路径，其和为sum
	 * 437.给定一颗二叉树及sum，判断存在多少条路径，使得路径上所有节点的和为sum。
	 * 235.给定一颗二叉搜索树和两个节点，寻找这两个节点最近的公共祖先
	 * 236.给定一颗二叉树和两个节点，寻找这两个节点最近的公共祖先
	 * 98.给定一颗二叉树，验证是否是二叉搜索树。
	 * 108.将一个有序数组 转化为 一颗平衡的二叉搜索树。
	 * 230.给定一颗二叉搜索树，寻找第k小元素
	 */
}
