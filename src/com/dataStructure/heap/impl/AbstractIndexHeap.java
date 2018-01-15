package com.dataStructure.heap.impl;

import com.dataStructure.heap.IndexHeap;

public abstract class AbstractIndexHeap<T extends Comparable<T>> implements IndexHeap<T>{
	
	private T[] arr;
	private int count;
	private int[] indexs;
	private int[] reverse;
}
