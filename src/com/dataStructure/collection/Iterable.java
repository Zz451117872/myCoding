package com.dataStructure.collection;

//可迭代的 接口
public interface Iterable<T> {
	
	//获取迭代器
	Iterator<T> iterater();
}
