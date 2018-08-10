package com.arithmetic.thought;

import java.math.BigDecimal;

/*
 * 迭代算法思想
 */
public class Iteration {

	/**
	 * n的阶乖 结果的尾部的0的个数
	 * @param n
	 * @return
	 */
	public static int tailZero(int n)
	{
		int count =0;
		BigDecimal result = factorial(n);
		while(result.remainder(new BigDecimal(10)).equals(new BigDecimal(0)))
		{
			result = result.divide(new BigDecimal(10));
			System.out.println(result.toString());
			count ++;
		}
		return count;
	}
	
	/**
	 * 求阶乘 ：递归，有缺陷
	 * @param n
	 * @return
	 */
	public static BigDecimal factorial(int n)
	{		
		if(n == 1) return new BigDecimal(1);
		
		if(n > 1) return (new BigDecimal(n)).multiply(factorial(n-1));
		return null;
	}
}
