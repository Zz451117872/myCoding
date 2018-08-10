<<<<<<< HEAD
package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicReference;

import com.multiThread.concurrency.lock._Lock;

/*
 * 无界队列锁，使用一个链表来组织线程 
 */
public class CLHLock implements _Lock{
	// 原子变量指向队尾 
	private AtomicReference<LockNode> tail;
	private ThreadLocal<LockNode> myNode;
	private ThreadLocal<LockNode> preNode;
	
	public CLHLock(){
		tail = new AtomicReference<CLHLock.LockNode>(new LockNode());
		myNode = new ThreadLocal<LockNode>(){
			@Override
			protected LockNode initialValue() {
				return new LockNode();
			}
		};
		preNode = new ThreadLocal<LockNode>(){
			@Override
			protected LockNode initialValue() {
				return null;
			}
		};
	}
	
	@Override
	public void lock() {
		LockNode node = myNode.get();
		node.lock = true;
		LockNode pre = tail.getAndSet(node);
		preNode.set(pre);
		// volatile变量，能保证锁释放及时通知  
        // 只对前一个节点的状态自旋，减少缓存一致性流量 
		while(pre.lock){
			
		}
	}

	@Override
	public void unlock() {
		LockNode current = myNode.get();
		current.lock = false;
		 //把myNode指向preNode，目的是保证同一个线程下次还能使用这个锁，
			//因为myNode原来指向的节点有它的后一个节点的preNode引用  
         //防止这个线程下次lock时myNode.get获得原来的节点  
		myNode.set(preNode.get());
	}
	
	private static class LockNode{
		volatile boolean lock;
	}

}
=======
package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicReference;

import com.multiThread.concurrency.lock._Lock;

/*
 * 无界队列锁，使用一个链表来组织线程 
 */
public class CLHLock implements _Lock{
	// 原子变量指向队尾 
	private AtomicReference<LockNode> tail;
	private ThreadLocal<LockNode> myNode;
	private ThreadLocal<LockNode> preNode;
	
	public CLHLock(){
		tail = new AtomicReference<CLHLock.LockNode>(new LockNode());
		myNode = new ThreadLocal<LockNode>(){
			@Override
			protected LockNode initialValue() {
				return new LockNode();
			}
		};
		preNode = new ThreadLocal<LockNode>(){
			@Override
			protected LockNode initialValue() {
				return null;
			}
		};
	}
	
	@Override
	public void lock() {
		LockNode node = myNode.get();
		node.lock = true;
		LockNode pre = tail.getAndSet(node);
		preNode.set(pre);
		// volatile变量，能保证锁释放及时通知  
        // 只对前一个节点的状态自旋，减少缓存一致性流量 
		while(pre.lock){
			
		}
	}

	@Override
	public void unlock() {
		LockNode current = myNode.get();
		current.lock = false;
		 //把myNode指向preNode，目的是保证同一个线程下次还能使用这个锁，
			//因为myNode原来指向的节点有它的后一个节点的preNode引用  
         //防止这个线程下次lock时myNode.get获得原来的节点  
		myNode.set(preNode.get());
	}
	
	private static class LockNode{
		volatile boolean lock;
	}

}
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
