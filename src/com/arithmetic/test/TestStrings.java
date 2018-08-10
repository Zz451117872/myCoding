package com.arithmetic.test;

import java.util.List;

import org.junit.Test;

import com.arithmetic.CommonTool;
import com.arithmetic.exercise.Strings;

public class TestStrings {
	
	@Test
	public void longestPalindromicByRecursion_test()
	{
		System.out.println("---longestPalindromicByRecursion_test------------------------------------");
		String str = CommonTool.createString(8);
		String longest = Strings.longestPalindromicByRecursion(str);
		System.out.println("str:"+str);
		System.out.println("longest:"+longest);
	}
	
	@Test
	public void getAllSubStringByRecursion_test()
	{
		System.out.println("---getAllSubStringByRecursion_test------------------------------------");
		String str = CommonTool.createString(3);
		List<String> substrings = Strings.getAllSubStringByRecursion(str);
		System.out.println("str:"+str);
		printStringList(substrings);
	}
	
	@Test
	public void getAllSubString_test()
	{
		System.out.println("---getAllSubString------------------------------------");
		String str = CommonTool.createString(3);
		List<String> substrings = Strings.getAllSubString(str,true);
		System.out.println("str:"+str);
		printStringList(substrings);
	}
	
	@Test
	public void getAllSubSequence_test()
	{
		System.out.println("---getAllSubSequence_test------------------------------------");
		String str = CommonTool.createString(3);
		List<String> substrings = Strings.getAllSubString(str,false);
		System.out.println("str:"+str);
		printStringList(substrings);
	}
	
	@Test
	public void getCommonPrefix_test()
	{
		System.out.println("---getCommonPrefix_test------------------------------------");
		String str1 = CommonTool.createString(5);
		String str2 = CommonTool.createString(7);
		String commonPrefix = Strings.getCommonPrefix(str1,str2);
		System.out.println("str1:"+str1);
		System.out.println("str2:"+str2);
		System.out.println("commonPrefix:"+commonPrefix);		
	}
	@Test
	public void shiftString()
	{
		System.out.println("---shiftString------------");
		String str = CommonTool.createString(7);
		System.out.println("str:"+str);
		System.out.println("shiftString:"+Strings.shiftString(str, 3));
	}
	
	@Test
	public void rotateString_test()
	{
		System.out.println("---rotateString_test------------");
		String str = CommonTool.createString(7);
		System.out.println("str:"+str);
		System.out.println("rotateString:"+Strings.rotateString(str,8));
	}
	
	private void printStringList(List<String> substrings) {
		if(substrings != null && !substrings.isEmpty())
		{
			for(int i=0; i<substrings.size(); i++)
			{
				System.out.println(substrings.get(i));
			}
		}
	}
}
