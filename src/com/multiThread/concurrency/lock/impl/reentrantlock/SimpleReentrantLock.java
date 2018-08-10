<<<<<<< HEAD
package com.multiThread.concurrency.lock.impl.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.multiThread.concurrency.lock._Lock;

public class SimpleReentrantLock implements _Lock{

	// 指向已经获得锁的线程  
    private volatile Thread exclusiveOwnerThread;  
      
    // 记录获取了同一个锁的次数  
    private volatile int holdCount;  
      
    private final Lock lock;  
      
    // 是否是自己获得锁的条件  
    private final Condition isCountZero;  
	
    
    public SimpleReentrantLock()
    {
    	lock = new ReentrantLock();  
        isCountZero = lock.newCondition();  
        holdCount = 0;
    }
	
	@Override
	public void lock() {
		lock.lock();
		try{
			Thread thread = Thread.currentThread();
			if(thread == exclusiveOwnerThread)
			{
				System.out.println(thread.getName()+"重入成功："+this.holdCount);
				this.holdCount ++;
				return;
			}
			while(this.holdCount != 0)
			{
				try {
					System.out.println("即将进入等待"+thread.getName());
					this.isCountZero.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("获取锁："+thread.getName());
			this.exclusiveOwnerThread = thread;
			this.holdCount ++;
		}finally{
			lock.unlock();
		}
	}

		
	@Override
	public void unlock() {
		lock.lock();
		try{
			this.holdCount --;
			if(this.holdCount == 0)
			{
				this.isCountZero.signalAll();
			}
		}finally{
			lock.unlock();
		}
	}

}
=======
package com.multiThread.concurrency.lock.impl.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.multiThread.concurrency.lock._Lock;

public class SimpleReentrantLock implements _Lock{

	// 指向已经获得锁的线程  
    private volatile Thread exclusiveOwnerThread;  
      
    // 记录获取了同一个锁的次数  
    private volatile int holdCount;  
      
    private final Lock lock;  
      
    // 是否是自己获得锁的条件  
    private final Condition isCountZero;  
	
    
    public SimpleReentrantLock()
    {
    	lock = new ReentrantLock();  
        isCountZero = lock.newCondition();  
        holdCount = 0;
    }
	
	@Override
	public void lock() {
		lock.lock();
		try{
			Thread thread = Thread.currentThread();
			if(thread == exclusiveOwnerThread)
			{
				System.out.println(thread.getName()+"重入成功："+this.holdCount);
				this.holdCount ++;
				return;
			}
			while(this.holdCount != 0)
			{
				try {
					System.out.println("即将进入等待"+thread.getName());
					this.isCountZero.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("获取锁："+thread.getName());
			this.exclusiveOwnerThread = thread;
			this.holdCount ++;
		}finally{
			lock.unlock();
		}
	}

		
	@Override
	public void unlock() {
		lock.lock();
		try{
			this.holdCount --;
			if(this.holdCount == 0)
			{
				this.isCountZero.signalAll();
			}
		}finally{
			lock.unlock();
		}
	}

}
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
