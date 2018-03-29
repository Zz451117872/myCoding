package com.dataStructure.collection.set;

import java.io.Serializable;

import com.dataStructure.collection.Iterator;
import com.dataStructure.collection.map.HashMap;
import com.dataStructure.collection.map.Map;

public class HashSet<E> implements Set<E>,Serializable{

	private static final long serialVersionUID = 1L;

	private HashMap<E,Object> map;
	private static final Object obj= "xxx";
	
	public HashSet()
	{
		this(10);
	}
	
	public HashSet(int capcity)
	{
		capcity = capcity < 0 ? 10 : capcity;
		this.map = new HashMap<E,Object>(capcity);
	}
	
	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean add(E e) {
		this.map.put(e, obj);
		return true;
	}

	@Override
	public boolean remove(Object e) {
		return this.map.remove( e );
	}

	@Override
	public boolean contains(Object e) {
		return this.map.containsKey( e );
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public void clear() {
		this.map.clear();
	}

	@Override
	public Iterator<E> iterater() {
		return this.map.keySet().iterater();
	}
}
