package com.dataStructure.tree.binary;

import java.util.Random;

public class TestTree {

	public static void main(String[] str)
	{
		Random r = new Random();
//		BinarySerachTree<Integer> tree = new BinarySerachTree<Integer>();
		RedBlockTree<Integer> tree = new RedBlockTree<Integer>();
		for(int i=0; i<500; i++)
		{
			RBNode<Integer> node = new RBNode<Integer>(r.nextInt(1000));
			tree.insert(tree, node);
		}
		System.out.println("root:"+tree.root.key);
		tree.balanceCheck(tree);
		
		int success = 0;
		int faild = 0;
		for(int i=0; i<1000; i++)
		{
			boolean result =tree.delete(tree, tree.search(tree.root, r.nextInt(1000)));
			if(result)
			{
				success ++;
			}else
			{
				faild ++;
			}
		}
		System.out.println("success:"+success+"  faild:"+faild);
		
		System.out.println("root:"+tree.root.key+":"+tree.root.color);
		tree.balanceCheck(tree);
	}
}
