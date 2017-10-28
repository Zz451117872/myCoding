package com.dataStructure.list;

import java.util.Random;

public class ListMerge {
	/*
	 * 合并 两个 排序链表，其实就是归并排序的 合并部分。
	 */
	
	public static ListNode mergeList(ListNode list1,ListNode list2)
	{
		ListNode node3 =new ListNode(Integer.MAX_VALUE,null);
		ListNode node1 = list1;
		ListNode node2 = list2;
		while(node1 != null && node2!= null)
		{
			if(node1.getData() <= node2.getData())
			{
				ListNode temp = node1;
				node1 = node1.getNext();
				addNode(node3,new ListNode(temp.getData(),null));
			}else{
				ListNode temp = node2;
				node2 = node2.getNext();
				addNode(node3,new ListNode(temp.getData(),null));
			}
		}
		while(node1 !=null)
		{
			ListNode temp = node1;
			node1 = node1.getNext();
			addNode(node3,new ListNode(temp.getData(),null));
		}
		while(node2 !=null)
		{
			ListNode temp = node2;
			node1 = node2.getNext();
			addNode(node3,new ListNode(temp.getData(),null));
		}
		return node3;
	}
	
	public static int length(ListNode root)
	{
		int count = 0;
		ListNode head = root;
		while(head != null)
		{
			count ++;
			head = head.getNext();
		}
		return count;
	}
	public static void sort(ListNode root)
	{
		ListNode head = root;
		ListNode temp = null;
		int length = length(root);
		int j =0;
		for(int i=0; i<length-1; i++)
		{			
			for(temp = head,j=0; temp!=null&&j<length-1-i; temp=temp.getNext(),j++)
			{
				if(temp.getData() > temp.getNext().getData())
				{
					int val = temp.getData();
					temp.setData(temp.getNext().getData());
					temp.getNext().setData(val);
				}
			}
		}
	}
	
	public static void main(String[] str)
	{
//		ListNode list1 = createList(10);
//		ListNode list2 = createList(5);
//		sort(list1);
//		sort(list2);
//		display(list1);
//		display(list2);
//		ListNode list3 = mergeList(list1,list2);
//		display(list3);
		
		ListNode root = createList(10);
		System.out.println("反转前：");
		display(root);
		listReverse(root,2,2);
		System.out.println("反转后：");
		display(root);
	}

	/*
	 * 反转链表，这里的逻辑相当的绕。
	 */
	private static void listReverse(ListNode root, int start, int end) {
		int index = 1;
		ListNode head = root;
		ListNode p = head;
		while(index < start && head!=null)
		{		//将指针定在要反转的对象上
			p = head;
			head = head.getNext();
			index ++;
		}
		ListNode bf = head;
		head = head.getNext();
		while(index < end && head!=null)
		{
			ListNode temp = bf;
			bf = head;
			head = head.getNext();
			p.getNext().setNext(head);
			bf.setNext(temp);
			index++;
		}
		p.setNext(bf);
	}

	private static void display(ListNode root)
	{
		if(root == null) return;
		ListNode head = root;
		while(head != null)
		{
			System.out.print(head.getData() + "->");
			head = head.getNext();
		}
		System.out.println("");
		return;
	}
	private static ListNode createList(int length) {
		ListNode root = null;
		Random r = new Random();
		for(int i=0; i<length; i++)
		{
			int val = r.nextInt(100)+1;
			ListNode node = new ListNode(val,null);
			if(root == null)
			{
				root = node;
			}else{
				addNode(root,node);
			}
		}
		return root;
	}

	private static void addNode(ListNode root, ListNode node) {
		if(root == null) 
		{
			root = node;
			return;
		}
		ListNode head = root;
		while(head.getNext()!= null)
		{
			head = head.getNext();
		}
		head.setNext(node);
		return;
	}
}
