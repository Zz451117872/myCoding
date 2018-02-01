package com.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
		public static void main(String[] srt)
		{
				String s = "abbaaaaaa";
				System.out.println(minCut(s));
		}
		
	    public static  int minCut(String s) {
	    		if( s == null || s.length() < 2) return 0;
	    		int[][] dp = new int[s.length()][s.length()];
	    		int n = s.length();
	    		for(int i=n-1; i>=0; i--)
	    		{
	    			for(int j=i+1; j<n; j++)
	    			{
	    				String subString = j == n ? s.substring(i) : s.substring(i, j+1);
	    				if(!isPalindromic(subString))
	    				{
	    					dp[i][j] = Integer.MAX_VALUE;
	    					for(int k=i; k<j; k++)
	    					{
	    						dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k+1][j]+1);
	    					}
	    				}
	    			}
	    		}
	    		for(int i=0; i<dp.length; i++)
	    		{
	    			System.out.println(Arrays.toString(dp[i]));
	    		}
	    		return dp[0][s.length()-1];
	    }
	    
	    public static  boolean isPalindromic(String sub) {
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

		
}
