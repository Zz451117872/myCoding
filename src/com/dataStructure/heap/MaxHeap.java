package com.dataStructure.heap;

public class MaxHeap<T extends Comparable<T>> {
	// 最大堆 
	private Comparable[] arr ;
	private int count;
	
	public MaxHeap(int count)
	{	
		this.arr =  new Comparable[count];
		this.count = 0;
	}
	
	public void push(T data)
	{
		if(this.count > this.arr.length) return;
		arr[count] = data;	
		shiftDown(count);
		count ++;
	}
	
	public T pop()
	{
		T data = (T) arr[0];
		swap(arr,0,count - 1);
		count--;
		shiftUp(0);
		return data;
	}
	
	public void shiftUp(int parent)
	{
		int left = leftChild(parent);
		int right = rightChild(parent);
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
			shiftUp(biggest);
		}
		
	}
	
	public void shiftDown(int child)
	{
		int parent = parentNode(child);
		if(parent != -1)
		{
			if(arr[child].compareTo(arr[parent]) > 0)
			{
				swap(arr,child,parent);
				shiftDown(parent);
			}
		}
	}
	
	public void display()
	{
		for(int i=0; i< this.count; i++)
		{
			System.out.println(this.arr[i].toString());
		}
	}
		

	private void swap(Comparable<T>[] arr, int child, int parent) 
	{
		Comparable temp = arr[child];
		arr[child] = arr[parent];
		arr[parent]= temp;
	}
	public  void sort(Comparable[] data)
	{
		MaxHeap maxHeap = heapift(data);
		for(int i=data.length-1; i >= 0; i--)
		{
			data[i] = maxHeap.pop();
		}
	}
	
	public  MaxHeap heapift(Comparable[] data)
	{
		MaxHeap maxHeap = new MaxHeap(data.length);
		for(int i=0; i < data.length; i++)
		{
			maxHeap.push(data[i]);
		}
		maxHeap.display();
		return maxHeap;
	}
	
	public int parentNode(int child)
	{
		return (child - 1)/2 >= 0 ? (child - 1)/2 : -1;
	}	
	public int leftChild(int parent)
	{
		return parent*2 +1 < this.count ? parent*2 +1 : -1;
	}
	public int rightChild(int parent)
	{
		return parent*2 +2 < this.count ? parent*2 +2 : -1;
	}
}
