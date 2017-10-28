package com.dataStructure.apply.sort.other;

public class SingleList {
	private Node head= null;
	
	//倒至列表前k位
	public void reverse(int k)
	{
		if(k<0 || k>length()) {
			System.out.println("输入参数不合法");
			return;
		}
		Node temp = this.head;
		Node t1=temp.next;
		Node t2=null;
		while(k > 1 && t1!=null)
		{
			t2 =t1.next;
			t1.next = null;
			t1.next = temp;
			temp = t1;
			t1 = t2;
			k--;
		}
		this.head.next = null;
		this.head.next = t1;
		this.head = temp;
	}
	//排序
	public void sort()
	{
		if(this.head == null) return;
		Node temp = this.head;
		int i,j;
		int len = length();
		Node node ;
		for(i=0; i<len-1; i++)
		{
			for(j=0,node = temp; j<len-1-i&&node.next!=null; j++,node=node.next)
			{
				if(node.data > node.next.data)
				{
					int val = node.data;
					node.data = node.next.data;
					node.next.data = val;
				}
			}
		}
	}
	
	//列表的长度
	public int length()
	{
		if(this.head == null) return 0;
		Node temp = this.head;
		int length =0;
		while(temp != null)
		{
			length++;
			temp = temp.next;
		}
		return length;
	}
	//遍历
	public void display(){
		if(this.head == null )
		{
			System.out.println("没有什么可以输出的");
			return;
		}
		Node temp = this.head;
		while(temp != null)
		{
			System.out.print(temp.data+"->");
			temp = temp.next;
		}
		return;
	}
	//添加节点
	public void add(int data)
	{
		Node n = new Node(data,null);
		if(this.head == null)
		{
			this.head = n;
		}else{
			addNode(n);
		}
	}
	
	private void addNode(Node n) {
		if(this.head == null) this.head = n;
		Node temp = this.head;
		while(temp.next !=null)
		{
			temp = temp.next;
		}
		temp.next = n;
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
		public Node(int data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		
	}
}
