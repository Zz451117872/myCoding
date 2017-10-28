package com.dataStructure.heap;

public class IndexMaxHeap<T extends Comparable<T>> {
	/*
	 * 索引堆 有什么作用
	 * 在数据相当大的时候，移动数据会非常耗损性能，所以采用索引来代替数据移动。
	 */
	private Comparable[] arr;
	private int count;
	private int[] indexs;
	
	public IndexMaxHeap(int size)
	{
		arr =  new Comparable[size];
		count = 0;
		indexs = new int[size];
		for(int i=0; i < indexs.length; i++)
		{
			indexs[i]= i;
		}
	}
	public void push(T[] dataArr)
	{
		for(int i=0; i<dataArr.length; i++)
		{
			push(dataArr[i]);
		}
	}
	public void push(T data)
	{
		arr[count] = data;	
		this.shiftDown(count);
		this.count ++;
	}
	
	public T pop()
	{
		T temp = (T) arr[indexs[0]];
		swap(indexs,0,count - 1);
		this.count --;
		this.shiftUp(0);
		return temp;
	}
	
	public void shiftUp(int parent)
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
			shiftUp(biggest);
		}
	}
	
	public void shiftDown(int child)
	{
		int parent = Parent(child);
		if(parent != -1)
		{
			if(arr[indexs[child]].compareTo(arr[indexs[parent]]) > 0)
			{
				swap(indexs,child,parent);
				shiftDown(parent);
			}
		}
	}
	
	public void display()
	{
		for(int i=0; i < this.count; i++)
		{
			System.out.println(arr[indexs[i]].toString());
		}
	}
	
	public int Parent(int child)
	{
		return (child -1)/2 >= 0 ? (child - 1)/2 : -1;
	}
	public int Left(int parent)
	{
		return parent*2 +1 < this.count ? parent*2 +1 : -1;
	}
	
	public int Right(int parent)
	{
		return parent*2 + 2 < this.count ? parent*2+2 : -1;
	}
	
	public void swap(int[] arr, int source,int dest)
	{
		int temp = arr[source];
		arr[source] = arr[dest];
		arr[dest] = temp;
	}
}
