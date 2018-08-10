package com.dataStructure.collection;

//迭代器 接口
public interface Iterator<T> {
	//是否还有元素没有遍历
	boolean hasNext();
	//获取下一个没有遍历的元素
	T next();
	//删除当前元素
	boolean remove();
}
