package com.dataStructure.dynamicPlan;

import java.util.Arrays;

public class DynamicPlans {
	public static int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
	public static int[] i = new int[]{0,1,2,3,4,5,6,7,8,9,10};
	
	public static void main(String[] str)
	{
		int[] weight = new int[]{2,1,3,2};
		int[] value = new int[]{3,2,4,2};
		int load = 5;
		System.out.println(maxLoad(load,weight,value));
		
	}

	///////////背包问题
	//求能装的最大价值
	public static int maxLoad(int load,int[] weight,int[] value)
	{
		
		return 0;
	}

	////最长非降序子序列问题
	public static int getLongestIncrementSubSequence(int[] arr)
	{
		if(arr == null || arr.length < 1) return 0;
		int[] dp = new int[arr.length];
		for(int i=0; i<dp.length; i++)
		{
			dp[i] = 0;		
		}
		dp[0] = 1;
		for(int i=1; i<arr.length; i++)
		{		
			int longest = 1;
			for(int k=0; k<=i; k++)
			{							
				if(arr[i] > arr[k])
				{
					if(dp[k]+1 > longest)
					{
						longest = dp[k]+1;
					}
				}
			}			
			dp[i] = longest;
		}
		Arrays.sort(dp);
		return dp[arr.length-1];
	}
	
	
	
	
	
	
	///硬币分配 问题
	/*
	 * 　[思路] 从上面的分析中我们也可以这么考虑，我们希望用m种硬币构成sum，根据最后一个硬币Vm的系数的取值为无非有这么几种情况，xm分别取｛0, 1, 2, ..., sum/Vm｝，换句话说，上面分析中的等式和下面的几个等式的联合是等价的。
sum = x1 * V1 + x2 * V2 + ... + 0 * Vm
sum = x1 * V1 + x2 * V2 + ... + 1 * Vm
sum = x1 * V1 + x2 * V2 + ... + 2 * Vm
...
sum = x1 * V1 + x2 * V2 + ... + K * Vm  
　　其中K是该xm能取的最大数值K = sum / Vm。可是这又有什么用呢？不要急，我们先进行如下变量的定义：
dp[i][sum] = 用前i种硬币构成sum 的所有组合数。
　　那么题目的问题实际上就是求dp[m][sum]，即用前m种硬币（所有硬币）构成sum的所有组合数。在上面的联合等式中：当xn=0时，有多少种组合呢？ 实际上就是前i-1种硬币组合sum，有dp[i-1][sum]种！ xn = 1 时呢，有多少种组合？ 实际上是用前i-1种硬币组合成(sum - Vm)的组合数，有dp[i-1][sum -Vm]种; xn =2呢， dp[i-1][sum - 2 * Vm]种，等等。所有的这些情况加起来就是我们的dp[i][sum]。所以：
dp[i][sum] = dp[i-1][sum - 0*Vm] + dp[i-1][sum - 1*Vm]
+ dp[i-1][sum - 2*Vm] + ... + dp[i-1][sum - K*Vm]; 其中K = sum / Vm
	 */
	//使用 至顶向下 方式分配硬币
	public static int coinAllot(int sum)
	{
		int[] value = new int[]{1,2,5,10};
		int[][] dp = new int[value.length+1][sum+1];
		
		for(int i=0; i<=value.length; i++)
		{
			for(int k=0; k<=sum; k++)
			{
				dp[i][k] = 0;
			}
		}
		for(int i=0; i<=value.length; i++)
		{
			dp[i][0] = 1;
		}
		int count = 0;
		for(int i=0; i<value.length; i++)
		{
			if(sum >= value[i])
			{
				count++;
			}
		}
		return coinAllot_aux(count,sum,value,dp);
	}
		
	private static int coinAllot_aux(int count,int sum, int[] value, int[][] dp) 
	{		
		if(count < 0) return 0;
		if(dp[count][sum] > 0)
		{
			return dp[count][sum];
		}
		dp[count][sum] = 0;
		int coinValue = value[count-1 >= 0 ? count-1 : 0];
		for(int i=0; i<=sum/coinValue; i++)
		{
			dp[count][sum] += coinAllot_aux(count-1,sum-i*coinValue,value,dp);
		}
		return dp[count][sum];
	}
	//使用 至底向上 方式分配硬币
	public static int down_up_coinAllot(int sum)
	{
		int[] value = new int[]{1,2,5,10};
		int[][] dp = new int[value.length+1][sum+1];
		
		for(int i=0; i<=value.length; i++)
		{
			for(int k=0; k<=sum; k++)
			{
				dp[i][k] = 0;
			}
		}
		for(int i=0; i<=value.length; i++)
		{
			dp[i][0] = 1;
		}
		
		for(int i=1; i<=value.length; i++)
		{
			for(int j = 1; j<=sum; j++)
			{
				dp[i][j] = 0;
				for(int k=0; k<=j/value[i-1]; k++)
				{
					dp[i][j] += dp[i-1][j - k*value[i-1]];
				}
			}
		}
		
		return dp[value.length][sum];
	}
	
	
	
	
	

	

	///钢条切割问题
	//至底向上 方式 
	public static int bottom_up_cut(int[] p,int n)
	{
		int[] r = new int[n+1];
		r[0] = 0;
		for(int j=1; j<=n; j++)
		{
			int solution = Integer.MIN_VALUE;
			for(int i=1; i<=j; i++)
			{
				int max =0;
				if(i >= p.length)
				{			
					max = r[i] + r[j-i] ;
				}else
				{
					max = p[i]+r[j-i];
				}
				if(max > solution)
				{
					solution = max;
				}	
				
			}
			r[j] = solution;
		}
		return r[n];
	}
	
	//递归方式
	public static int memoized_cut_rod(int[] p,int n)
	{
		int[] r = new int[n+1];
		int[] cut = new int[n-1];
		for(int i=0; i<r.length; i++)
		{
			r[i] = Integer.MIN_VALUE;
		}
		for(int i=0; i<cut.length; i++)
		{
			cut[i] = 0;
		}
		return memoized_cut_rod_aux(p,r,n,cut);
	}

	private static int memoized_cut_rod_aux(int[] p, int[] r, int n, int[] cut) 
	{
		if(r[n] > 0)
			return r[n];
		if(n == 0)
			return 0;
		int solution = Integer.MIN_VALUE;
		for(int i=1; i<=n; i++)
		{
			int max =0;
			if(i >= p.length)
			{			
				max = r[i] + memoized_cut_rod_aux(p,r,n-i,cut);
			}else
			{
				max = p[i]+memoized_cut_rod_aux(p,r,n-i,cut);
			}
			if(max > solution)
			{
				solution = max;
			}			
		}
		r[n] = solution;
		System.out.println(Arrays.toString(r));;
		return solution;
	}
}
