package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import com.multiThread.concurrency.lock._Lock;

/*
 * 回退自旋锁，在测试-测试-设置自旋锁的基础上增加了线程回退，降低锁的争用 
 * 优点是在锁高争用的情况下减少了锁的争用，提高了执行的性能 
 * 缺点是回退的时间难以控制，需要不断测试才能找到合适的值，而且依赖底层硬件的性能，扩展性差 
 */
public class BackoffLock implements _Lock{

	private AtomicBoolean lock;
	private long delay;
	
	public BackoffLock(long delay)
	{
		lock = new AtomicBoolean(false);
		this.delay = delay;
	}
		
	@Override
	public void lock() {
		while(true)
		{
			while(lock.get()){};
			if(!lock.getAndSet(true))
			{
				return;
			}else{
				delay();
			}
		}
	}

	@Override
	public void unlock() {
		lock.set(false);
	}
	
	private void delay()
	{
		try {
			Thread.sleep(this.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


