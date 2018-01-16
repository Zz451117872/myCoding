package com.dataStructure.heap;
/*
 * 普通堆
 */
public interface Heap<T extends Comparable<T>> {
	public void push(T data); //入堆
	public T pop();	//出堆
	public int size();
	public boolean isEmpty();
	public boolean isFull();
	public boolean contains(T data);	
}
