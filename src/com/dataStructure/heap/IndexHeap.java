package com.dataStructure.heap;

public interface IndexHeap<T extends Comparable<T>> extends Heap<T>{
	public void push(T data ,int index);
	public void push(T[] arr);
	public T get(int index);
	public void update(T data,int index);
	public void clear();
}
