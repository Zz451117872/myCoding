package com.dataStructure.collection.map;

import java.io.Serializable;


import com.dataStructure.collection.Collection;
import com.dataStructure.collection.Iterator;
import com.dataStructure.collection.list.ArrayList;
import com.dataStructure.collection.set.HashSet;
import com.dataStructure.collection.set.Set;

//hash算法实现map，增删速度快，但失去元素插入时的顺序
public class HashMap<K,V> implements Map<K,V>,Serializable{
	private static final long serialVersionUID = 1L;

	private Node<K,V>[] table ; 
	private int size;
	
	
	public HashMap(){
		this(5);
	}
	
	public HashMap(int capcity){
		capcity = capcity < 0 ? 5 : capcity;
		table = new Node[capcity];
	}
	public int getLength()
	{
		return this.table.length;
	}
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean containsKey(Object e) {
		int index = hash(e);
		if( this.table[ index ] != null )
		{
			Node<K,V> root = this.table[ index ];
			for(; root != null; root=root.next){
				if( root.getK().equals( e ) )
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object e) {
		for( int i=0; i<this.table.length; i++ )
		{
			if( this.table[ i ] != null )
			{
				Node<K,V> root = this.table[ i ];
				while( root != null )
				{
					if( root.getV().equals( e  ))
					{
						return true;
					}
					root = root.next;
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object o) {
		int index = hash( o );
		if( this.table[ index ] != null )
		{
			Node<K,V> root = this.table[ index ];
			while( root != null )
			{
				if( root.getK().equals( o ) )
				{
					return root.getV();
				}
				root = root.next;
			}
		}
		return null;
	}

	public Node<K,V> newNode(int hash , K k , V v){
		return new Node<K,V>( hash , k , v );
	}
	
	@Override
	public void put(K k, V v) {
		if( isFull() && !expensionCapcity() ) return;
				
		int index = hash(k);
		Node<K,V> node = newNode( index, k, v );
		if( table[ index ] != null )
		{
			Node<K,V> root = table[ index ];
			while( root != null )
			{
				if( root.getK().equals( k ))
				{
					root.setV( v );
					return;
				}else{
					if( root.next != null ){
						root = root.next;
					}
				}
				root.next = node;
			}
		}else{
			table[ index ] = node;
		}
		this.size ++;
		insertCallback( node );
	}
	//插入回调函数，供子类重写
	public void insertCallback(Node<K,V> root){};
		
	private boolean isFull(){
		for( int i=0; i<this.table.length; i++ )
		{
			if( this.table[ i ] == null )
			{
				return false;
			}
		}
		return true;
	}
	//对节点数组进行扩容
	private boolean expensionCapcity()
	{
		expensionCapcityCallback();
		Node<K,V>[] oldTable = this.table;
		this.table = new Node[ oldTable.length * 2 + 1 ];
		this.size = 0;
		for( int i=0; i<oldTable.length; i++ )
		{
			Node<K,V> root = oldTable[ i ];
			while( root != null )
			{
				this.put( root.k , root.v );
				root = root.next;
			}
		}		
		return true;
	}
	//扩容函数回调，供子类重写 
	public void expensionCapcityCallback(){}
	
	//hash算法
 	private int hash(Object o)
	{
		return Math.abs(o.hashCode()) % this.table.length;
	}
	
	@Override
	public boolean remove(Object o) {
		int index = hash( o );
		if( this.table[ index ] != null )
		{
			Node<K,V> root = new Node<K,V>();
			root.next = this.table[ index ];			
			while( root.next != null )
			{
				if(root.next.getK().equals( o ) )
				{
					removeCallBack( root.next );
					root.next = root.next.next;
					this.size --;
					this.table[ index ] = root.next;					
					return true;
				}
				root = root.next;
			}			
		}
		return false;
	}
	//删除元素回调，供子类重写
	public void removeCallBack( Node<K,V> node ){}
	
	@Override
	public void clear() {
		this.table = new Node[ this.table.length ];
		this.size = 0;
	}

	@Override
	public Set<K> keySet() {
		return new KeySet();
	}

	class KeySet extends HashSet<K>{
		public Iterator<K> iterater() {
			return  new KeyIterator();
		}
	}
	@Override
	public Collection<V> values() {
		return new Values();
	}

	class Values extends ArrayList<V>{
		
		public Iterator<V> iterater() {
			return  new ValueIterator();
		}
	}
	@Override
	public Set<com.dataStructure.collection.map.Map.Entry<K, V>> entrySet() {
		return new EntrySet();
	}

	class EntrySet extends HashSet<com.dataStructure.collection.map.Map.Entry<K, V>>{
		public Iterator<com.dataStructure.collection.map.Map.Entry<K, V>> iterater() {
			return  new EntryIterator();
		}
	}
	abstract class HashIterator {
		private Node<K,V> next;
		private Node<K,V> last;
		private int index;
		Node<K,V>[] t = table;
		
		public HashIterator(){			
			next = last = null;
			index = 0;
			if( size > 0 )
			{
				do{}while( index < table.length && (next = table[ index++ ]) == null);
			}
		}
		
		public  boolean hasNext()
		{
			return next != null ;
		}
		
		public Node<K,V> nextNode()
		{
			Node<K,V> node = next;
			this.last = node;
			next = next.next;
			while( next == null && index < table.length)
			{			
				next = table[index];
				index ++;
			}
			return node;
		}
		
		public  boolean  remove()
		{
			if( this.last != null ){
				HashMap.this.remove( last.k );
				this.last = null;
				return true;
			}
			return false;
		}
	}
	
	class KeyIterator extends HashIterator implements Iterator<K>{
		@Override
		public K next() {
			return nextNode().k;
		}
		
	}
	
	class ValueIterator extends HashIterator implements Iterator<V>{
		@Override
		public V next() {
			return nextNode().v;
		}
		
	}
	
	class EntryIterator extends HashIterator implements Iterator<Map.Entry<K, V>>{
		@Override
		public Map.Entry<K, V> next() {
			return nextNode();
		}
		
	}
	//节点内部类
	static class Node<K,V> implements Map.Entry<K, V>{
		K k;
		V v;
		int hash;
		Node<K,V> next;
		
		public Node(){}
		
		public Node(int hash, K k ,V v)
		{
			this.hash = hash;
			this.k = k;
			this.v = v;
			this.next = null;
		}
		@Override
		public K getK() {
			return this.k;
		}

		@Override
		public V getV() {
			return this.v;
		}
		@Override
		public void setV(V v) {
			this.v = v;
		}
		
	}
}
