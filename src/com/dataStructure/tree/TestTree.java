package com.dataStructure.tree;

import java.util.Random;

import com.dataStructure.tree.binary.RBNode;
import com.dataStructure.tree.binary.RedBlockTree;

public class TestTree {

	public static void main(String[] str)
	{
		Random r = new Random();
//		BinarySerachTree<Integer> tree = new BinarySerachTree<Integer>();
		RedBlockTree<Integer> tree = new RedBlockTree<Integer>();
		for(int i=0; i<10; i++)
		{
			RBNode<Integer> node = new RBNode<Integer>(r.nextInt(100));
			tree.insert(tree, node);
			tree.inorder(tree.root);System.out.println("");
			System.out.println("root:"+tree.root.key);
		}
	}
}
