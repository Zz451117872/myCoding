package com.dataStructure.collection.map;

import com.dataStructure.collection.Collection;
import com.dataStructure.collection.Iterator;
import com.dataStructure.collection.map.HashMap.Node;
import com.dataStructure.collection.set.HashSet;
import com.dataStructure.collection.set.Set;

//继承hashmap，保留hashmap增删快优点，通过引入链表，使元素拥有插入时的顺序
public class LinkedHashMap<K,V> extends HashMap<K,V>  {

	private static final long serialVersionUID = 1L;

	//在hashmap节点的基础上，增加的前置和后置节点
	static class Entry<K,V> extends HashMap.Node<K,V>{
		private Entry<K,V> before;	//前置节点
		private Entry<K,V> after;	//后置节点
		public Entry(int hash, K k, V v) {
			super(hash, k, v);
		}		
	}
	
	private Entry<K,V> head; //头节点
	private Entry<K,V> end;	 //尾结点
		
	//重写父类方法，使节点类型保持一至
	@Override
	public Node<K, V> newNode(int hash, K k, V v) {
		return new Entry<K, V>(hash, k, v);
	}
	//重写插入函数回调，维持元素插入时的顺序
	@Override
	public void insertCallback( Node<K, V> node )
	{
		System.out.println("insertCallBack");
		Entry<K,V> entry = (Entry<K, V>) node;
		Entry<K, V> currentEnd = end;
		if( end == null )
		{
			head = end = entry;
		}else{
			currentEnd.after = entry;
			entry.before = currentEnd;
			end = entry;
		}
	}
	//重写删除函数回调，维持元素插入时的顺序
	@Override
	public void removeCallBack(Node<K, V> node) {
		System.out.println("removeCallBack");
		Entry<K, V> current = (Entry<K, V>) node;
		if( head == node )
		{
			head = head.after;
			head.before = null;
			current.after = null;
			return;
		}else if( end == node )
		{
			end = end.before;
			end.after = null;
			current.before = null;
			return;
		}else{
			Entry<K, V> before = current.before;
			Entry<K, V> after = current.after;
			current.after = null;
			current.before = null;
			before.after = after;
			after.before = before;
			return;
		}
	}

	//重写扩容函数回调，维持元素插入时的顺序
	@Override
	public void expensionCapcityCallback() {
		head = null;
		end = null;
	}

	@Override
	public Set<K> keySet() {
		return new LinkedKeySet();
	}
	
	class LinkedKeySet extends HashSet<K>{
		@Override
		public Iterator<K> iterater() {
			return new LinkedKeyIterator();
		}
	}
	
	@Override
	public Collection<V> values() {
		return new LinkedValues();
	}
	
	class LinkedValues implements Collection<V>{

		@Override
		public Iterator<V> iterater() {
			return new LinkedValueIterator();
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean add(V e) {
			return false;
		}

		@Override
		public boolean remove(Object e) {
			return false;
		}

		@Override
		public boolean contains(Object e) {
			return false;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public void clear() {
			
		}
		
	}
	
	@Override
	public Set<com.dataStructure.collection.map.Map.Entry<K, V>> entrySet() {
		return new LinkedEntrySet();
	}
	
	class LinkedEntrySet extends HashSet<com.dataStructure.collection.map.Map.Entry<K, V>>{
		@Override
		public Iterator<com.dataStructure.collection.map.Map.Entry<K, V>> iterater() {
			return new LinkedEntryIterator();
		}		
	}

	abstract class LinkedHashIterator{
		private Entry<K,V> next;
		private Entry<K,V> last;
		
		public LinkedHashIterator()
		{
			next = head;
			last = null;
		}
		
		public boolean hasNext()
		{
			return next != null ;
		}
		
		public Node<K,V> nextNode()
		{
			Entry<K,V> entry = next;
			this.last = entry;
			next = next.after;
			return entry;
		}
		
		public boolean remove()
		{
			if( this.last != null )
			{
				LinkedHashMap.this.remove( last.k );
				this.last = null;
				return true;
			}
			return false;
		}
	}
	
	class LinkedKeyIterator extends LinkedHashIterator implements Iterator<K>{
		@Override
		public K next() {
			return nextNode().k;
		}		
	}
	
	class LinkedValueIterator extends LinkedHashIterator implements Iterator<V>{
		@Override
		public V next() {
			return nextNode().v;
		}		
	}
	
	class LinkedEntryIterator extends LinkedHashIterator implements Iterator<com.dataStructure.collection.map.Map.Entry<K, V>>{
		@Override
		public Entry<K,V> next() {
			return (Entry<K, V>) nextNode();
		}		
	}
}
