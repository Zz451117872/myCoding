package com.arithmetic.thought;

import java.util.Arrays;

/*
 * 动态规划
 */
public class DP {		
	
	/**
	 * 钢条切割问题：至底向上
	 * 有一根很长的钢条，不同尺寸的钢条价格不一样，如何切目标钢条使收益最大化
	 * @param p :不同尺寸钢条对应的价格
	 * @param n :需要切割钢条的长度
	 * @return
	 */
	public static Solution bottom_up_cut( int[] p, int n )
	{	
		//dp[x] 表示 x米长的钢条的最佳切割方案
		Solution[] dp = new Solution[ n + 1 ]; 
		dp[0] = new Solution(0,""); //0米长的钢条，收益为0
		
		for(int j=1; j<=n; j++)//从1开始向上递推
		{
			Solution solution = new Solution(Integer.MIN_VALUE,"");
			for(int i=1; i<=j; i++)
			{
				if(i >= p.length) //如果 i 超出了 p的范围，
				{			
					if(i == j) continue;
					solution = Solution.max(solution, Solution.add(dp[i], dp[j-i]));
				}else
				{
					if(p[i] < 0) continue;
					solution = Solution.max(solution, Solution.add(i, p[i], dp[j-i]));
				}						
			}
			dp[j] = solution;
		}		
		return dp[n];
	}
	
	//钢条切割 解决方案类
	static class Solution{
		private int maxValue;	//方案实现的最大收益
		private String solution;	//方案具体实现，如：8，8，2，意思是切成3段，第一段8，第二段8，第三段2.
		
		public Solution(){}
		
		public Solution(int value,String solution)
		{
			this.maxValue = value;
			this.solution = solution;
		}
		//方案比较
		public static Solution max(Solution s1,Solution s2)
		{
			if(s1.getMaxValue() > s2.getMaxValue())
			{
				return s1;
			}else{
				return s2;
			}
		}
		//方案相加
		public static Solution add(Solution s1,Solution s2)
		{
			Solution solution = new Solution(0,"");;
			if( s1 != null && s2 != null )
			{				
				solution.setMaxValue( s1.getMaxValue() + s2.getMaxValue() );
				solution.setSolution( s1.getSolution() + s2.getSolution() );
			}
			return solution;
		}
		
		//方案相加
		public static Solution add( int index , int value, Solution s1 )
		{
			Solution solution = new Solution();
			solution.setMaxValue( s1.getMaxValue() + value );
			if( !"".equals( s1.getSolution() )){
				solution.setSolution(s1.getSolution()+index+",");
			}else{
				solution.setSolution( index + ",");
			}
			return solution;
		}
		
		public int getMaxValue() {
			return maxValue;
		}
		
		public void setMaxValue(int maxValue) {
			this.maxValue = maxValue;
		}
		
		public String getSolution() {
			return solution;
		}
		
		public void setSolution(String solution) {
			this.solution = solution;
		}

		@Override
		public String toString() {
			return this.maxValue+" : "+this.solution;
		}				
	
	}
	
	/**
	 * 钢条切割：至上向下
	 * @param p
	 * @param n
	 * @return
	 */
	public static Solution memoized_cut_rod(int[] p,int n)
	{
		//dp[x] 表示 x米长的钢条的最佳切割方案
		Solution[] dp = new Solution[n+1];	
		dp[0] = new Solution(0,"");		//0米长的 价值为0
		return memoized_cut_rod_aux(p,dp,n);
	}

	private static Solution memoized_cut_rod_aux(int[] p, Solution[] dp, int n) 
	{
		if(dp[n] != null && dp[n].getMaxValue() >= 0){
			return dp[n];	
		}
		Solution solution = new Solution(Integer.MIN_VALUE,"");
		for(int i=1; i<=n; i++) //每隔1米，切一个
		{
			if(i >= p.length)
			{			
				if( i == n) continue;
				solution = Solution.max(solution, Solution.add(dp[i], memoized_cut_rod_aux( p, dp, n-i)));
			}else
			{
				if(p[i] < 0) continue;
				solution = Solution.max(solution, Solution.add(i, p[i], memoized_cut_rod_aux(p,dp,n-i)));
			}
					
		}
		dp[n] = solution;
		return solution;
	}

	
	/**
	 * 背包问题 ：求能装的最大价值 
	 * 每个物品只能装一次
	 * @param space :指定空间大小
	 * @param goods :物品所占的空间
	 * @param value :物品对应的价值
	 * @return
	 */
	public static int maxLoadValue(int space,int[] goods,int[] value)
	{
		int row = goods.length+1;
		int column = space+1;
		//dp[x][y] 表示 y的空间用前x个物品来装，可装入的最大价值
		int[][] dp = new int[row][column];
		//当x为0 或者 y为0时，可以装入的价值都为0
		for(int i=0; i<row; i++)	
		{
			dp[i][0] = 0;
		}
		for(int i=0; i<column; i++)
		{
			dp[0][i] = 0;
		}
		//至底向上 递推， i表示前几个物品，ｊ则表示背包的容量
		for(int i=1; i<= goods.length; i++)
		{
			for(int j=1; j<=space ;j++)
			{
				dp[i][j] = 0;
				int index = i;
				//这里的判断，意思是当前的空间不足以装下当前物品时，可以试装更小的物品
				while(index >= 1 && j < goods[index -1] )
				{
					index--;
				}
				//使用前i 个或者前i-1个物品，分别试装，取最大值
				if(j >= goods[index -1])
				{						//每个物品只能装载一次
					dp[i][j] =Math.max( value[index-1] + dp[index-1][j - goods[index-1]]
							,dp[index-1][j]);
				}		
			}
		}
		
		return dp[row-1][column-1];
	}
	
	
	/**
	 * 背包问题，最多能装进多少空间
	 * @param goods
	 * @param space
	 * @return
	 */
	public static int loadMaxSpace(int[] goods,int space)
	{
		int row = goods.length+1;
		int column = space+1;
		//dp[x][y] 表示 y个空间 用前 x个物品来装，可装入的最大空间
		int[][] dp = new int[row][column];
		//当 y 为0 或者 x为0时，可装入空间都为0
		for(int i=0; i<row; i++)	
		{
			dp[i][0] = 0;
		}
		for(int i=0; i<column; i++)
		{
			dp[0][i] = 0;
		}
		//至底向上 递推， i表示前几个物品，ｊ则表示背包的容量
		for(int i=1; i<= goods.length; i++)
		{
			for(int j=1; j<=space ;j++)
			{
				dp[i][j] = 0;
				int index = i;
				//这里的判断，意思是当前的空间不足以装下当前物品时，可以试装更小的物品
				while(index >= 1 && j < goods[index -1] )
				{
					index--;
				}
				//使用前i 个或者前i-1个物品，分别试装，取最大值
				if(j >= goods[index -1])
				{		
					dp[i][j] =Math.max( 
							goods[index-1] + dp[index-1][j - goods[index-1]]
							,dp[index-1][j]);
				}		
			}
		}
		return dp[row-1][column-1];
	}

	
	/**
	 * 硬币游戏
	 * 有两个人拿硬币，一次拿一个或者两个，谁价值高谁赢
	 * @param value
	 * @return
	 */
	public static boolean down_up_coinValuePlay(int[] value)
	{
		if(value.length <= 2) return true;
		//dp[i] 表示从i到end 可获取的最大价值，有包括i
		int[] dp = new int[value.length + 1];		
		//当 i==end时，没有东西拿了，所以 为0
		dp[value.length] = 0;	
		//当 i==end-1时，只有最后1个硬币可以拿，所以直接拿
		dp[value.length - 1] = value[value.length-1];	
		//当 i==end-2时，有2个硬币可以拿，所以直接全部拿
		dp[value.length - 2] = value[value.length -2]+value[value.length-1];
		//当 i==end-3时，有3个硬币可以拿，价值最大化，所以拿2个
		dp[value.length -3] = value[value.length -3]+value[value.length-2];

		//当i = len-4以及以后的情况中，显然可以选择拿一个或者拿两个两种情况，我们自然是选择拿最多的那个作为dp的值，那么我们就分分析这两种情况：
		//第一种，只拿一个,那么对手可能拿两个或者一个，对手肯定是尽可能多拿，对方所选取的结果一定是使得我们以后选取的值最小,所以我们要选择尽可能小的那个，
		//所以dp[i] = values[i] + min(dp[i+2],dp[i+3])
		//第二种，拿两个，同样的情况，dp[i] = values[i]+ values[i+1]+min(dp[i+3],dp[i+4])
		//然后我们取这两种情况下的最大值。
		for(int i=value.length - 4; i>=0; i--)
		{
			dp[i] = Math.max(value[i]+Math.min(dp[i+2], dp[i+3])
			                 , value[i]+value[i+1]+Math.min(dp[i+3], dp[i+4]));
		}
		int sum = 0;
		for(int i=0; i<value.length; i++)
		{
			sum += value[i];
		}
		System.out.println("sum:"+sum);
		System.out.println("dp[0]:"+dp[0]);
		return dp[0] > sum-dp[0];
	}
	
	
	/**
	 * 硬币游戏
	 * 有两个人拿硬币，一次拿一个或者两个，谁拿完为赢
	 * @param n
	 * @return
	 */
	public static boolean coinPlayByDynamic(int n)
	{
		if( n <= 2) return true;
		//dp[x] 表示 有x个硬币时，第一个人是否赢
		int[] dp = new int[n+1];
		dp[1] = 1;		//当只有1个或者2个硬币时，第一个人赢
		dp[2] = 1;
		for(int i=3; i<dp.length; i++)
		{
			dp[i] = 0;
		}
		return coinPlayByDynamicAux(dp,n);
	}
	
	private static boolean coinPlayByDynamicAux(int[] dp, int n) 
	{
		if(dp[n] != 0)
		{
			return dp[n] == 1;
		}	
		//如果第一个人拿掉1个或者2个后，第二个人都能赢，则第一个人输
		if( coinPlayByDynamicAux(dp,n-1) && coinPlayByDynamicAux(dp,n-2) )
		{
			dp[n] = -1;
		}else
		{
			dp[n]= 1;
		}
		return dp[n] == 1;
	}
	
	/**
	 * 硬币游戏 至底向上
	 * @param n
	 * @return
	 */
	public static boolean down_up_coinPlay(int n)
	{
		if(n <= 2) return true;
		//dp[x]表示 有x个硬币时，第一个人是否赢
		int[] dp = new int[n+1];
		dp[1] = 1;  //当只有1个硬币或者2个时，第一个人赢
		dp[2] = 1;
		for(int i=3; i<dp.length; i++)
		{
			dp[i] = 0;
		}
		
		//从第3开始 往上递推
		for(int i=3; i<=n; i++) 
		{
			if(dp[i-1] == 1 && dp[i-2]==1)
			{
				dp[i] = -1;
			}else{
				dp[i] = 1;
			}
		}
		return dp[n] == 1;
	}
	
	/**
	 * 硬币游戏 纯递归
	 * @param n
	 * @return
	 */
	public static boolean coinPlayByRecursion(int n)
	{
		if( n <= 2) return true;
		if(n == 3) return false;
		return !( coinPlayByRecursion(n-1) && coinPlayByRecursion(n-2) );
	}
}
