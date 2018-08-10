<<<<<<< HEAD
package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.TimeUnit;

import com.multiThread.concurrency.lock._TryLock;

/*
 * 时限队列锁，支持tryLock超时操作 
 * QNode维护一个指针preNode指向前一个节点。当preNode == AVAILABLE表示已经释放锁。当preNode == null表示等待锁 
 * tail维护一个虚拟链表,通过tail.getAndSet方法获得前一个节点,并在前一个节点自旋,当释放锁时前一个节点的preNode == AVAIABLE，自动通知后一个节点获取锁 
 * 当一个节点超时或者被中断，那么它的前驱节点不为空。后续节点看到它的前驱节点不为空，并且不是AVAILABLE时，知道这个节点退出了，就会跳过它 
 * 当节点获得锁，进入临界区后，它的前驱节点可以被回收 
 */
public class TimeoutLock implements _TryLock{

	@Override
	public void lock() {
		
	}

	@Override
	public void unlock() {
		
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

}
=======
package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.TimeUnit;

import com.multiThread.concurrency.lock._TryLock;

/*
 * 时限队列锁，支持tryLock超时操作 
 * QNode维护一个指针preNode指向前一个节点。当preNode == AVAILABLE表示已经释放锁。当preNode == null表示等待锁 
 * tail维护一个虚拟链表,通过tail.getAndSet方法获得前一个节点,并在前一个节点自旋,当释放锁时前一个节点的preNode == AVAIABLE，自动通知后一个节点获取锁 
 * 当一个节点超时或者被中断，那么它的前驱节点不为空。后续节点看到它的前驱节点不为空，并且不是AVAILABLE时，知道这个节点退出了，就会跳过它 
 * 当节点获得锁，进入临界区后，它的前驱节点可以被回收 
 */
public class TimeoutLock implements _TryLock{

	@Override
	public void lock() {
		
	}

	@Override
	public void unlock() {
		
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

}
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
