package com.arithmetic.dissertation.sort;

import com.arithmetic.dissertation.sort.other.Sort;

public class HeapSort implements Sort{
	/*
	 * 堆排序，堆其实不是用来干排序的。堆 用来处理动态数据会比较好。
	 */
	private static int heap_size = -1;
	/*
	 * 维护最大堆的基本性质：根节点比所有子节点都要大
	 */
	public  void max_heapify(int[] data, int parent)
	{
		if(parent < 0) return;
		int left = Left(parent);//左节点
		int right = Right(parent);//右节点
		int largest = -1;//最大值节点下标
		if(left < HeapSort.heap_size  && data[left] > data[parent])
		{
			largest = left;
		}else{
			largest = parent;
		}
		if(right < HeapSort.heap_size && data[right] > data[largest])
		{
			largest = right;
		}
		if(largest != parent)
		{//如果最大值下标不是父节点，则交换最大值节点与父节点，并向下递归
			change(data,parent,largest);
			max_heapify(data,largest);
		}
	}
	
	/*
	 * 构建最大堆，自底向上
	 * data.length/2+1 .....n，是叶子节点，已是最大堆
	 */
	public  void bulid_max_heap(int[] data)
	{
		HeapSort.heap_size = data.length;
		for(int i = (this.heap_size - 2)/2 ; i >= 0; i--)
		{
			max_heapify(data,i);
		}
	}
	
	/*
	 * 堆排序
	 * 最大堆 中下标为0的节点总是最大值，排序前要先构建最大堆； 
	 */
	public  void sort(int[] data,int start,int end,int[] room)
	{
		bulid_max_heap(data);
		for(int i=data.length-1; i > 0; i--)
		{
			change(data,i,0);
			HeapSort.heap_size = HeapSort.heap_size - 1;
			max_heapify(data,0);
		}
	}
	
	public  void change(int[] data,int i, int j) 
	{
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	private  int Right(int parent) {
		return parent*2+2 < this.heap_size  ? parent*2+2: this.heap_size;
	}

	private  int Left(int parent) {
		return parent*2+1 < this.heap_size  ? parent*2+1: this.heap_size ;
	}

	public  int Parent(int son)
	{
		return (son -1)/2 < 0 ? -1 : (son - 1)/2;
	}
}
