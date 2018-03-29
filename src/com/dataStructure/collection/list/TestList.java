package com.dataStructure.collection.list;

import com.dataStructure.collection.Iterator;
import com.dataStructure.collection.entry.Person;

public class TestList {
	
	public static void main(String[] str)
	{
		Person p1 = new Person("zhangjie1",34);
		Person p2 = new Person("zhangjie2",24);
		Person p3 = new Person("zhangjie3",44);
		Person p4 = new Person("zhangjie4",54);
		Person p5 = new Person("zhangjie5",14);
		Person p6 = new Person("zhangjie6",84);
		Person p7 = new Person("zhangjie7",4);
		
		//ArrayList<Person> list = new ArrayList<Person>();
		LinkedList<Person> list = new LinkedList<Person>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		Person p8 = new Person("zhangjie7",4);
		list.add(p8);
		
		print(list);
		
		list.add(3, p8);
		print(list);
		System.out.println(list.contains(new Person("zhangjie2",23)));
		list.remove( new Person("zhangjie7",4) );
		print(list);
		System.out.println(list.get(6).toString());
		System.out.println(list.indexOf( new Person("zhangjie7",4)));
		list.clear();
		print(list);
	
	}
	
	
	public static void print(List<Person> list)
	{
		System.out.println(list.size());
		Iterator<Person> i = list.iterater();
		while( i.hasNext() )
		{
			Person p = i.next();
			//i.remove();
			System.out.println( p.toString() );
		}
		System.out.println("======================");
	}
}