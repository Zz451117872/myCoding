package com.dataStructure.heap.impl;

/*
 * 最大堆
 */
public class MaxHeap<T extends Comparable<T>> extends AbstractHeap<T>{
			
	public MaxHeap(int count) {
		super(count);
	}
	
	public MaxHeap(T[] arr) {
		super(arr);
	}

	public static <T extends Comparable<T>> void sort(T[] arr)
	{
		MaxHeap<T> maxHeap = new MaxHeap<T>(arr);
		for(int i=arr.length-1; i>=0; i--)
		{
			arr[i] = maxHeap.pop();
		}
	}
	/*
	 * 向下维持堆特性
	 */
	public void shiftDown(int parent)
	{
		int left = leftChild(parent);	//获取左右子元素，并判断左右子元素中是否有比父元素大，
		int right = rightChild(parent);	//大则交换 再递归
		int biggest = parent;
		if(left != -1)
		{
			if(arr[left].compareTo(arr[biggest]) > 0)
			{
				biggest = left;
			}
		}
		if(right != -1)
		{
			if(arr[right].compareTo(arr[biggest]) > 0)
			{
				biggest = right;
			}
		}
		if(biggest != parent)
		{
			swap(arr,biggest,parent);
			shiftDown(biggest);
		}
		
	}
	
	/*
	 * 向上维持堆特性
	 */
	public void shiftUp(int child)
	{
		int parent = parentNode(child);//获取父元素，并判断父元素是否比子元素小，小则交换 再递归
		if(parent != -1)
		{
			if(arr[child].compareTo(arr[parent]) > 0)
			{
				swap(arr,child,parent);
				shiftUp(parent);
			}
		}
	}

	
	
}
