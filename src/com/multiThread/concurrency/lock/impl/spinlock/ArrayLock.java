package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

import com.multiThread.concurrency.lock._Lock;

/*
 * 有界队列的队列锁
 * 缺点是得预先知道线程的规模n，所有线程获取同一个锁的次数不能超过n
 */
public class ArrayLock implements _Lock{
	// 使用volatile数组来存放锁标志， flags[i] = true表示可以获得锁 
	private volatile boolean[] flags;
	// 指向新加入的节点的后一个位置
	private AtomicInteger tail;
	// 总容量  
	private final int capacity;
	//线程对应的锁标志
	private ThreadLocal<Integer> slotIndex = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		};
	};
	
	public ArrayLock(int capacity)
	{
		this.capacity = capacity;
		this.flags = new boolean[capacity];
		this.tail = new AtomicInteger(0);
		flags[0] = true; //默认第一个锁是可以获取的
	}
	
			
	@Override
	public void lock() { //不用考虑锁重入问题		
		int slot = tail.getAndIncrement() % capacity;
		slotIndex.set(slot);
		//flags 返回true时，表示获取到锁，退出自旋
		while(!flags[slot])
		{
			
		}
	}

	@Override
	public void unlock() {
		int slot = slotIndex.get();//获取当前线程对应的锁标志位
		flags[slot] = false;		//将对应的标志位置为false，且将下一个标志位置为true
		flags[(slot+1) % capacity] = true;
	}

}
