package com.dataStructure.heap.impl;

public class IndexMinHeap<T extends Comparable<T>> extends AbstractIndexHeap<T>{
	
	public IndexMinHeap(int size) {
		super(size);
	}
	public IndexMinHeap(T[] arr) {
		super(arr);
	}

	//向下维护堆特性
	public void shiftDown(int parent)
	{
		int left = Left(parent);
		int right = Right(parent);
		int smaller = parent;
		if(left != -1)
		{
			if(arr[indexs[left]].compareTo(arr[indexs[smaller]]) < 0)
			{
				smaller = left;
			}
		}
		if(right != -1)
		{
			if(arr[indexs[right]].compareTo(arr[indexs[smaller]]) < 0)
			{
				smaller = right;
			}
		}
		if(smaller != parent)
		{
			swap(indexs,smaller,parent);
			swap(reverse,indexs[smaller],indexs[parent]);
			shiftDown(smaller);
		}
	}
	
	//向上维护堆特性
	public void shiftUp(int child)
	{
		int parent = Parent(child);
		if(parent != -1)
		{
			if(arr[indexs[child]].compareTo(arr[indexs[parent]]) < 0)
			{
				swap(indexs,child,parent);
				swap(reverse,indexs[child],indexs[parent]);
				shiftUp(parent);
			}
		}
	}
	
	
	
}
