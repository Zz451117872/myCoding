package com.arithmetic.dissertation.sort;

import java.util.Random;

import com.arithmetic.dissertation.sort.other.Sort;



public class RadixSort implements Sort{
/*
 * 基数排序，感觉要比其他排序要复杂，
 * 由于主循环是基于 链表数组的，所以需要把输入数据封装成 链表数组。
 * 还需要创建一个临时的 链表数组，用于临时接收数据。
 */
	public void sort(int[] data, int start,int end,int[] room)
	{
		int length = end - start;
		Node[] nodes = new Node[data.length]; //对输入数据进行封装
		for(int i=0; i<nodes.length; i++)
		{
			nodes[i] = new Node(data[i]);
		}		
		Node[] result = new Node[10];  //临时 链表数组，用于接收数据
		
		for(int d=1; d<=length; d++) // 功能主循环，n位数排序 就循环n轮
		{
			for(int i=0; i<nodes.length ; i++)
			{
				Node node = nodes[i];
				while(node != null)
				{
					int temp = node.data;
					//获得一个数据的个位，百位，千位。
					int num = (temp%((int)Math.pow(10, d)))/((int)Math.pow(10, d-1));
					insertDataToList(result,num,temp);//将数据写入指定的桶中
					node = node.next;
				}				
			}
			if(d != length)
			{
				nodes = result;  //一轮排序完成后需要 把 结果集 赋值给输入集，并重置结果集。
				result = new Node[10];
			}
		}
		fillDataToArray(data,result);//将最后得到的结果集 输出
	}
	
	private void fillDataToArray(int[] data, Node[] nodes) {
		int index = 0;
		for(int i=0; i<nodes.length; i++)
		{
			Node node = nodes[i];
			while(index < data.length&&node != null)
			{
				data[index] = node.data;
				index++;
				node = node.next;
			}
		}
	}

	private  void insertDataToList(Node[] nodes, int num, int value) {
		if(nodes[num] == null)
		{
			nodes[num] = new Node(value);
		}else{
			Node node = nodes[num];
			while(node.next != null)
			{
				node = node.next;
			}
			node.next = new Node(value);
		}
	}
	
	class Node{
		private int data;
		private Node next;
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node(int data) {
			super();
			this.data = data;
			this.next = null;
		}
		
	}
}
