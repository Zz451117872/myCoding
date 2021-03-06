package com.arithmetic.exercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.arithmetic.entry.MajorityEntry;

import java.util.Set;

/*
 * 数学，计算相关
 */
public class Maths {	

<<<<<<< HEAD
	/**
	 * 寻找{1,2,3,5}的第n 个丑数，丑数即公因子 只能是给定集合中的数
	 * 从1开始循环，获取目标的质因子集合，判断丑数集合是否完全包含质因子集合
	 * @param n ：第几个丑数
	 * @return
	 */
=======
	//寻找{1,2,3,5}的第n 个丑数，丑数即公因子 只能是给定集合中的数
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	public static int uglyDigit(int n)
	{
		int number = 1;
		Set<Integer> preme = new HashSet<Integer>();
		preme.add(1);preme.add(2);
		preme.add(3);preme.add(5);
		
		while(n > 0)
		{										//获取一个数的质因子
			Set<Integer> divisor = getProtons(number);	
			if(preme.containsAll(divisor))//从1开始循环，若是丑数，则计数减1.
			{
				n --;
			}
			number ++;
		}
		return number-1;
	}
<<<<<<< HEAD
	
	/**
	 * 获得一个数的 质因子，
	 * @param n
	 * @return
	 */
=======
	//获得一个数的 质因子
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	public static Set<Integer> getProtons(int n)
	{
		if(n <= 0) return null;
		Set<Integer> divisor = new HashSet<Integer>();
		divisor.add(1);
		if( n == 1) return divisor;
		
		if(isProton(n))
		{
			divisor.add(n);
			return divisor;
		}
		for(int i=2; i<n; i++)
		{
			if(n % i == 0)
			{
				int value = n / i;
				if(isProton(i))
				{
					divisor.add(i);
				}else{
					divisor.addAll(getProtons(i));
				}
				if(isProton(value))
				{
					divisor.add(value);
				}else{
					divisor.addAll(getProtons(value));
				}
			}
		}
		return divisor;
	}
	
<<<<<<< HEAD
	/**
	 * 判断一个数是否是质子，只能被1和自己整除的数
	 * @param n
	 * @return
	 */
=======
	//判断一个数是否是质子
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	public static boolean isProton(int n)
	{
		if(n <= 0 )return false;
		if(n <= 3) return true;
		for(int i=2; i<n; i++)
		{
			if(n%i == 0)
			{
				return false;
			}
		}
		return true;
	}
	
<<<<<<< HEAD
	/**
	 * 从0 到 digit，统计 n 出现的次数
	 * @param digit ：范围
	 * @param n ：目标
	 * @return
	 */
=======
	//从0 到 digit，统计 n 出现的次数
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	public static int digitCount(int digit , int n)
	{
		if(n < 0 || digit < 0) return 0;
		int count = 0;
		for(int i= digit; i>0; i--)
		{
			String s = i+"";
			int index = s.indexOf(n+"");
			if(index != -1)
			{
				count ++;
			}
		}
		return count;
	}
				
	
<<<<<<< HEAD
	/**
	 * 主元素 1/2:是指重复出现次数最多的数字 大于指定概率
	 * 若d在n个数中占比达到K分之一，一次性去除k个互不相同的数不影响d的占比
	 * @param arr
	 * @return
	 */
	public static int majorityNumberHalf1(int[] arr)
=======
	/*
	 * 主元素问题：若d在n个数中占比达到K分之一，一次性去除k个互不相同的数不影响d的占比
	 */

	//主元素 1/2:是指重复出现次数最多的数字
	public static int majorityNumberHalf(int[] arr)
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	{
		if(arr == null || arr.length == 0) return 0;
		if(arr.length == 1) return arr[0];
		
		int major = arr[0];
		int size = 1;
		for(int i=1; i<arr.length; i++)	//获得主元素
		{
			if(arr[i] == major)
			{
				size ++;
			}else{
				if(size > 0)
				{
					size --;
				}else{
					major = arr[i];
					size = 1;
				}
			}
		}
		size = 0;
		for(int i=0; i<arr.length; i++)	//判断是否有主元素
		{
			if(arr[i] == major)
			{
				size ++;
			}
		}
		if(Math.floor(arr.length /( size+0.00 )) < 2)
		{
			return major;
		}else{
			System.out.println("没有主元素");
			return 0;
		}
	}
			
<<<<<<< HEAD
	public static int majorityNumberHalf2(int[] arr)
	{
		if(arr == null || arr.length == 0) return 0;
		if(arr.length == 1) return arr[0];
		if(arr.length == 2) return -1;
		
		
		return 0;
	}
	
	private static void swap(int[] arr, int b, int back) {
		int temp = arr[b];
		arr[b] = arr[back];
		arr[back] = temp;
	}

=======
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	//主元素，达到总数的k分之一以上
	public static int majorityNumberK(int[] arr ,int k)
	{
		if(arr == null || arr.length == 0 || k < 1) return -1;
		if(arr.length == 1) return arr[0];
		
		List<MajorityEntry> majoritys = new ArrayList<MajorityEntry>();
		for(int i=0; i<arr.length; i++)
		{
			MajorityEntry entry = new MajorityEntry(arr[i]);
			if(majoritys.isEmpty())//集合为空，则直接存入
			{
				majoritys.add(entry);
			}else if(majoritys.contains(entry)) //若集合中存在该元素，而增加计数
			{
				int index = majoritys.indexOf(entry);
				MajorityEntry me = majoritys.get(index);
				me.setCount(me.getCount()+1);
			}else{	//若集合中不存在该元素，则判断集合大小。
				if(majoritys.size() < k-1)//大小未超出，则直接存入元素
				{
					majoritys.add(entry);//大小超出，则同时删除 k个互不相同的元素，其主元素占比不会改变
				}else{
					int len = 0;
					while(len < majoritys.size())
					{
						MajorityEntry me = majoritys.get(len);
						if(me.getCount() == 1)
						{
							majoritys.remove(me);
						}else{
							me.setCount(me.getCount()-1);
							len ++;
						}
					}									
				}
			}
		}
		
		if(!majoritys.isEmpty() && majoritys.size() == 1)
		{
			int majority =  majoritys.get(0).getKey();
			int count = 0;
			for(int i=0; i<arr.length; i++)
			{
				if(arr[i] == majority)
				{
					count ++;
				}
			}
			if(Math.floor(arr.length / (count*1.00)) < k)//若主元素占比大于k分之一，则返回
			{
				return majority;
			}else{
				System.out.println("没有主元素！！！");
				return 0;
			}
		}else{
			System.out.println("没有主元素！！！");
			return 0;
		}
	}
	
<<<<<<< HEAD
		
	/**
	 * 孤单的数，2n+1
	 * 异或：相同的数异或 为 0 ，相同为0，不同为1
	 * @param arr
	 * @return
	 */
	public static int getAloneNumber1(int[] arr)
	{
		int result = arr[0];
		for(int i=1; i<arr.length; i++)
=======
	/*
	 * 孤单的数 问题
	 */
	//孤单的数，2n+1
	public static int getAloneNumber1(int[] arr)
	{
		int result = 0;
		for(int i=0; i<arr.length; i++)
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
		{
			result = result^arr[i];		//位运算
		}
		return result;
	}
	
<<<<<<< HEAD
	/**
	 * 孤单的数，3n+1
	 * 重写了异或方法,普通的异或可以理解为 （1+1）% 2 = 0 ， （1+0）% 2 = 1，则新异或方法可以写成 （ 1+1+1）%3 = 0
	 * @param arr
	 * @return
	 */
	public static int getAloneNumber2(int[] arr)
	{
		String result = getBinaryString( arr[0]);
		for(int i=1; i<arr.length; i++)
=======
	//孤单的数，3n+1
	public static int getAloneNumber2(int[] arr)
	{
		String result = getBinaryString(0);
		for(int i=0; i<arr.length; i++)
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
		{			
			result = xor(result,getBinaryString(arr[i]));
		}
		return Integer.valueOf(result, 2);
	}
	
<<<<<<< HEAD
	/**
	 * 二进制加法
	 * @param x
	 * @param y
	 * @return
	 */
=======
	//二进制加法
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	public static int add(int x, int y) {
	    if (y == 0) return x;
	    int sum, carry;
	    sum = x ^ y;
	    carry = (x & y) << 1;
	    return add(sum, carry);
	  }

<<<<<<< HEAD
	/**
	 * 模拟异或操作
	 * @param binaryD1
	 * @param binaryD2
	 * @return
	 */
=======
	//模拟异或操作
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
	private static String xor(String binaryD1, String binaryD2) 
	{
		String resultBinaryString = "";
		int index = 0;
		while(index < binaryD1.length() && index < binaryD2.length())
		{
			resultBinaryString +=  ((int)binaryD1.charAt(index) + (int)binaryD2.charAt(index)) % 3;
			index ++;
		}		
		return resultBinaryString;
	}

<<<<<<< HEAD
	/**
	 * 得到 32 位 二进制
	 * @param d1
	 * @return
	 */
	private static String getBinaryString(int d1) 
	{
		if(d1 == 0)
			return "00000000000000000000000000000000";
		String binaryString = Integer.toBinaryString(d1);
		int len = 32 - binaryString.length();
=======
	//得到 24 位 二进制
	private static String getBinaryString(int d1) 
	{
		if(d1 == 0)
			return "000000000000000000000000";
		String binaryString = Integer.toBinaryString(d1);
		int len = 24 - binaryString.length();
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
		String prefix = "";
		if(d1 >  0)
		{			
			while(len > 0)
			{
				prefix += '0';
				len --;
			}
		}else{
			while(len > 0)
			{
				prefix += '1';
				len --;
			}
		}
		return prefix+binaryString;
	}
}
