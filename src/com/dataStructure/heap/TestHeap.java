package com.dataStructure.heap;

import java.util.Random;

public class TestHeap {
	
	public static void main(String[] str)
	{
		Random r = new Random();
//		MinHeap<People> minHeap = new MinHeap<People>(20);
//		MaxHeap<People> maxHeap = new MaxHeap<People>(20);
//		IndexMaxHeap<People> indexMaxHeap = new IndexHeap<People>(20);
		IndexMinHeap<People> indexMinHeap = new IndexMinHeap<People>(5);
//		People[] peoples = new People[20];
		
			People p = new People("zhang1",77);
			indexMinHeap.push(p,1);
			p = new People("zhang2",33);
			indexMinHeap.push(p,3);
			p = new People("zhang3",89);
			indexMinHeap.push(p,4);
			p = new People("zhang4",58);
			indexMinHeap.push(p,2);
			p = new People("zhang5",12);
			indexMinHeap.push(p,0);
		
			indexMinHeap.change(new People("zhang4",100),3);
		for(int i=0; i< 5; i++)
		{
			p = indexMinHeap.pop();
			System.out.println(p.toString());
		}
		
	}
	
}

class People implements Comparable<People>{
	private int age;
	private String name;
	
	public People(String name,int age)
	{
		this.name = name;
		this.age = age;
	}

	public String toString()
	{
		return this.name+" : "+this.age;
	}
	@Override
	public int compareTo(People p) {
//		if(p == null) return 10;
		
		return this.age - p.age;
	}
	
}
