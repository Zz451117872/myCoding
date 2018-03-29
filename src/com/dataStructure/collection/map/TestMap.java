package com.dataStructure.collection.map;

import com.dataStructure.collection.Iterator;
import com.dataStructure.collection.entry.Person;
import com.dataStructure.collection.map.Map.Entry;
import com.dataStructure.collection.set.Set;

public class TestMap {
	public static void main(String[] str){
		Person p1 = new Person("zhangjie1",34);
		Person p2 = new Person("zhangjie2",24);
		Person p3 = new Person("zhangjie3",44);
		Person p4 = new Person("zhangjie4",54);
		Person p5 = new Person("zhangjie5",14);
		Person p6 = new Person("zhangjie6",84);
		Person p7 = new Person("zhangjie7",4);
		
		//HashMap<String,Person> map = new HashMap<String,Person>();
		LinkedHashMap<String,Person> map = new LinkedHashMap<String,Person>();
		map.put(p1.getName(), p1);
		map.put(p2.getName(), p2);
		map.put(p3.getName(), p3);
		map.put(p4.getName(), p4);
		map.put(p5.getName(), p5);
		map.put(p6.getName(), p6);
		map.put(p7.getName(), p7);
		map.put(p7.getName(), p1);
		System.out.println(map.size());
		Set<String> keys = map.keySet();
		Iterator<String> i = keys.iterater();
		while( i.hasNext() )
		{
			System.out.println(i.next());;
			//i.remove();
		}
		System.out.println(map.size());
		Set<Entry<String,Person>> entrys = map.entrySet();
		Iterator<Entry<String,Person>> i1 = entrys.iterater();
		while( i1.hasNext() )
		{
			Entry<String,Person> entry = i1.next();
			System.out.println(entry.getK()+" : "+entry.getV());;
			//i.remove();
		}
		System.out.println(map.size());
		
		map.remove( p1.getName() );
		map.remove( p2.getName() );
		map.remove( p3.getName() );
		map.remove( p4.getName() );
		System.out.println(map.size());
		
		entrys = map.entrySet();
		i1 = entrys.iterater();
		while( i1.hasNext() )
		{
			Entry<String,Person> entry = i1.next();
			System.out.println(entry.getK()+" : "+entry.getV());;
			//i.remove();
		}
	}
}
