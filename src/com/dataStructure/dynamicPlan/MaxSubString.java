package com.dataStructure.dynamicPlan;

public class MaxSubString {

	public static void main(String[] str)
	{
		String s1 = "abcdefg";
		String s2 = "dabccojk";
		int max = getMaxSubString(s1,s2);
		System.out.println(max);
	}
	
	///最大公共子串问题
	public static int getMaxSubString(String s1, String s2)
	{
		int len1 = s1.length();
		int len2 = s2.length();
		int[][] max = new int[len1+1][len2+1];
		
		for(int i=0; i<=len1; i++)
		{
			max[i][0] = 0;
		}
		for(int i=0; i<=len2; i++)
		{
			max[0][i] = 0;
		}
		
		for(int i=1; i<=len1; i++)
		{
			for(int j=1; j<=len2; j++)
			{
				if(s1.charAt(i-1) == s2.charAt(j-1))
				{
					max[i][j]= max[i-1][j-1] +1;
				}else{
					max[i][j] = Math.max(max[i][j-1], max[i-1][j]);
				}
			}
		}
		return max[len1][len2];
	}
}
