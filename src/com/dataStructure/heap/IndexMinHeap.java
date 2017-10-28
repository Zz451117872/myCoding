package com.dataStructure.heap;

public class IndexMinHeap<T extends Comparable<T>> {
	/*
	 * 索引堆的用途，不仅是因为数据过大不方便移动，更重要的是数据与下标的对应关系没有变动，
	 * 可以随时更新指定下标的数据。而索引数组 维护的数据是 目标数据的下标。
	 * 目标数据是可以不按顺序插入的。
	 */
	private Comparable[] arr;	//目标数据
	private int count;
	private int[] indexs;		//索引组
	private int[] rever;		//索引数据
	
	public IndexMinHeap(int size)
	{
		arr =  new Comparable[size];
		count = 0;
		indexs = new int[size];
		rever = new int[size];
	}
	public void push(T[] dataArr)
	{
		for(int i=0; i<dataArr.length; i++)
		{
			push(dataArr[i],i);
		}
	}
	//将数据插入 指定 下标处
	public void push(T data ,int index)
	{
		if(isFull()) return;
		
		arr[index] = data;	//这个下标是目标数组的下标
		indexs[count] = index;	
//		rever[index] = count;		////////
		this.shiftDown(count);
		this.count ++;
	}
	
	public Comparable<T> getDataByIndex(int index)
	{
		if(index <0 || index >= this.arr.length) return null;
		return this.arr[index];
	}
	//更改 指定 下标处的 目标数据
	public void change(Comparable<T> data,int index)
	{
		if(index <0 || index >= this.arr.length) return;
		this.arr[index] = data;	//更新数据
		
		for(int i=0; i<this.count; i++)
		{
			if(this.indexs[i] == index)	//更新 该数据 在堆 中 的位置
			{
				shiftUp(i);
				shiftDown(i);
				return;
			}
		}
	}
	
	public int size()
	{
		return this.count;
	}
	public boolean isFull()
	{
		return this.count == this.arr.length;
	}
	
	public boolean isEmpty()
	{
		return this.count == 0;
	}
	public void clean()
	{
		for(int i=0; i<this.count; i++)
		{
			arr[indexs[i]] = null;
			indexs[i] = -1;
		}
		this.count = 0;
	}
	//弹出数据
	public T pop()
	{
		if(isEmpty()) return null;
		
		T temp = (T) arr[indexs[0]];
//		arr[indexs[0]] = null;
		swap(indexs,0,count - 1);
		
		this.count --;
		this.shiftUp(0);
		return temp;
	}
	// 从上往下 维护堆 的发性质
	public void shiftUp(int parent)
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
			shiftUp(smaller);
		}
	}
	//从下往上维护堆的性质
	public void shiftDown(int child)
	{
		int parent = Parent(child);
		if(parent != -1)
		{
			if(arr[indexs[child]].compareTo(arr[indexs[parent]]) < 0)
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
