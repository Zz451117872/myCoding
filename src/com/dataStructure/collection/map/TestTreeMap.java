package com.dataStructure.collection.map;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.dataStructure.collection.entry.Person;

public class TestTreeMap {
	public static void main(String[] str)
	{
		Person p1 = new Person("zhangjie1",34);
		Person p2 = new Person("zhangjie2",24);
		Person p3 = new Person("zhangjie3",44);
		Person p4 = new Person("zhangjie4",54);
		Person p5 = new Person("zhangjie5",14);
		Person p6 = new Person("zhangjie6",84);
		Person p7 = new Person("zhangjie7",4);
		Person p8 = new Person("zhangjie8",15);
		
		java.util.TreeMap<Person,String> map = new java.util.TreeMap<Person,String>();
		map.put(p1, p1.getName());
		map.put(p2, p2.getName());
		map.put(p3, p3.getName());
		map.put(p4, p4.getName());
		map.put(p5, p5.getName());
		map.put(p6, p6.getName());
		map.put(p7, p7.getName());
		
		print(map);
		p2.setAge(0);
		print(map);
		map.put(p8, p8.getName());
		print(map);
	}
	
	public static void print(java.util.TreeMap<Person,String> map)
	{
		System.out.println(map.size());
		Set<Entry<Person, String>> entrys = map.entrySet();
		Iterator<Entry<Person, String>> i = entrys.iterator();
		while( i.hasNext() )
		{
			Entry<Person, String> entry = i.next();
			System.out.println(entry.getKey());
		}
	}
}
