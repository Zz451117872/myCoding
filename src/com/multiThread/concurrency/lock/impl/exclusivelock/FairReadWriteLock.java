package com.multiThread.concurrency.lock.impl.exclusivelock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.multiThread.concurrency.lock._Lock;
import com.multiThread.concurrency.lock._ReadWriterLock;

public class FairReadWriteLock implements _ReadWriterLock{

	private final Lock lock;
	private final Condition existWriterLock;
	private final Condition existReadLock;
	private final _Lock readLock;
	private final _Lock writerLock;	
	private volatile boolean write; 
	private volatile int readAccquired;      
    private volatile int readReleased;  
	
    public FairReadWriteLock()
    {
    	lock = new ReentrantLock(true);
    	existWriterLock = lock.newCondition();
    	existReadLock = lock.newCondition();
    	write = false;
    	this.readLock = new ReadLock();
    	this.writerLock = new writerLock();
    	readAccquired = 0;
    	readReleased = 0;
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
				readAccquired ++;
			}finally{
				lock.unlock();
			}
		}

		@Override
		public void unlock() {
			lock.lock();
			try{
				readReleased ++;
				if(readReleased == readAccquired)
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
				while(write)
				{
					try {
						existWriterLock.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				write = true;
				while(readAccquired != readReleased)
				{
					try {
						existReadLock.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}finally{
				lock.unlock();
			}
		}

		@Override
		public void unlock() {
			
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
