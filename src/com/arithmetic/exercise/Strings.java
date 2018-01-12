package com.arithmetic.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.arithmetic.thought.DP2;


public class Strings {
		
	/*
	 * 给定字符串，求该字符串中最长的回文串，暴力方式
	 */
	//给定一个字符串，获取该字符串中最长的回文串
	public static String longestPalindromic(String target)
	{
		if(target == null || "".equals(target)) return "";
		if(target.length() == 1) return target;
		
		List<String> arr = getAllSubString(target,true);
		String palindromic = "";
		for(int i=0; i<arr.size(); i++)
		{
			if(DP2.isPalindromic(arr.get(i)) && arr.get(i).length() > palindromic.length())
			{
				palindromic = arr.get(i);
			}
		}
		return palindromic;
	}
	
	//居然没有用到递归，这里用到了动态规划的思想
	
	/*
	 * 给定字符串，求该字符串中最长的回文串，动态规划
	 */
	public static String longestPalindromicByRecursion(String target)
	{
		if(target == null || "".equals(target)) return "";
		if(target.length() == 1) return target;
		String longest = "";
		for(int i=0; i<target.length(); i++)//计算以第i个字符为末尾字符的 最长回文串长度
		{
			for(int j=i; j>=0; j--)
			{
				String sub = target.substring(j, i+1);
				if(DP2.isPalindromic(sub) && sub.length() > longest.length())
				{
					longest = sub;
				}
			}
		}
		return longest;
	}
	//判断字符串中的字符是否均唯一出现
	
	/*
	 * 给定一个字符串，如何判断该字符串中没有重复字符
	 */
	public static boolean isUnique(String target)
	{
		HashSet<Character> set = new HashSet<Character>();
		for(int i=0; i<target.length(); i++)
		{
			set.add(target.charAt(i));
		}
		return target.length() == set.size();
	}
	
	//字符串排序
	
	/*
	 * 给定字符串，让字符串中字符按顺序排列
	 */
	public static String stringSort(String target)
	{
		char[] arr = target.toCharArray();
		for(int i=0; i<arr.length-1; i++)
		{
			for(int j=0; j<arr.length -1- i ; j++)
			{
				if(compare(arr[j], arr[j+1]) > 0)
				{
					char t = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = t;
				}
			}
		}
		return new String(arr);
	}
	
	//重构字符串比较
	private static int compare(char c, char d) 
	{
		if(Character.isUpperCase(c) == Character.isUpperCase(d))
		{
			return Character.compare(c, d);
		}else if(Character.isUpperCase(c)){
			return 1;
		}else if(Character.isUpperCase(d)){
			return -1;
		}
		return 0;
	}
		
	/*
	 * 给定2个字符串，求最大公共子序列
	 */
	public static String getMaxPublicSubSequence(String one, String another)
	{
		List<String> oneSubStrings = getAllSubString(one,false);
		List<String> anotherSubStrings = getAllSubString(another,false);
		String maxPublicSubString = "";
		if(oneSubStrings != null && anotherSubStrings != null )
		{
			for(String oneSub : oneSubStrings)
			{
				if(anotherSubStrings.contains(oneSub) && oneSub.length() > maxPublicSubString.length())
				{
					maxPublicSubString = oneSub;
				}
			}
		}
		return maxPublicSubString;
	}
	
	
	/*
	 * 给定2个字符串，求最大公共子串	
	 */
	public static String getMaxPublicSubString(String one, String another)
	{
		List<String> oneSubStrings = getAllSubString(one,true);
		List<String> anotherSubStrings = getAllSubString(another,true);
		String maxPublicSubString = "";
		if(oneSubStrings != null && anotherSubStrings != null )
		{
			for(String oneSub : oneSubStrings)
			{
				if(anotherSubStrings.contains(oneSub) && oneSub.length() > maxPublicSubString.length())
				{
					maxPublicSubString = oneSub;
				}
			}
		}
		return maxPublicSubString;
	}
	
	//得到字符串的所有子串，传入是否连续参数
	
	/*
	 * 获取给定字符串的所有子串（子串 和 子序列不一样，子串是连续的字符）
	 */
	public static List<String> getAllSubStringByRecursion(String source)
	{
		if(source == null || "".equals(source)) return null;
		List<String> result = new ArrayList<String>();
		if(source.length() == 1)
		{
			result.add(source);
			return result;
		}
		List<String> subResult = getAllSubStringByRecursion(source.substring(0, source.length()-1));
		if(subResult != null && !subResult.isEmpty())
		{
			result.addAll(subResult);
			for(int i=source.length()-1; i>=0; i--)
			{				//判断是否有重复
				if(!result.contains(source.substring(i)))
				{
					result.add(source.substring(i));
				}
				
			}
		}
		return result;
	}
	
	/*
	 * 获取给定字符串的 所有子序列 或者子串，子串必需是连续的
	 * Continuous：如果为true，表示是获取子串。
	 */
	public static List<String> getAllSubString(String source,boolean Continuous)
	{
		if(source == null) return null;
		List<String> result = new ArrayList<String>();
		if(source.equals("")) 
		{
			result.add("");
			return result;
		}
		if(source.length() == 1)
		{
			result.add("");
			result.add(source);
			return result;
		}
		List<String> subStrings = getAllSubString(source.substring(0,source.length()-1),Continuous);
		String last = source.substring(source.length()-1);
		if(subStrings != null)
		{
			result.addAll(subStrings);
			for(String sub : subStrings)
			{			
				if(!result.contains(sub+last))
				{
					if(Continuous)
					{
						if(source.contains(sub+last))
						{
							result.add(sub+last);
						}	
					}else{
						result.add(sub+last);
					}
				}
			}
		}
		return result;
	}
	
	//求公共前缀

	/*
	 * 获取给定2个字符串的公共前缀
	 */
	public static String getCommonPrefix(String str1,String str2)
	{
		if(str1 == null || "".equals(str1) || str2 == null || "".equals(str2) )
			return "";
		String commonPrefix = "";
		for(int i=0; i<str1.length(); i++)
		{
			if(str2.indexOf(str1.substring(0, i+1)) == 0)
			{
				commonPrefix = str1.substring(0, i+1);
			}
		}
		return commonPrefix;
	}
	
	
	//判断 s3 是不是 由s1 s2交错构成
		
	//旋转字符串
/*
 	* 	给定字符串，将前offset个字符移到到末尾 
 	*/
	public static String shiftString(String str , int offset)
	{
		if(offset < 0) return null;
		offset = offset % str.length();
		int len = str.length();		
		return str.substring(len - offset-1)+str.substring(0, len-offset-1);
	}
	
	
	/*
	 * 给定字符串，旋转前offset个字符
	 */
	public static String rotateString (String str,int offset)
	{
		if(str == null || "".equals(str)) return "";
		if(offset > str.length()) return "";
		if(offset == 1) return str.substring(0,offset);
		return str.substring(offset-1,offset)+rotateString(str.substring(0, offset-1),offset-1)
		+str.substring(offset);
	}	
	
}
