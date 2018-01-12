package com.arithmetic.thought;

import java.util.ArrayList;
/*
 * 递归算法
 */
public class Recursion {

	//给定一个没有重复数字的集合，求所有子集，子集元素非降序排列	
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
		int last = data.get(data.size()-1);
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

	//给定一个有重复数字的集合，求所有子集，子集元素非降序排列	
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
	
	//给定一个不重复的集合，对其全排列，所有数字排列组合。少年，不错哦！
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
	
	//从输入集合中 取出不为index的所有 成员
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

	//给定一个有重复数字的集合，对其全排序 
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

}
