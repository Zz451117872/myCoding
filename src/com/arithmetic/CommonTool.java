package com.arithmetic;

import java.util.Random;

public class CommonTool {

	static Random random = new Random();
	static char[] chars = new char[]{'A','B','C','D','e','f','j'};
	
	public static int[] createArray(int length,int scope)
	{
		int[] data = new int[length];
		for(int i=0; i<length; i++)
		{
			data[i] = random.nextInt(scope);
		}
		return data;
	}
	
	public static int[] createAlmostOrderly(int length,int Inversion)
	{
		int[] data = new int[length];
		for(int i=0; i<length; i++)
		{
			data[i] = i;
		}
		for(int i=0; i<Inversion; i++)
		{
			int a = random.nextInt(length);
			int b = random.nextInt(length);
			swap(data,a,b);
		}
		return data;
	}
	
	private static void swap(int[] data, int a, int b) {
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
	
	public static String createString(int length)
	{
		char[] charArr = new char[length];
		for(int i=0; i<charArr.length; i++)
		{
			charArr[i] = chars[random.nextInt(chars.length)];
		}
		return new String(charArr);
	}
}
