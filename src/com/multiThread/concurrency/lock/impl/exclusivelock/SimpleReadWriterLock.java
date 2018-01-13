package com.multiThread.concurrency.lock.impl.exclusivelock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.multiThread.concurrency.lock._Lock;
import com.multiThread.concurrency.lock._ReadWriterLock;

public class SimpleReadWriterLock implements _ReadWriterLock{

	private final Lock lock;
	private final Condition existWriterLock;
	private final Condition existReadLock;
	private final _Lock readLock;
	private final _Lock writerLock;	
	private volatile boolean write;     
    private volatile int readCount;
    
    public SimpleReadWriterLock()
    {
    	lock = new ReentrantLock();
    	existWriterLock = lock.newCondition();
    	existReadLock = lock.newCondition();
    	write = false;
    	readCount = 0;
    	this.readLock = new ReadLock();
    	this.writerLock = new writerLock();
    }
    
	private class ReadLock implements _Lock{

		@Override
		public void lock() {
			lock.lock();
			try{
				while(write)
				{
					try {
						existWriterLock.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				readCount ++;
			}finally{
				lock.unlock();
			}
		}

		@Override
		public void unlock() {
			lock.lock();
			try{
				readCount --;
				if(readCount == 0)
				{
					existReadLock.signalAll();
				}
			}finally{
				lock.unlock();
			}
		}
		
	}
	
	private class writerLock implements _Lock{

		@Override
		public void lock() {
			lock.lock();
			try{
				while(readCount > 0)
				{
					try {
						existReadLock.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				while(write)
				{
					try {
						existWriterLock.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				write = true;
			}finally{
				lock.unlock();
			}
		}

		@Override
		public void unlock() {
			lock.lock();
			try{
				write = false;
				existWriterLock.signalAll();
			}finally{
				lock.unlock();
			}
		}
		
	}
	
	@Override
	public _Lock readLock() {
		return this.readLock;
	}

	@Override
	public _Lock writerLock() {
		return this.writerLock;
	}

}
