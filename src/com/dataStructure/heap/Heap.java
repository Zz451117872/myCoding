package com.dataStructure.heap;

public interface Heap<T extends Comparable<T>> {
	public void push(T data);
	public T pop();
	public int size();
	public boolean isEmpty();
	public boolean isFull();
	public boolean contains(T data);	
}
