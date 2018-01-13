package com.arithmetic.exercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 数学，计算相关
 */
public class Maths {	
	
	public static int getUglyDigitTh(int n)
	{
		int ugly = 1;
		int previous = 0;
		while(n > 0)
		{
			
		}
		return ugly;
	}
	
	//寻找{1,2,3,5}的第n 个丑数，丑数即公因子 只能是给定集合中的数
	public static int uglyDigit(int n)
	{
		int number = 1;
		Set<Integer> preme = new HashSet<Integer>();
		preme.add(1);preme.add(2);
		preme.add(3);preme.add(5);
		
		while(n > 0)
		{										//获取一个数的质因子
			Set<Integer> divisor = getPreme(number);	
			if(preme.containsAll(divisor))//从1开始循环，若是丑数，则计数减1.
			{
				n --;
			}
			number ++;
		}
		return number-1;
	}
	//获得一个数的 质因子
	public static Set<Integer> getPreme(int n)
	{
		if(n <= 0) return null;
		Set<Integer> divisor = new HashSet<Integer>();
		divisor.add(1);
		if( n == 1) return divisor;
		
		if(isPreme(n))
		{
			divisor.add(n);
			return divisor;
		}
		for(int i=2; i<n; i++)
		{
			if(n % i == 0)
			{
				int value = n / i;
				if(isPreme(i))
				{
					divisor.add(i);
				}else{
					divisor.addAll(getPreme(i));
				}
				if(isPreme(value))
				{
					divisor.add(value);
				}else{
					divisor.addAll(getPreme(value));
				}
			}
		}
		return divisor;
	}
	//判断一个数是否是质子
	public static boolean isPreme(int n)
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
	
	//从0 到 digit，统计 n 出现的次数
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
				
	//二进制加法
	public static int add(int x, int y) {
	    if (y == 0) return x;
	    int sum, carry;
	    sum = x ^ y;
	    carry = (x & y) << 1;
	    return add(sum, carry);
	  }

	//主元素 1/2
	public static int majorityNumberHalf(int[] arr)
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
		
	//主元素，达到总数的k分之一以上
	public static int majorityNumberK(int[] arr ,int k)
	{
		return 0;
	}
	
	//找到这个孤单的娃:2n+1

	//孤单的数，2n+1
	public static int getAloneNumber1(int[] arr)
	{
		int result = 0;
		for(int i=0; i<arr.length; i++)
		{
			result = result^arr[i];		//位运算
		}
		return result;
	}
	
	//找到这个孤单的娃:3n+1

	//孤单的数，3n+1
	public static int getAloneNumber2(int[] arr)
	{
		String result = getBinaryString(0);
		for(int i=0; i<arr.length; i++)
		{			
			result = xor(result,getBinaryString(arr[i]));
		}
		return Integer.valueOf(result, 2);
	}

	//模拟异或操作
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

	//得到 24 位 二进制
	private static String getBinaryString(int d1) 
	{
		if(d1 == 0)
			return "000000000000000000000000";
		String binaryString = Integer.toBinaryString(d1);
		int len = 24 - binaryString.length();
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

	//找到这个孤单的娃:2n+2

}
