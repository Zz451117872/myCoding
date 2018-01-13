package com.multiThread.concurrency.lock;

import java.util.concurrent.TimeUnit;

public interface _TryLock {
	void lock();
	void unlock();
	boolean tryLock();  	  
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;  
}
