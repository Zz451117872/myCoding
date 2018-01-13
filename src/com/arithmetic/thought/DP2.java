package com.arithmetic.thought;

import java.util.Arrays;

import com.arithmetic.test.TestDP2;

public class DP2 {

	/*
	 * 数塔问题：从指定的坐标点往数塔底层遍历所得的最大值
	 * tower:传入的数塔，一个二维数组
	 * x,y:需要检查的坐标点
	 */
	//数塔问题 至底向上 
	public static int down_up_digitTower(int[][] tower,int x,int y)
	{
		int row = tower.length;
		int[] dp = new int[row];//存储当前最大值结果
		for(int i=0; i<dp.length; i++)
		{
			dp[i] = tower[row-1][i];
		}
		for(int i=row-2; i>=x; i--)//从倒数第二层开始循环
		{
			for(int j=0; j<tower[i].length; j++)
			{			//
				dp[j] = Math.max(dp[j],dp[j+1])+tower[i][j];					
			}
			System.out.println(Arrays.toString(dp));
		}		
		
		return dp[y];
	}
	
	//数塔问题 递归
	public static int digitTowerByRecursion(int[][] tower, int x ,int y)
	{
		int[][] dp = new int[tower.length][];
		for(int i=0; i<dp.length; i++)
		{
			int[] row = new int[tower[i].length];
			dp[i] = row;
			for(int j=0; j<dp[i].length; j++)
			{
				dp[i][j] = 0;
			}
		}		
		return digitTowerByRecursion_aux(dp,tower,x,y);
	}
	
	private static int digitTowerByRecursion_aux(int[][] dp, int[][] tower, int x, int y) 
	{		
		try{			
			int a=dp[x][y];	// 检查输入的坐标是否正常
		}catch(Exception e)
		{
			return 0;
		}
		if(dp[x][y] != 0)
		{
			return dp[x][y];
		}
		if(x == tower.length-1)//如果是最后一行，则直接赋值并返回
		{
			dp[x][y] = tower[x][y];
		}else{				//如果不是最后一行，
			dp[x][y] = Math.max(digitTowerByRecursion_aux(dp,tower,x+1,y), 
					digitTowerByRecursion_aux(dp,tower,x+1,y+1))
					+tower[x][y];
		}
		return dp[x][y] ;
	}

	/*
	 * 公共子串问题
	 */
	///最大公共子序列问题-------------------------------------------------------------------
	public static int longestCommonSubSequence(String s1, String s2)
	{	//dp[x][y] s1前x个字符 与 s2前y个字符 的最长公共子序列的长度
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for(int i=0; i<dp.length; i++)
		{	
			for(int j=0; j<dp[i].length; j++)
			{	//当x,y为0时，最长公共子序列为0
				if(i == 0 || j == 0)
				{
					dp[i][j] = 0;
				}
			}
		}		
		for(int i=1; i<=s1.length(); i++)
		{
			for(int j=1; j<=s2.length(); j++)
			{	//当s1第x个字符 与 s2第y个字符 相等时
				if(s1.charAt(i-1) == s2.charAt(j-1))
				{
					dp[i][j]= dp[i-1][j-1] +1;
				}else{
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
		
	//最长公共子串问题------------------------------------------------------------------------
	public static int longestCommonSubString(String s1,String s2)
	{	//dp[x][y] 表示 以x结尾的s1的子串  与 以y结尾的s2的子串 的 最长公共子串的长度
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for(int i=0; i<dp.length; i++)
		{	//x,y为0时，最长公共子串为0
			for(int j=0; j<dp[i].length; j++)
			{
				if(i == 0 || j == 0)
				{
					dp[i][j] = 0;
				}
			}
		}
		int maxSubString = 0;
		for(int i=1; i<=s1.length(); i++)
		{
			for(int j=1; j<=s2.length(); j++)
			{		
				if(s1.charAt(i-1) == s2.charAt(j-1))
				{	//x,y相等时，最长公共子串
					dp[i][j]= dp[i-1][j-1] +1;	
					if(dp[i][j] > maxSubString)
					{
						maxSubString = dp[i][j];
					}
				}else{//x,y不相等时，最长公共子串为0
					dp[i][j] = 0;
				}
			}
		}	
		return maxSubString;
	}
		
	////最长非降序子序列问题-----------------------------------------------------------------
	public static int longestIncrementSubSequence(int[] arr)
	{
		if(arr == null || arr.length < 1) return 0;
		if(arr.length == 1) return 1;
		//dp[x] 表示 以第x个元素结尾的自增子序列的长度
		int[] dp = new int[arr.length+1];		
		dp[0] = 0;	//以第0个元素结尾的自增子序列为0
		dp[1] = 1;	//以第1个元素结尾的自增子序列为1
		for(int i=2; i<=arr.length; i++)
		{		
			dp[i] = 1;
			for(int k=1; k<i; k++)
			{							
				if(arr[i-1] > arr[k-1])
				{	//当。。
					dp[i] = Math.max(dp[i], dp[k]+1);					
				}
			}			
		}
		Arrays.sort(dp);		
		return dp[arr.length];
	}

	/*
	 * 回文数：给定一个字符串，用最少的切割次数把该字符串分割成多个回文串段。
	 */
	//分割回文数 递归方式
	public static int partitionPalindromic(String palindromic)
	{
		if(palindromic == null ||  "".equals(palindromic) ||palindromic.length() == 1) 
			return 0;
		//dp[x]表示 字符串前x个字符 最少需要切割的次数
		int[] dp = new int[palindromic.length()+1];
		dp[0] = 0;
		dp[1] = 0;
		for(int i=2; i<dp.length; i++)
		{
			dp[i] = -1;
		}		
		return  partitionPalindromicAux(dp,palindromic,palindromic.length());
	}
	
	private static int partitionPalindromicAux(int[] dp, String palindromic, int end) 
	{
		if(end < 0 || end > palindromic.length()) return 0;
		if(dp[end] != -1)
		{
			return dp[end];
		}
		String target = palindromic.substring(0,end);
		if(isPalindromic(target))//如果待切割字符串是一个回文串，则直接返回
		{
			dp[end] = 0;
			return dp[end];
		}						//得到末尾最长回文串的长度
		int longest = getLongestTailPalindromic(target);
		if(longest > 0)
		{						//如果待切割字符串末尾是有回文串的，则试着切掉回文串 或者 只切1个字符，看谁小
			dp[end] = Math.min(partitionPalindromicAux(dp,palindromic,end-longest)+1, 
					partitionPalindromicAux(dp,palindromic,end-1)+1);
		}else{					//如果待切割字符串末尾没有回文串，则切掉末尾1个字符
			dp[end] = partitionPalindromicAux(dp,palindromic,end-1)+1;
		}
		return dp[end];
	}
	
	//分割回文数 纯递归:与 动态规划的方式是一样的，但动态规划缓存了子问题的，提高了效率
	public static int partitionPalindromicByRecursion(String s,int start, int end)
	{
		if(start == end) return 0;
		if(isPalindromic(s.substring(start,end+1)))
		{
			return 0;
		}else{
			int longest = getLongestTailPalindromic(s.substring(start,end+1));
			if(longest > 0)
			{
				return Math.min(partitionPalindromicByRecursion(s,start,end-longest)+1, 
						partitionPalindromicByRecursion(s,start,end-1)+1);
			}else{
				return partitionPalindromicByRecursion(s,start,end-1)+1;
			}
		}
	}
		
	//判断回文数
	public static boolean isPalindromic(String sub) {
		if(sub == null || "".equals(sub))
			return false;
		if(sub.length() == 1)
			return true;
		int start = 0;
		int end = sub.length()-1;
		while(start < end)
		{
			if(sub.charAt(start) != sub.charAt(end))
			{
				return false;
			}
			start ++;
			end --;
		}
		return true;
	}
		
	//分割回文数 递推方式
	public static int down_up_partitionPalindromic(String palindromic)
	{
		if(palindromic == null || "".equals(palindromic) || palindromic.length() == 1) return 0; 
		//dp[x] 表示待切割字符串前x个字符 最小需要切割的次数
		int[] dp = new int[palindromic.length()+1];
		dp[0] = 0;		//前0个字符，切0次
		dp[1] = 0;		//前1个字符，切0次		
		//从第2个字符开始，每隔一个字符切一刀
		for(int i=2; i<=palindromic.length(); i++)
		{
			String target = palindromic.substring(0, i);
			if(isPalindromic(target))//若被切下的字符串是回文数，则可结束本轮循环
			{
				dp[i] = 0; continue;
			}				//获取待切割字符串末尾最长回文串的长度
			int longest = getLongestTailPalindromic(target);
			if(longest >0){	//如果待切字符串末尾有回文串，则试着切掉回文串 或者 只切1个字符，看谁小
				dp[i] = Math.min(dp[i-longest] + 1, dp[i-1] +1);			
			}else{				//如果待切字符串末尾没有回文串，则只切1个字符
				dp[i] = dp[i-1]+1;
			}
		}		
		return dp[palindromic.length()];
	}
	
	//返回字符串 末尾回文数的长度
	public static int getLongestTailPalindromic(String palindromic)
	{
		int result = 0; //从字符串的末尾一直往前切，当切掉的子串是回文数时，更新结果值
		for(int i=palindromic.length()-1; i>=0; i--)
		{
			if(isPalindromic(palindromic.substring(i)))
			{
				result =Math.max(result, palindromic.substring(i).length());
			}
		}
		return result;
	}

	/*
	 * 硬币分配
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
}
