package com.arithmetic.dissertation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.arithmetic.dissertation.sort.BubbleSort;
import com.arithmetic.dissertation.sort.HeapSort;
import com.arithmetic.dissertation.sort.InsertSort;
import com.arithmetic.dissertation.sort.MergeSortDownTop;
import com.arithmetic.dissertation.sort.MergeSortTopDowm;
import com.arithmetic.dissertation.sort.QuilkSort3Ways;
import com.arithmetic.dissertation.sort.QulikSort1Ways;
import com.arithmetic.dissertation.sort.QulikSort2Ways;
import com.arithmetic.dissertation.sort.other.Sort;



public class TestSort {
	static Random r = new Random();
	//构造 测试数据
	public static int[] build(int n,int m)
	{
		int[] data = new int[n];
		for(int i=0; i<n; i++)
		{
			data[i] = r.nextInt(m);
		}
		return data;
	}
	
	public static int[] buildAlmostOrderly(int n,int m)
	{
		int[] data = new int[n];
		for(int i=0; i<n; i++)
		{
			data[i] = i;
		}
		for(int i=0; i<m; i++)
		{
			int a = r.nextInt(n);
			int b = r.nextInt(n);
			swap(data,a,b);
		}
		return data;
	}
	//打印测试时间
	private static void swap(int[] data, int a, int b) {
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
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
	
	public static void main(String[] str)
	{
		int n = 10000000;		//数组的长度
		int m = 10000;		//数组数据 的随机范围
		int repeat = 10;		//数组数据 的重复范围
				
		BubbleSort bubble = new BubbleSort();
		InsertSort insert = new InsertSort();
		QulikSort2Ways qulik2 = new QulikSort2Ways();
		QulikSort1Ways quilk1 = new QulikSort1Ways();
		QuilkSort3Ways quilk3 = new QuilkSort3Ways();
		MergeSortTopDowm mergeByRecursion = new MergeSortTopDowm();
		MergeSortDownTop mergeByNoRecursion = new MergeSortDownTop();
		HeapSort heap = new HeapSort();
		List<Sort> sorts = new ArrayList<Sort>();
//		sorts.add(bubble);
//		sorts.add(insert);
//		sorts.add(quilk1);
//		sorts.add(qulik2);
		sorts.add(quilk3);
		sorts.add(mergeByRecursion);
		sorts.add(mergeByNoRecursion);
		sorts.add(heap);
		
		
		for(int i=0; i<sorts.size(); i++)
		{
			int[] randomArr = build(n,m);		//随机数组
			int[] almostArr = buildAlmostOrderly(n, m);//近似有序数组
			int[] repeatArr = build(n,repeat);			//大量重复数组数组
			Sort sort = sorts.get(i);
			String name = sort.getClass().getSimpleName();
			int[] room = new int[n];
			test(name+" random",sort,randomArr,room);
			test(name+" almost",sort,almostArr,room);
			test(name+" repeat",sort,repeatArr,room);
			System.out.println("--------------------------------");
		}

	}

	private static void test(String sortname, Sort sort, int[] randomArr,int[] room) 
	{
		long t1 = System.currentTimeMillis();
		sort.sort(randomArr, 0, randomArr.length-1,room);
		if(checkOrderly(randomArr))
		{
			System.out.println(sortname+" success!");
		}else{
			System.out.println(sortname+" faild!");
		}
		long t2 = System.currentTimeMillis();
		System.out.println(sortname+" 花费："+(t2-t1)/1000.00+"s");
	}
	
}
