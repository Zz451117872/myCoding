package com.dataStructure.collection.list;

import com.dataStructure.collection.Collection;

//列表 接口
public interface List<E> extends Collection<E> {
	boolean add(int index , E e);
	E get(int index);
	int indexOf(Object obj);
	boolean remove(int index);
}
