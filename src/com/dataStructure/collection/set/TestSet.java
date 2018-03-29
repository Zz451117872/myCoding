package com.dataStructure.collection.set;

import com.dataStructure.collection.Iterator;
import com.dataStructure.collection.entry.Person;

public class TestSet {

	public static void main(String[] str)
	{
		Person p1 = new Person("zhangjie1",34);
		Person p2 = new Person("zhangjie2",24);
		Person p3 = new Person("zhangjie3",44);
		Person p4 = new Person("zhangjie4",54);
		Person p5 = new Person("zhangjie5",14);
		Person p6 = new Person("zhangjie6",84);
		Person p7 = new Person("zhangjie7",4);
		Person p8 = new Person("zhangjie7",14);
		
		HashSet<Person> persons = new HashSet<Person>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);
		persons.add(p6);
		persons.add(p7);
		persons.add(p7);
		persons.add(p8);
		
		Iterator<Person> i = persons.iterater();
		while( i.hasNext() )
		{
			System.out.println(i.next());
			i.remove();
		}
		System.out.println(persons.size());
	}
}
