package com.dataStructure.heap.impl;

public class IndexMaxHeap<T extends Comparable<T>>  extends AbstractIndexHeap<T>{
	
	public IndexMaxHeap(int size) {
		super(size);
	}
	
	public IndexMaxHeap(T[] arr) {
		super(arr);
	}

	public void shiftDown(int parent)
	{
		int left = Left(parent);
		int right = Right(parent);
		int biggest = parent;
		if(left != -1)
		{
			if(arr[indexs[left]].compareTo(arr[indexs[biggest]]) > 0)
			{
				biggest = left;
			}
		}
		if(right != -1)
		{
			if(arr[indexs[right]].compareTo(arr[indexs[biggest]]) > 0)
			{
				biggest = right;
			}
		}
		if(biggest != parent)
		{
			swap(indexs,biggest,parent);
			swap(reverse,indexs[biggest],indexs[parent]);
			shiftDown(biggest);
		}
	}
	
	public void shiftUp(int child)
	{
		int parent = Parent(child);
		if(parent != -1)
		{
			if(arr[indexs[child]].compareTo(arr[indexs[parent]]) > 0)
			{
				swap(indexs,child,parent);
				swap(reverse,indexs[child],indexs[parent]);
				shiftUp(parent);
			}
		}
	}
	
}
