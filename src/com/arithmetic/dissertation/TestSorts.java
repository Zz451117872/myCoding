package com.arithmetic.dissertation;

import com.arithmetic.dissertation.sort.GeneralSort;

public class TestSorts {

	private  void test(String sortname, GeneralSort sort, int[] randomArr,int[] room) 
	{
		long t1 = System.currentTimeMillis();
		if(checkOrderly(randomArr))
		{
			System.out.println(sortname+" success!");
		}else{
			System.out.println(sortname+" faild!");
		}
		long t2 = System.currentTimeMillis();
		System.out.println(sortname+" 花费："+(t2-t1)/1000.00+"s");
	}
	
	public static boolean checkOrderly(int[] data)
	{
		for(int i=0; i<data.length-1; i++)
		{
			if(data[i] > data[i+1])
			{
				return false;
			}
		}
		return true;
	}
}
