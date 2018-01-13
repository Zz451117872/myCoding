package com.dataStructure.heap;

//最小堆
public class MinHeap<T extends Comparable<T>> {
		private Comparable[] arr ;
		private int count;
		
		public MinHeap(int count)
		{	
			this.arr =  new Comparable[count ];
			this.count = 0;
		}
		
		public void push(T data)
		{
			if(this.count > this.arr.length) return;
			if(isFull()) return;
			if(contains(data)) return;
			
			arr[count] = data;			
			shiftDown(count);
			count ++;
		}
		public int size()
		{
			return this.count;
		}
		public boolean isEmpty()
		{
			return this.count == 0;
		}
		
		public boolean isFull()
		{
			return this.count == this.arr.length;
		}
		
		private boolean contains(T data) 
		{
			for(int i=0; i < this.count; i++)
			{
				if(this.arr[i].equals(data))
				{
					return true;
				}
			}
			return false;
		}

		public T pop()
		{
			if(isEmpty()) return null;
			
			T data = (T) arr[0];
			swap(arr,0,count -1);
			count--;
			shiftUp(0);
			return data;
		}
		
		public void shiftUp(int parent)
		{
			int left = leftChild(parent);
			int right = rightChild(parent);
			int small = parent;
			if(left != -1)
			{
				if(arr[left].compareTo(arr[small]) < 0)
				{
					small = left;
				}
			}
			if(right != -1)
			{
				if(arr[right].compareTo(arr[small]) < 0)
				{
					small = right;
				}
			}
			if(small != parent)
			{
				swap(arr,small,parent);
				shiftUp(small);
			}			
		}
		
		public void shiftDown(int child)
		{
			int parent = parentNode(child);
			if(parent != -1)
			{
				if(arr[child].compareTo(arr[parent]) < 0)
				{
					swap(arr,child,parent);
					shiftDown(parent);
				}
			}
		}
		
		public void display()
		{
			for(int i=0; i<this.count; i++)
			{
				System.out.println(this.arr[i].toString());
			}
		}
			

		private void swap(Comparable[] arr, int child, int parent) 
		{
			Comparable temp = arr[child];
			arr[child] = arr[parent];
			arr[parent]= temp;
		}
		public static  void sort(Comparable[] data)
		{
			MinHeap minHeap = heapify(data);
			for(int i = 0; i < data.length; i++)
			{
				data[i] = minHeap.pop();
			}
		}
		
		public MinHeap(Comparable[] data,int length)
		{
			this.arr = new Comparable[length];
			this.count = length;
			for(int i=0; i<length; i++)
			{
				this.arr[i] = data[i];
			}
			for(int i=(this.arr.length - 2) / 2 ; i >= 0; i--)
			{
				shiftUp(i);
			}
		}
		
		public static <T>  MinHeap heapify(Comparable<T>[] data)
		{
			return new MinHeap(data,data.length);
		}
		
		public int parentNode(int child)
		{
			return (child - 1)/2 >= 0 ? (child - 1)/2 : -1;
		}	
		public int leftChild(int parent)
		{
			return parent*2 +1 < this.count ? parent*2+1 : -1;
		}
		public int rightChild(int parent)
		{
			return parent*2 +2 < this.count ? parent*2 +2: -1;
		}
}
