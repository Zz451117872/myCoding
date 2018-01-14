package com.arithmetic.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.arithmetic.entry.MajorityEntry;
import com.arithmetic.exercise.Maths;

public class TestMaths {
	
	
	
	@Test
	public void majorityNumberHalf()
	{
		System.out.println("--majorityNumberHalf------------------");
		int[] arr = new int[]{1,2,2,1,5,3,1,2,1,1,1};
		int majorityNumber = Maths.majorityNumberHalf(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("majorityNumber:"+majorityNumber);
		System.out.println("");
	}

	
	@Test
	public void majorityNumberK()
	{
		System.out.println("--majorityNumberK------------------");
		int[] arr = new int[]{1,2,2,3,5,2,3,2,1,3};
		int majorityNumber = Maths.majorityNumberK(arr,3);
		System.out.println(Arrays.toString(arr));
		System.out.println("majorityNumber:"+majorityNumber);
		System.out.println("");
	}
}
