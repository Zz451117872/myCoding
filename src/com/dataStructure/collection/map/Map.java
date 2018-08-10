package com.dataStructure.collection.map;

import com.dataStructure.collection.Collection;
import com.dataStructure.collection.set.Set;

public interface Map<K,V> {
	int size();
	boolean isEmpty();
	boolean containsKey(Object e);
	boolean containsValue(Object e);
	V get(Object o);
	void put(K k, V v);
	boolean remove(Object o);
	void clear();
	Set<K> keySet();
	Collection<V> values();
	Set<Map.Entry<K, V>> entrySet();
	
	interface Entry<K,V>{
		K getK();
		V getV();
		void setV(V v);
	}
}
