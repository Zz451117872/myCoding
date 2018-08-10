package com.dataStructure.collection;

//收集器接口
public interface Collection<E> extends Iterable<E>{
	int size();
	boolean add(E e);
	boolean remove(Object e);
	boolean contains(Object e);
	boolean isEmpty();
	void clear();
	
}
