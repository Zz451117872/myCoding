package com.dataStructure.heap.impl;

//最小堆
public class MinHeap<T extends Comparable<T>> extends AbstractHeap<T>{				
	public MinHeap(int count)
	{	
		super(count);
	}
	
	public MinHeap(T[] arr)
	{	
		super(arr);
	}
		
	public static <T extends Comparable<T>> void sort(T[] arr)
	{
		MinHeap<T> minHeap = new MinHeap<T>(arr);
		for(int i=arr.length-1; i>=0; i--)
		{
			arr[i] = minHeap.pop();
		}
	}
	
	public void shiftDown(int parent)
	{
		int left = leftChild(parent);
		int right = rightChild(parent);
		int small = parent;
		if(left != -1)
		{
			if(arr[left].compareTo(arr[small]) < 0)
			{
				small = left;
			}
		}
		if(right != -1)
		{
			if(arr[right].compareTo(arr[small]) < 0)
			{
				small = right;
			}
		}
		if(small != parent)
		{
			swap(arr,small,parent);
			shiftDown(small);
		}			
	}
	
	public void shiftUp(int child)
		{
			int parent = parentNode(child);
			if(parent != -1)
			{
				if(arr[child].compareTo(arr[parent]) < 0)
				{
					swap(arr,child,parent);
					shiftUp(parent);
				}
			}
		}
				
}
