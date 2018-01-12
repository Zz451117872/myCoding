package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import com.multiThread.concurrency.lock._Lock;

/*
 * 测试-设置锁，自旋时每次尝试获取锁
 * 1.getAndSet底层使用CAS来实现，一直在修改共享变量的值，会引发缓存一致性流量风暴
 */
public class TASLock implements _Lock{

	private AtomicBoolean lock;
	
	public TASLock()
	{
		lock = new AtomicBoolean(false);
	}
	
	@Override
	public void lock() {
		//当getAndSet返回false时，表示获取到了锁，退出自旋
		while(lock.getAndSet(true))
		{
			
		}
	}

	@Override
	public void unlock() {
		lock.set(false);
	}

}
