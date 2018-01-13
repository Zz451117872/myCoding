package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import com.multiThread.concurrency.lock._Lock;

/*
 * 测试-测试-设置锁
 * 在自旋尝试获取锁时，分为两步，第一步通过读操作来获取锁状态，
 * 当锁可获取时，第二步再通过CAS操作来尝试获取锁，减少了CAS的操作次数。
 */
public class TTASLock implements _Lock{

	private AtomicBoolean lock;
	
	public TTASLock()
	{
		lock = new AtomicBoolean(false);
	}
	
	@Override
	public void lock() {
		while(true)
		{
			//当get返回false时，表示可以获取锁，退出自旋
			while(lock.get()){}
			//当getAndSet返回false时，表示已获取到锁
			if(!lock.getAndSet(true))
			{
				return;
			}
		}
	}

	@Override
	public void unlock() {
		lock.set(false);
	}

}
