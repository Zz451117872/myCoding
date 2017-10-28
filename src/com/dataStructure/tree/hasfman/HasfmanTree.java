package com.dataStructure.tree.hasfman;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class HasfmanTree {
	/*
	 * 哈夫曼编码
	 */
	//密码本：字符与哈夫曼编码的映射集合
	private HashMap<Character,String> codebook = new HashMap<Character,String>();
	private Code root;  //哈夫曼树
	
	public HasfmanTree(String sample)
	{
		generetor(sample); //哈夫曼树生成器
		generatorCodeBook(this.root);//密码本初始化
	}
	
	/*
	 * 哈夫曼树生成器
	 * 通过对样本串的分析得到字符的权重，初始化优先队列
	 */
	public void generetor(String sample)
	{
		HashMap<Character,Code> charMap = new HashMap<Character,Code>();
		char[] charArray = sample.trim().toCharArray();
		for(int i=0; i<charArray.length; i++)
		{
			Code code = charMap.get(charArray[i]);
			if(code == null)
			{
				Code temp = new Code(charArray[i],1,null,null);
				charMap.put(charArray[i], temp);
			}else{
				code.setWeight(code.getWeight() + 1);
			}
		}
		PriorityQueue<Code> queue = new PriorityQueue<Code>(charMap.size(), new CodeComparator());	
		initPriorityQueue(queue,charMap);
	}
	
	/*
	 * 初始化优先队列
	 * 将所有字符节点 压入 优先队列，再依次弹出2个最小权重字符节点合成父节点，并将父节点压入优先队列
	 */
	private void initPriorityQueue(PriorityQueue<Code> queue,HashMap<Character,Code> charMap) {
		Set<Entry<Character,Code>> entrys = charMap.entrySet();
		Iterator<Entry<Character,Code>> i =  entrys.iterator();
		while(i.hasNext())
		{
			Entry<Character,Code> entry = i.next();
			Code code = entry.getValue();
			queue.add(code);
		}	
		builderHasfman(queue);
	}

	private void builderHasfman(PriorityQueue<Code> queue) {
		while(!queue.isEmpty())
		{
		if(queue.size() == 1)
		{
			this.root  = (Code) queue.poll();
			this.root.setCode("#");
		}else{
			Code minCode1 = (Code) queue.poll();
			Code minCode2 = (Code) queue.poll();
			Code parent = new Code('#',minCode1.getWeight()+minCode2.getWeight(),minCode1,minCode2);
			queue.add(parent);
			}
		}	
	}

	/*
	 * 密码本初始化
	 * 与遍历二叉树差不多。
	 */
	public void generatorCodeBook(Code root)
	{
		if(root.getLeft() == null && root.getRight() == null)
		{
			String code = root.getCode();
			this.codebook.put(root.getC(), code);
		}else{
			if(root.getLeft() != null)
			{
				Code left = root.getLeft();
				left.setCode(root.getCode() + "0");
				generatorCodeBook(left);
			}
			if(root.getRight() != null)
			{
				Code right = root.getRight();
				right.setCode(root.getCode() + "1");
				generatorCodeBook(right);
			}
		}
	}

	public void  display(Code root)
	{
		if(root != null)
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
	private  String decode(String code) {
		System.out.println("密钥："+code);
		char[] codeArray = code.toCharArray();
		Code head = this.root;
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
	private  String encode(String msg) {
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
			encodeString += code.substring(1);
		}
		return encodeString;
	}
	
	public static void main(String[] str)
	{
		String sample = "alksjhfgdalkfhjsaghjslaksajhadsghdsalakdhsjakjhfgdh";
		HasfmanTree tree = new HasfmanTree(sample);
		String msg = "asjhgdfghdfffffffffffffdf";
		String code = tree.encode(msg);
		System.out.println("加密后的消息为："+code);
		String message = tree.decode(code);
		System.out.println("解码后的消息为："+message);
//		tree.display(tree.root);
	}
}
