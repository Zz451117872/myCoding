package com.dataStructure.tree;

import org.junit.Test;

import com.dataStructure.tree.hasfman.HasfmanTree;

public class TestHasfman {

	@Test
	public void hasfman()
	{
		String sample = "alksjhfgdalkfhjsaghjslaksajhadsghdsalakdhsjakjhfgdh";
		HasfmanTree tree = new HasfmanTree(sample);
		tree.display(tree.getRoot());
		String msg = "asjhgdfghdfffffffffffffdf";
		String code = tree.encode(msg);
		System.out.println("加密后的消息为："+code);
		String message = tree.decode(code);
		System.out.println("解码后的消息为："+message);
	}
}
