package com.dataStructure.collection.list;

import java.io.Serializable;

import com.dataStructure.collection.Iterator;

//链表实现List，有序可重复，删除元素快
public class LinkedList<E> implements List<E>,Serializable{	
	private static final long serialVersionUID = 1L;
	
	private ListNode<E> head; //头结点
	private ListNode<E> last; //尾结点
	private int size;
	
	@Override
	public int size() {
		return this.size;
	}
	//添加元素
	@Override
	public boolean add(E e) {
		ListNode<E> node = new ListNode<E>(e);
		if( head == null )
		{
			head = node;
			last = node;
		}else{
			last.next = node;
			last = node;
		}
		this.size ++ ;
		return true;
	}
	//删除元素
	@Override
	public boolean remove(Object e) {
		ListNode<E> node = getListNode( e );
		if( node != null )
		{
			if( node == head )
			{
				this.head = head.next;
				this.head.previous = null;
			}else if( node == last )
			{
				this.last = last.previous;
				this.last.next = null;
			}else{
				ListNode<E> delectPointPrevious = node.previous;
				ListNode<E> delectPointNext = node.next;
				node.next = null;
				node.previous = null;
				delectPointPrevious.next = delectPointNext;
				delectPointNext.previous = delectPointPrevious;
			}
			this.size --;
			return true;
		}
		return false;
	}
	//删除元素
	@Override
	public boolean remove(int index) {
		ListNode<E> node = getListNode( index );
		if( node != null )
		{
			if( node == head )
			{
				this.head = head.next;
				this.head.previous = null;
			}else if( node == last )
			{
				this.last = last.previous;
				this.last.next = null;
			}else{
				ListNode<E> delectPointPrevious = node.previous;
				ListNode<E> delectPointNext = node.next;
				node.next = null;
				node.previous = null;
				delectPointPrevious.next = delectPointNext;
				delectPointNext.previous = delectPointPrevious;
			}
			this.size --;
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object e) {
		return getListNode( e ) != null;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public void clear() {
		this.head = null;
		this.last = null;
		this.size = 0;
	}
	//获取迭代器
	@Override
	public Iterator<E> iterater() {
		return new ListIterator<E>(0);
	}
	//在指定位置添加元素
	@Override
	public boolean add(int index, E e) {
		ListNode<E> node = new ListNode<E>(e);
		if( index < this.size -1 || index > -1 ){
			ListNode<E> insertCurrentPoint = getListNode( index );
			ListNode<E> insertNextPoint = insertCurrentPoint.next;
			insertCurrentPoint.next = node;
			insertNextPoint.previous = node;
			node.previous = insertCurrentPoint;
			node.next = insertNextPoint;
		}else{
			this.last.next = node;
			this.last = node;
		}
		this.size ++ ;
		return true;
	}

	@Override
	public E get(int index) {
		ListNode<E>  node = getListNode( index );
		if( node != null )
		{
			return node.data;
		}
		return null;
	}
	//通过索引获取节点
	public ListNode<E> getListNode(int index) {
		if( index >= this.size || index < 0 ) return null;
		
		ListNode<E> node = head;
		while( index > 0 )
		{
			node = node.next;
			index -- ;
		}
		return  node;		
	}
	//通过元素获取节点
	public ListNode<E> getListNode(Object e) {		
		ListNode<E> node = head;
		while( node != null  )
		{
			if(node.data.equals( e )) return node;
			node = node.next;
		}
		return  null;		
	}
	//获取元素索引
	@Override
	public int indexOf(Object obj) {
		ListNode<E> root = head;
		int index = -1;
		while( root != null ){
			index ++;
			if( root.data.equals( obj ) )
			{
				return index;
			}
			root = root.next;
		}
		return -1;
	}
		
	//迭代器内部类
	class ListIterator<E> implements Iterator<E>{
		
		private int nextIndex;		//下一个索引
		private ListNode<E> next;	//下一个未遍历的节点
		private ListNode<E> lastRef; //最后遍历的节点
		
		//从第index开始遍历
		public ListIterator(int index)
		{
			next = index >= size ? null : (ListNode<E>)getListNode( index );
			nextIndex = index;
		}
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public E next() {
			lastRef = next;
			next = next.next;
			nextIndex ++;
			return lastRef.data;
		}

		@Override
		public boolean remove() {
			if( lastRef != null )
			{
				LinkedList.this.remove( lastRef.data );
				lastRef = null;
				nextIndex --;
				return true;
			}
			return false;
		}
		
	}
	
	//节点内部类
	private static class ListNode<E>{
		public E data;	//数据
		public ListNode<E> next;	//下一个
		public ListNode<E> previous;//上一个
		
		public ListNode( E data )
		{
			this.data = data;
			this.next = null;
			this.previous = null;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public ListNode<E> getNext() {
			return next;
		}

		public void setNext(ListNode<E> next) {
			this.next = next;
		}

		public ListNode<E> getPrevious() {
			return previous;
		}

		public void setPrevious(ListNode<E> previous) {
			this.previous = previous;
		}
		
		
	}
}
