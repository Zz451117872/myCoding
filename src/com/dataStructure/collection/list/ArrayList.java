package com.dataStructure.collection.list;

import java.io.Serializable;

import com.dataStructure.collection.Iterator;

//数组实现list：有序可重复,查询速度快
public class ArrayList<E> implements List<E>,Serializable {
	private static final long serialVersionUID = 1L;

	private int size;
	private Object[] array; 
	
	public ArrayList()
	{
		this(5);
	}
	
	public ArrayList(int capcity)
	{
		capcity = capcity < 0 ? 10 : capcity;
		this.array = new Object[capcity];
	}
	
	@Override
	public int size() {
		return this.size;
	}

	//添加元素
	@Override
	public boolean add(E e) {
		if( isFull() && !expensionCapcity() )
		{
			return false;
		}
		this.array[ this.size ] = e;
		this.size ++ ;
		return true;
	}
	
	//判断是否满
	private boolean isFull()
	{
		return this.size == this.array.length;
	}
	
	//扩充底层数组容量
	private boolean expensionCapcity()
	{
		try{
			int newCapcity = this.array.length * 2 + 1;
			Object[] newArray = new Object[newCapcity];
			for(int i=0; i<this.array.length; i++ )
			{
				newArray[ i ] = this.array[ i ];
			}
			this.array = newArray;
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//删除元素
	@Override
	public boolean remove(Object e) {
		int index = this.indexOf( e );
		if( index != -1 )
		{
			for( int i=index; i<this.size; i++ ){
				this.array[ i ] = this.array[ i + 1 ];
			}
			this.size -- ;
			return true;
		}
		return false;
	}
	
	//删除元素
	@Override
	public boolean remove(int index) {
		if( index >=0 && index < size )
		{
			for( int i=index; i<this.size; i++ ){
				this.array[ i ] = this.array[ i + 1 ];
			}
			this.size -- ;
			return true;
		}
		return false;
	}

	//判断包含
	@Override
	public boolean contains(Object e) {
		return this.indexOf( e ) != -1;
	}
	
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public void clear() {
		this.array = new Object[ this.array.length ];
		this.size = 0;
	}
	
	//获取迭代器
	@Override
	public Iterator<E> iterater() {
		return new ListIterator<E>();
	}
	//添加元素
	@Override
	public boolean add(int index, E e) {
		if( isFull() && !expensionCapcity() ){
			return false;
		}
		if( index < size && index >= 0 ){
			for( int i=this.size - 1; i>=index; i-- )
			{
				this.array[ i+1 ] = this.array[ i ];
			}
			this.array[ index ] = e;
		}else{
			this.array[ size ] = e;
		}
		this.size++;
		return true;
	}
	//获取元素
	@Override
	public E get(int index) {
		if( index >= size || index < 0) return null;
		return (E) this.array[ index  ];
	}
	//获取元素下标
	@Override
	public int indexOf(Object obj) {
		if( !isEmpty() ){
			for( int i=0; i<this.size; i++ )
			{
				if( this.array[ i ].equals( obj ) ){
					return i;
				}
			}
		}
		return -1;
	}
	//迭代器内部类，不同的结构底层 对 迭代器的实现不一样，
	class ListIterator<E> implements Iterator<E>{
		private int currentIndex ; //当前元素下标
		private int lastRef = -1;	//最后访问下标
		
		@Override
		public boolean hasNext() {//判断是否有下一个未遍历的元素
			return size > this.currentIndex;
		}
		/*
		 * 在源码中，next , remove操作前，会检查容器中元素数量是否改变，若有改变则抛出异常
		 * ，在迭代器中删除元素导致元素数量变化，不会抛出异常。
		 * revome 操作 需要在 next 操作后才可使用，remove 也不可以连续调用2次
		 * (non-Javadoc)
		 * @see com.dataStructure.collection.Iterator#next()
		 */
		@Override
		public E next() { //获取下一个元素
			int index = this.currentIndex;
			this.currentIndex ++ ;
			this.lastRef = index;
			return (E) array[ index ];			
		}

		@Override
		public boolean remove() {//删除最后访问的元素
			if( this.lastRef > 0 ){
				ArrayList.this.remove( this.lastRef );
				this.currentIndex = this.lastRef;
				this.lastRef = -1;
				return true;
			}
			return false;
		}
		
	}
}
