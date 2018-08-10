<<<<<<< HEAD
package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicReference;

import com.multiThread.concurrency.lock._Lock;

/*
 *  无界队列锁，使用一个链表来组织线程
 */
public class MCSLock implements _Lock{

	// 原子变量指向队尾  
    private AtomicReference<LockNode> tail;
    
    private ThreadLocal<LockNode> myNode;
    
    public MCSLock()
    {
    	tail = new AtomicReference<LockNode>(null);  
    	myNode = new ThreadLocal<LockNode>(){  
            protected LockNode initialValue(){  
                return new LockNode();  
            }  
        };  
    }
	
	@Override
	public void lock() {
		LockNode node = myNode.get();  
        // CAS原子操作，保证原子性  
		LockNode preNode = tail.getAndSet(node);  
        // 如果preNode等于空，证明是第一个获取锁的  
        if(preNode != null){  
            node.lock = true;  
            preNode.next = node;  
            while(node.lock){  
                  
            }  
        }  
	}

	@Override
	public void unlock() {
		LockNode node = myNode.get();  
        if(node.next == null){  
            // CAS操作，判断是否没有新加入的节点  
            if(tail.compareAndSet(node, null)){  
                // 没有新加入的节点,直接返回  
                return;  
            }  
            // 有新加入的节点，等待设置链关系  
            while(node.next == null){  
                  
            }  
        }  
        // 通知下一个节点获取锁  
        node.next.lock = false;  
        // 设置next节点为空，为下次获取锁清理状态  
        node.next = null;  
	}

	private static class LockNode{
		volatile boolean lock;  
        volatile LockNode next;
	}
}
=======
package com.multiThread.concurrency.lock.impl.spinlock;

import java.util.concurrent.atomic.AtomicReference;

import com.multiThread.concurrency.lock._Lock;

/*
 *  无界队列锁，使用一个链表来组织线程
 */
public class MCSLock implements _Lock{

	// 原子变量指向队尾  
    private AtomicReference<LockNode> tail;
    
    private ThreadLocal<LockNode> myNode;
    
    public MCSLock()
    {
    	tail = new AtomicReference<LockNode>(null);  
    	myNode = new ThreadLocal<LockNode>(){  
            protected LockNode initialValue(){  
                return new LockNode();  
            }  
        };  
    }
	
	@Override
	public void lock() {
		LockNode node = myNode.get();  
        // CAS原子操作，保证原子性  
		LockNode preNode = tail.getAndSet(node);  
        // 如果preNode等于空，证明是第一个获取锁的  
        if(preNode != null){  
            node.lock = true;  
            preNode.next = node;  
            while(node.lock){  
                  
            }  
        }  
	}

	@Override
	public void unlock() {
		LockNode node = myNode.get();  
        if(node.next == null){  
            // CAS操作，判断是否没有新加入的节点  
            if(tail.compareAndSet(node, null)){  
                // 没有新加入的节点,直接返回  
                return;  
            }  
            // 有新加入的节点，等待设置链关系  
            while(node.next == null){  
                  
            }  
        }  
        // 通知下一个节点获取锁  
        node.next.lock = false;  
        // 设置next节点为空，为下次获取锁清理状态  
        node.next = null;  
	}

	private static class LockNode{
		volatile boolean lock;  
        volatile LockNode next;
	}
}
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
