package com.dataStructure.heap.impl;

/*
 * 普通堆的基本实例
 */
public abstract class AbstractHeap<T extends Comparable<T>> {
	protected T[] arr ; //堆在结构上使用数组实现的完全二叉树
	protected int count;//堆中数据的个数
	
	public AbstractHeap(int count)
	{			
		this.arr =  (T[]) new Comparable[count];
		this.count = 0;
	}
	
	public AbstractHeap(T[] arr)
	{
		this.arr =  (T[]) new Comparable[arr.length];
		for(int i=0; i<this.arr.length; i++)
		{
			this.arr[i] = arr[i];
		}	
		this.count = arr.length;
		heapify();		
	}
	/*
	 * 将堆中数组堆化，即满足堆的性质：父元素比子元素权值大（小）	
	 */
	public  void heapify(){
		for(int i = (this.count - 2)/2 ; i >= 0; i--)
		{			//在数组中，第this.count - 2个元素 是第一个未堆化的元素
			shiftUp(i);
			shiftDown(i);//试着进行 向下整理 向上整理			
		}
	}
		
	public void push(T data)
	{
		if(isFull()) return;
		arr[count] = data;//入堆 即把数据放在数组末尾 再向上维持堆特性
		shiftUp(count);
		count ++;
	}		
	
	public T pop()
	{
		if(isEmpty()) return null;
		T data = (T) arr[0];
		swap(arr,0,count - 1);//出堆 即 交换根元素 最 末尾元素，再从根节点向下维持堆特性
		count--;
		shiftDown(0);
		return data;
	}
	
	public abstract void shiftUp(int parent);
	public abstract void shiftDown(int child);
	
	public int size()
	{
		return this.count;
	}
	
	public boolean contains(T data) 
	{
		for(int i=0; i < this.count; i++)
		{
			if(this.arr[i].equals(data))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty()
	{
		return this.count == 0;
	}
	
	public boolean isFull()
	{
		return this.count == this.arr.length;
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
	
	public void display()
	{
		for(int i=0; i< this.count; i++)
		{
			System.out.print(this.arr[i].toString()+" ");
		}
		System.out.println("");
	}
	
	public void swap(T[] arr, int child, int parent) 
	{
		T temp = arr[child];
		arr[child] = arr[parent];
		arr[parent]= temp;
	}
}
