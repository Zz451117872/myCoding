package com.dataStructure.heap;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.dataStructure.heap.impl.MaxHeap;
import com.dataStructure.heap.impl.MinHeap;

public class TestHeap {

	Random random = new Random();
	@Test
	public void MaxHeap_test()
	{
		System.out.println("--MaxHeap_test----------------");
		Integer[] data = new Integer[]{5,7,32,75,2,53,23,765,34,33};
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(data);
		maxHeap.display();
		
		MaxHeap.sort(data);
		System.out.println(Arrays.toString(data));
		System.out.println("-------------------");
	}
	
//	@Test
//	public void MinHeap_test()
//	{
//		System.out.println("--MinHeap_test----------------");
//		MinHeap<Integer> minHeap = new MinHeap<Integer>(20);
//		for(int i=0; i<20; i++)
//		{
//			minHeap.push(random.nextInt(100));
//			minHeap.display();
//		}
//		System.out.println("-------------------");
//	}
}
