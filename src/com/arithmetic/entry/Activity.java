package com.arithmetic.entry;

public class Activity{
	public int id;
	public int start;
	public int end;
	
	public Activity(int id, int start, int end)
	{
		this.id = id;
		this.start = start;
		this.end = end;
	}
	
	public Activity(){}

	
	@Override
	public String toString() {
		return this.id +" :start:"+this.start+" end:"+this.end;
	}
}
