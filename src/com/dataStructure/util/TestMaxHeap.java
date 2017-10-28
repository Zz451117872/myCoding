package com.dataStructure.util;

import com.dataStructure.heap.IndexMaxHeap;

public class TestMaxHeap {
	
	public static void main(String[] str)
	{
		Integer[] data = {51,26,54,13,64,34,614,113,435,63,413,66,73,41};
		IndexMaxHeap<Integer> indexHeap = new IndexMaxHeap<Integer>(20);
		indexHeap.push(data);
		indexHeap.display();
		indexHeap.pop();
		indexHeap.display();
	}
}
