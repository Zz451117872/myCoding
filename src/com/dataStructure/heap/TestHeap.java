package com.dataStructure.heap;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.dataStructure.heap.impl.IndexMaxHeap;
import com.dataStructure.heap.impl.IndexMinHeap;
import com.dataStructure.heap.impl.MaxHeap;
import com.dataStructure.heap.impl.MinHeap;

public class TestHeap {

	Random random = new Random();
	@Test
	public void MaxHeap()
	{
		System.out.println("--MaxHeap----------------");
		Integer[] data = new Integer[]{5,7,32,75,2,53,23,765,34,33};
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(data);
		maxHeap.display();	
		MaxHeap.sort(data);
		System.out.println(Arrays.toString(data));
		System.out.println("-------------------");
	}
	@Test
	public void indexMaxHeap()
	{
		System.out.println("--indexMaxHeap----------------");
		Integer[] data = new Integer[]{5,7,32,75,2,53,23,765,34,33,45};
		System.out.println(Arrays.toString(data));
		IndexMinHeap<Integer> imh = new IndexMinHeap<Integer>(data);
		imh.display();
		imh.display();
		for(int i=0; i<11; i++)
		{
			System.out.println(imh.pop());
		}
		System.out.println("-----------------");
	}

}
