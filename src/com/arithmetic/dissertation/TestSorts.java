package com.arithmetic.dissertation;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import com.arithmetic.dissertation.sort.GeneralSort;
import com.arithmetic.dissertation.sort.SorterHandler;
import com.arithmetic.dissertation.sort.impl.GeneralSortImpl;

public class TestSorts{
	Random random = new Random();
	static int scale = 100000000;
	static int range = 100;
	static int Inversion = 100;
		
	@Test
	public void sorts()
	{
		GeneralSortImpl sortImpl = new GeneralSortImpl();
		SorterHandler handler = new SorterHandler(sortImpl);
				
		GeneralSort sorter = (GeneralSort) Proxy.newProxyInstance(GeneralSortImpl.class.getClassLoader(),
				GeneralSortImpl.class.getInterfaces(),
				handler);
		//out_of_order(sorter);
		almost_order(sorter);
		repeat_order(sorter);
	}	
		
	private void out_of_order(GeneralSort sorter)
	{
		System.out.println("--乱序数据-------------");
		Integer[] arr = this.createClassArray(scale, scale);
//		sorter.bubbleSort(arr);
//		arr = this.createClassArray(scale, scale);
//		sorter.selectSort(arr);
//		arr = this.createClassArray(scale, scale);
//		sorter.insertSort(arr);
//		arr = this.createClassArray(scale, scale);
//		sorter.qulikSort1Ways(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, scale);
		sorter.qulikSort2Ways(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, scale);
//		sorter.qulikSort3Ways(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, scale);
//		sorter.mergeSortByRecursion(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, scale);
//		sorter.mergeSortByRecurrence(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, scale);
//		sorter.heapSort(arr);
	}
	
	private void almost_order(GeneralSort sorter)
	{
		System.out.println("--近似有序数据-------------");
		Integer[] arr = this.createAlmostOrderly(scale, Inversion);
//		sorter.bubbleSort(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, Inversion);
//		sorter.selectSort(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, Inversion);
//		sorter.insertSort(arr);
//		arr = this.createClassArray(scale, Inversion);
//		sorter.qulikSort1Ways(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, Inversion);
		sorter.qulikSort2Ways(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, Inversion);
//		sorter.qulikSort3Ways(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, Inversion);
//		sorter.mergeSortByRecursion(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, Inversion);
//		sorter.mergeSortByRecurrence(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, Inversion);
//		sorter.heapSort(arr);
	}
	
	private void repeat_order(GeneralSort sorter)
	{
		System.out.println("--大量重复数据-------------");
		Integer[] arr = this.createAlmostOrderly(scale, range);
//		sorter.bubbleSort(arr);
//		arr = this.createClassArray(scale, range);
//		sorter.selectSort(arr);
//		arr = this.createClassArray(scale, range);
//		sorter.insertSort(arr);
//		arr = this.createClassArray(scale, range);
//		sorter.qulikSort1Ways(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, range);
		sorter.qulikSort2Ways(arr, 0, arr.length-1);
//		arr = this.createClassArray(scale, range);
//		sorter.qulikSort3Ways(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, range);
//		sorter.mergeSortByRecursion(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, range);
//		sorter.mergeSortByRecurrence(arr, 0, arr.length-1, new Integer[arr.length]);
//		arr = this.createClassArray(scale, range);
//		sorter.heapSort(arr);
	}
	
	public  Integer[] createClassArray(int scale, int scope)
	{
		Integer[] data = new Integer[scale];
		for(int i=0; i<scale; i++)
		{
			data[i] = random.nextInt(scope);
		}
		return data;
	}
	
	public  Integer[] createAlmostOrderly(int scale, int Inversion)
	{
		Integer[] data = new Integer[scale];
		for(int i=0; i<scale; i++)
		{
			data[i] = i;
		}
		for(int i=0; i<Inversion; i++)
		{
			int a = random.nextInt(scale);
			int b = random.nextInt(scale);
			swap(data,a,b);
		}
		return data;
	}
	
	private static void swap(Integer[] data, int a, int b) {
		Integer temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
		
}
