//package com.cn.sort;
//
//import java.util.Random;
//
//import com.cn.entry.Person;
//import com.cn.entry.PersonNode;
//
//public class BucketSort {
//	/*
//	 * 桶排序：使用数组加链表的方式实现
//	 * 有N个人，但人的年龄在100以内，则人的年龄有101种可能。
//	 * 遍历所有的人，把年龄相同 的人放在一个桶内，再遍历所有桶，则所有人按年龄排序
//	 */
//	
//	public  void sort(Person[] persons,int val)
//	{
//		PersonNode[] bucket = new PersonNode[val];
//		for(int i=0; i<persons.length ; i++)
//		{
//			Person p = persons[i];
//			int age = p.getAge();
//			PersonNode pn = new PersonNode(p,null);
//			if(bucket[age] == null)
//			{
//				bucket[age] =pn;
//			}else{
//				PersonNode temp = bucket[age];
//				while( temp.getNext() != null )
//				{
//					temp = temp.getNext();
//				}								
//				temp.setNext(pn);						
//			}
//		}
//		int value =0;
//		for(int i=0; i<bucket.length; i++)
//		{
//			PersonNode pn = bucket[i];
//			while(pn !=null)
//			{
//				persons[value] = pn.getDate();
//				value++;
//				pn = pn.getNext();
//			}
//		}
//	}
//}
