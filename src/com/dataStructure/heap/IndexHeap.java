package com.dataStructure.heap;

/*
 *索引堆
 */
public interface IndexHeap<T extends Comparable<T>> extends Heap<T>{
	public void push(T data ,int index); //在数据区 指定下标 处入堆
	public void push(T[] arr);//入堆
	public T get(int index);	// 
	public void update(T data,int index);//更新 数据区 指定下标处数据
	public void clear();
	public int popIndex();
	public T pop();
	
}
