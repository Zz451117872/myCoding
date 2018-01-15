package com.arithmetic.dissertation.sort;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SorterHandler implements InvocationHandler{

	private GeneralSort sorter;
	
	public SorterHandler(GeneralSort sorter)
	{
		this.sorter = sorter;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long t1 = System.currentTimeMillis();
		
		method.invoke(sorter, args);
		
		if(checkOrderly((Comparable[])args[0]))
		{
			System.out.print(method.getName()+" success! ");
		}else{
			System.out.print(method.getName()+" faild! ");
		}
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)/1000.00+"s");
		return proxy;
	}
	
	public <T extends Comparable<T>> boolean  checkOrderly(T[] data)
	{
		for(int i=0; i<data.length-1; i++)
		{
			if(data[i].compareTo(data[i+1]) > 0)
			{
				return false;
			}
		}
		return true;
	}

}
