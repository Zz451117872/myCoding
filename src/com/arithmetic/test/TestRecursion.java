package com.arithmetic.test;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import com.arithmetic.thought.Recursion;

public class TestRecursion {

	static Random random = new Random();
	@Test
	public void arrange_test()
	{
		ArrayList<Integer> digits = createIntegerList(3,20,false);
		printIntegerList(digits);
		System.out.println("---arrange_test----------------------------------");
		ArrayList<ArrayList<Integer>> result  = Recursion.arrange(digits);
		printIntegerListList(result);
		System.out.println("---arrange_test----------------------------------");
	}
	
	@Test
	public void arrangeRepeat_test()
	{
		ArrayList<Integer> digits = createIntegerList(4,5,true);
		printIntegerList(digits);
		System.out.println("---arrangeRepeat_test----------------------------------");
		ArrayList<ArrayList<Integer>> result  = Recursion.arrangeRepeat(digits);
		printIntegerListList(result);
		System.out.println("---arrangeRepeat_test----------------------------------");
	}
	
	@Test
	public void getSubList_test()
	{
		ArrayList<Integer> digits = createIntegerList(4,5,false);
		printIntegerList(digits);
		System.out.println("---getSubList----------------------------------");
		ArrayList<ArrayList<Integer>> result  = Recursion.getSubList(digits);
		printIntegerListList(result);
		System.out.println("---getSubList----------------------------------");
	}
	
	@Test
	public void getSubListRepeat_test()
	{
		ArrayList<Integer> digits = createIntegerList(4,5,true);
		printIntegerList(digits);
		System.out.println("---getSubListRepeat----------------------------------");
		ArrayList<ArrayList<Integer>> result  = Recursion.getSubListRepeat(digits);
		printIntegerListList(result);
		System.out.println("---getSubListRepeat----------------------------------");
	}
	
	
	private void printIntegerListList(ArrayList<ArrayList<Integer>> result)
	{
		if(result != null && !result.isEmpty())
		{
			for(int i=0; i<result.size(); i++)
			{
				printIntegerList(result.get(i));
			}
		}
	}

	private void printIntegerList(ArrayList<Integer> result)
	{
		if(result != null &&  !result.isEmpty())
		{
			for(int i=0; i<result.size(); i++)
			{
				System.out.print(result.get(i)+"  ");
			}
			System.out.println("");
		}
	}
	
	private ArrayList<Integer> createIntegerList(int length,int range,boolean repeat) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<length; i++)
		{
			int digit = random.nextInt(range);
			if(!repeat)
			{
				while(result.contains(digit))
				{
					digit = random.nextInt(range);
				}
			}
			result.add(digit);
		}
		return result;
	}
}
