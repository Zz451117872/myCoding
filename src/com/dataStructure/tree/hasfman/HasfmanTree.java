package com.dataStructure.tree.hasfman;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
/*
 * 哈夫曼树
 */
public class HasfmanTree {	
	//密码本：字符与哈夫曼编码的映射集合
	private HashMap<Character,String> codebook = new HashMap<Character,String>();
	private HasfmanNode root;  //哈夫曼树
	
	public HasfmanTree(String sample)
	{
		generetor(sample); //哈夫曼树生成器
		generateCodeBook(this.root);//密码本初始化
	}
	
	/*
	 * 哈夫曼树生成器
	 * 通过对样本串的分析得到字符的权重，初始化优先队列
	 */
	public void generetor(String sample)
	{	
		if(sample == null || "".equals(sample)) return;
				//HashMap 通常用来统计
		HashMap<Character,HasfmanNode> charMap = new HashMap<Character,HasfmanNode>();
		char[] charArray = sample.trim().toCharArray();
		for(int i=0; i<charArray.length; i++)
		{
			HasfmanNode code = charMap.get(charArray[i]);
			if(code == null)//若may中不存在则直接加入，若存在 则更新权重值
			{
				HasfmanNode temp = new HasfmanNode(charArray[i],1,null,null);
				charMap.put(charArray[i], temp);
			}else{
				code.setWeight(code.getWeight() + 1);
			}
		}					//优先队列
		PriorityQueue<HasfmanNode> queue = new PriorityQueue<HasfmanNode>(charMap.size(), new HasfmanNodeComparator());	
		
		Set<Entry<Character,HasfmanNode>> entrys = charMap.entrySet();
		Iterator<Entry<Character,HasfmanNode>> i =  entrys.iterator();
		while(i.hasNext())
		{
			Entry<Character,HasfmanNode> entry = i.next();
			HasfmanNode code = entry.getValue();
			queue.add(code);	//将map中的code元素压入优先队列
		}	
		builderHasfman(queue);	//	构造 哈夫曼树
	}	
	
	/*
	 * 构造哈夫曼树：弹出权值最小的2个节点，合成1个父节点则压入队列
	 */
	private void builderHasfman(PriorityQueue<HasfmanNode> queue) {		
		while(queue != null && !queue.isEmpty())
		{
			if(queue.size() == 1)
			{
				this.root  = (HasfmanNode) queue.poll();
				this.root.setCode("");
			}else{
				HasfmanNode firstMin = (HasfmanNode) queue.poll();
				HasfmanNode secondMin = (HasfmanNode) queue.poll();
				HasfmanNode parent = new HasfmanNode(firstMin.getWeight()+secondMin.getWeight(),firstMin,secondMin);
				queue.add(parent);
			}
		}	
	}

	/*
	 * 密码本初始化
	 * 与遍历二叉树差不多。
	 */
	public void generateCodeBook(HasfmanNode root)
	{
		if(root == null) return;
		if(root.getLeft() == null && root.getRight() == null)
		{				//是叶子节点（一个叶子节点对应一个哈夫曼编码），
			String code = root.getCode();
			this.codebook.put(root.getC(), code);
		}else{
			if(root.getLeft() != null)
			{			//给左子节点 更新编码
				HasfmanNode left = root.getLeft();
				left.setCode(root.getCode() + "0");
				generateCodeBook(left);
			}
			if(root.getRight() != null)
			{			//给右子节点 更新编码
				HasfmanNode right = root.getRight();
				right.setCode(root.getCode() + "1");
				generateCodeBook(right);
			}
		}
	}

	public void  display(HasfmanNode root)
	{
		if(root != null && root.getC() != null)
		{
			System.out.println("字符："+root.getC() + "  编码："+ root.getCode()+ "  权重："+root.getWeight());
		}
		if(root.getLeft() != null)
		{
			display(root.getLeft());
		}
		if(root.getRight() != null)
		{
			display(root.getRight());
		}
	}
	
	/*
	 * 解密功能，将二进制串解密为 字符串
	 */
	public  String decode(String code) {
		char[] codeArray = code.toCharArray();
		HasfmanNode head = this.root;
		String message = "";
		int index = 0;
		while( index <= codeArray.length)
		{
			if(head.getLeft() == null && head.getRight() == null)
			{
				message += head.getC();
				head = this.root;
			}else if(index < codeArray.length && codeArray[index] == '0'){
				head = head.getLeft();
				index++;
			}else if(index < codeArray.length && codeArray[index] == '1')
			{
				head = head.getRight();
				index++;
			}else{
				return message;
			}
		}
		return message;
	}

	
	/*
	 * 加密功能，将字符串加密为二进制串
	 */
	public  String encode(String msg) {
		char[] charArray = msg.trim().toCharArray();
		String encodeString = "";
		for(int i=0; i<charArray.length; i++)
		{
			String code = this.codebook.get(charArray[i]);
			if(code == null || code.equals(""))
			{
				System.out.println("输入消息不符合你大爷的条件");
				return "xx00";
			}
			encodeString += code;
		}
		return encodeString;
	}

	
	

	public HasfmanNode getRoot() {
		return root;
	}

		
}	
