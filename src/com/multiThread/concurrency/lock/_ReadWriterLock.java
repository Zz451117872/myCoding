package com.multiThread.concurrency.lock;


public interface _ReadWriterLock {
	public _Lock readLock();
	public _Lock writerLock();
}
