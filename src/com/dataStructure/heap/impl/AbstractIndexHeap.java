package com.dataStructure.heap.impl;

import com.dataStructure.heap.IndexHeap;

/*
 * 索引堆 的操作对象是索引
 * 索引堆的用途，不仅是因为数据过大不方便移动，更重要的是数据的相对位置没有变动，
 * 可以随时更新指定下标的数据。
 */
public abstract class AbstractIndexHeap<T extends Comparable<T>> implements IndexHeap<T>{
	
	protected T[] arr;
	protected int count;
	protected int[] indexs;
	protected int[] reverse;
	
	public AbstractIndexHeap(int size)
	{
		this.arr = (T[]) new Comparable[size];// 数据
		this.count = 0;
		indexs = new int[size];		//堆索引 作用：堆索引 代表着哪个数据
		reverse = new int[size];		//数据索引 作用：数据 在堆中的哪个位置
	}
	
	public AbstractIndexHeap(T[] arr)
	{
		this.arr = (T[]) new Comparable[arr.length];
		indexs = new int[arr.length];
		reverse = new int[arr.length];
		this.count = 0;
		for(int i=0; i<this.arr.length; i++)
		{
			if(this.arr[i] == null)
			{
				push(arr[i],i);
			}
		}		
	}
	
	public int popIndex()
	{
		if(isEmpty()) {
			System.out.println("is empty!!!");
			return -1;		
		}
		int index = indexs[0];
		arr[indexs[0]] = null;
		swap(indexs,0,count - 1);		
		swap(reverse,indexs[0],indexs[count-1]);
		reverse[count-1] = -1;
		this.count --;
		this.shiftDown(0);
		return index;
	}

	public T pop()
	{
		if(isEmpty()) {
			System.out.println("is empty!!!");
			return null;		
		}
		T temp = (T) arr[indexs[0]];
		arr[indexs[0]] = null;
		swap(indexs,0,count - 1);		
		swap(reverse,indexs[0],indexs[count-1]);
		reverse[count-1] = -1;
		this.count --;
		this.shiftDown(0);
		return temp;
	}
	/*
	 * 入堆操作：index 表示数据插入arr的位置
	 */
	public void push(T data ,int index)
	{
		if(isFull()) {
			System.out.println("is full!!!!");return;
		};	
		arr[index] = data;	//数据直接放在数据区指定位置
		indexs[count] = index;	//堆索引 指向 数据
		reverse[index] = count;	//数据 指向 堆索引
		this.shiftUp(count);	//维护堆特性
		this.count ++;
	}
	
	protected abstract void shiftUp(int index);
	protected abstract void shiftDown(int index);
	
	public T get(int index)
	{
		if(index <0 || index >= this.arr.length) return null;
		return this.arr[index];
	}
	
	/*
	 * 更新指定位置 的数据：index 是 arr 的下标
	 */
	public void update(T data, int index)
	{
		if(index <0 || index >= this.arr.length) return;
		if(this.arr[index] == null){
			push(data,index);
		}else{
			this.arr[index] = data;	//更新数据			
			shiftDown(reverse[index]);
			shiftUp(reverse[index]);
		}
	}
	
	@Override
	public void push(T[] arr) {
		for(int i=0; i<arr.length; i++)
		{
			push(arr[i]);
		}
	}
	
	@Override
	public void push(T data) {
		for(int i=0; i<this.arr.length; i++)
		{
			if(this.arr[i] == null)
			{
				push(data,i);
				return;
			}
		}
	}
	
	
	@Override	
	public boolean contains(T data) {
		return false;
	}
	
		
	public boolean contains(int index) {
		return reverse[index] != -1;
	}
	
	public void clear()
	{
		for(int i=0; i<this.count; i++)
		{
			arr[indexs[i]] = null;
			indexs[i] = -1;
		}
		this.count = 0;
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
	
	public void display()
	{
		for(int i=0; i < this.count; i++)
		{
			System.out.print(arr[indexs[i]].toString() +" ");
		}
		System.out.println("");
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
