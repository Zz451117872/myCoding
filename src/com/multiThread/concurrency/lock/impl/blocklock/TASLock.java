package com.multiThread.concurrency.lock.impl.blocklock;

import java.util.concurrent.atomic.AtomicBoolean;

import com.multiThread.concurrency.lock._Lock;

public class TASLock implements _Lock{

	private AtomicBoolean lock;
	
	public TASLock()
	{
		lock = new AtomicBoolean(false);
	}
	
	@Override
	public  void lock() {
		while(lock.getAndSet(true))
		{
		}
	}

	@Override
	public synchronized void unlock() {
		
	}

}
