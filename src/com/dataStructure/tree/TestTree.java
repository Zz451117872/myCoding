package com.dataStructure.tree;

import java.util.Random;

import org.junit.Test;

import com.dataStructure.tree.binary.BinarySerachTree;
import com.dataStructure.tree.binary.RBNode;
import com.dataStructure.tree.binary.RedBlockTree;

public class TestTree {

	Random random = new Random();
	
	@Test
	public void BinarySerachTree()
	{
		BinarySerachTree<Integer> tree = new BinarySerachTree<Integer>();
		for(int i=0; i<20; i++)
		{
			tree.insert(random.nextInt(100));
		}
		tree.inorder(tree.root);
		System.out.println("");
		tree.preorderByNoRecursion(tree.root);
		System.out.println("");
		tree.hierarchyTraverse(tree.root);
	}
}
