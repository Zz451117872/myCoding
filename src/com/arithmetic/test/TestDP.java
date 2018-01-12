package com.arithmetic.test;

import java.util.Random;

import org.junit.Test;

import com.arithmetic.thought.DP;

public class TestDP {

	static Random random = new Random();
	
	@Test
	public void bottom_up_cut_test()
	{
		//钢条尺寸对应的价格
		int[] p = new int[]{0,1,3,5,7,-1,-1,-1,15,-1,18};
		for(int i=0; i<10; i++)
		{
			int length = random.nextInt(30);
			System.out.println((i+1)+" "+length+" 米长钢条最大收益："+DP.bottom_up_cut(p, length));
		}
	}
	
	@Test
	public void memoized_cut_rod_test()
	{
		//钢条尺寸对应的价格
		int[] p = new int[]{0,1,3,5,7,-1,-1,-1,15,-1,18};
		for(int i=0; i<10; i++)
		{
			int length = random.nextInt(30);
			System.out.println((i+1)+" "+length+" 米长钢条最大收益："+DP.memoized_cut_rod(p, length));
		}
	}
	
	@Test
	public void loadMaxSpace_test()
	{
		int[] goods = new int[]{1,5,10,16};
		for(int i=0; i<10; i++)
		{
			int space = random.nextInt(20);
			System.out.println(space+" 空间最多可以放："+DP.loadMaxSpace(goods, space));
		}
	}
	
	@Test
	public void maxLoadValue_test()
	{
		int[] goods = new int[]{1,5,10,16};
		int[] values = new int[]{1,2,5,10};
		for(int i=0; i<10; i++)
		{
			int space = random.nextInt(20);
			System.out.println(space+" 空间可装入最大价值："+DP.maxLoadValue(space,goods,values));
		}
	}
	
	@Test
	public void down_up_coinValuePlay_test()
	{
		int[] values = new int[]{2,8,3,8,6,3,4,3,6,9,4,5,3,2,6,4,8,5,3,1,6,9,7,8,5,4,3};
		System.out.println(DP.down_up_coinValuePlay(values));
	}
	
	@Test
	public void coinPlayByDynamic_test()
	{
		for(int i=0; i<5; i++)
		{
			int count = random.nextInt(20);
			System.out.println("coinPlayByDynamic_test:"+count + " 个硬币 " +DP.coinPlayByDynamic(count));
		}
	}
	
	@Test
	public void down_up_coinPlay_test()
	{
		for(int i=0; i<5; i++)
		{
			int count = random.nextInt(20);
			System.out.println("down_up_coinPlay_test:"+count + " 个硬币 " +DP.coinPlayByDynamic(count));
		}
	}
	
	@Test
	public void coinPlayByRecursion_test()
	{
		for(int i=0; i<5; i++)
		{
			int count = random.nextInt(20);
			System.out.println("coinPlayByRecursion_test:"+count + " 个硬币 " +DP.coinPlayByDynamic(count));
		}
	}
}
