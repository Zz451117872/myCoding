<<<<<<< HEAD
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
=======
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
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
