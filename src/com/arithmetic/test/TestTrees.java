package com.arithmetic.test;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.dataStructure.tree.binary.BinarySerachTree;
import com.dataStructure.tree.binary.BinarySerachTreeNode;

public class TestTrees {

	static BinarySerachTree<Integer> tree;
	static Random random = new Random();
	
	@Before
	public void before()
	{
		tree = new BinarySerachTree<Integer>();
	}
	
	@Test
	public void initTree()
	{
		for(int i=0; i<50; i++)
		{
			tree.add(random.nextInt(200));
		}
		tree.inorder(tree.root);
	}
}
