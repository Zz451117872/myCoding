package com.arithmetic.test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.arithmetic.exercise.Trees;
import com.dataStructure.tree.binary.BinarySerachTree;
import com.dataStructure.tree.binary.BinarySerachTreeNode;

public class TestTrees {

	static BinarySerachTree<Integer> tree;
	static Random random = new Random();
	
	@BeforeClass
	public static void before()
	{
		System.out.println("----before-------------------------------------");
		tree = new BinarySerachTree<Integer>();
		for(int i=0; i<20; i++)
		{
			tree.insert(random.nextInt(200));
		}
		tree.inorder(tree.root);
		System.out.println("");
	}
	
	@Test
	public void range()
	{
		System.out.println("----range-------------------------------------");
		List<Integer> result = Trees.range(tree, 90, 120);
		printIntegerList(result);	
	}
	@Test
	public void handler()
	{
		System.out.println("----handler-------------------------------------");
		int[] postorder = new int[12];
		Trees.handler(0, 11, 0, 11, 0, 11, postorder);
		System.out.println(Arrays.toString(postorder));
		
	     int[] pre = {21,12,10,9,4,24,44,33,25,29,53,76};
		 int[] ino = {4,9,10,12,21,24,25,29,33,44,53,76};
		 int[] post = new int[12];
		 Trees.handler(pre,ino,post,0,11);
		 System.out.println(Arrays.toString(postorder));
	}
	
	
	private void printIntegerList(List<Integer> result)
	{
		if(result != null && !result.isEmpty())
		{
			for(int i=0; i<result.size(); i++)
			{
				System.out.print(result.get(i)+" ");
			}
			System.out.println("");
		}
	}
}
