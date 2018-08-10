<<<<<<< HEAD
package com.multiThread.concurrency.lock;

import java.util.concurrent.TimeUnit;

public interface _TryLock {
	void lock();
	void unlock();
	boolean tryLock();  	  
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;  
}
=======
package com.multiThread.concurrency.lock;

import java.util.concurrent.TimeUnit;

public interface _TryLock {
	void lock();
	void unlock();
	boolean tryLock();  	  
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;  
}
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
