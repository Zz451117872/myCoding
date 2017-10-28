package com.dataStructure.dynamicPlan;

import java.util.Arrays;
import java.util.Random;
////数塔问题
//从三角形的顶部 到底边，所经过的点的数字之和
public class POJ {
	
	int[][]  coords ;
	int[][] d;
	int n ;;
	
	public POJ(int x,int y)
	{
		Random r = new Random();
		coords = new int[x][y];
		d = new int[x][y];
		this.n = coords.length;
		for(int i=0; i<coords.length; i++)
		{
			for(int k=0; k<coords.length; k++)
			{
				coords[i][k] = r.nextInt(10);
				d[i][k] = coords[i][k];
			}
		}
		build();
	}
	
	public void build()
	{
		for(int i=n-1; i>=1; i--)
		{
			for(int k=1; k<=i; k++)
			{
				d[i-1][k-1] = Math.max(d[i][k-1], d[i][k]) + coords[i-1][k-1];
			}
		}
	}
	
	public int sum1(int x,int y)
	{
		if(d[x-1][y-1] >= 0)
		{
			return d[x-1][y-1];
		}
		return -100;
	}
	
	public int sum(int x,int y)
	{
		if(d[x-1][y-1] >= 0)
		{
			return d[x-1][y-1];
		}
		if(x == n)
		{
			return coords[x-1][y-1];
		}else{
			int left = sum(x+1,y);
			int right = sum(x+1,y+1);
			int max = Math.max(left, right)+coords[x-1][y-1];
			
			d[x-1][y-1] = max;
			return max;
		}
	}
	
	
	
	public static void main(String[] str)
	{
		POJ p = new POJ(20,20);
		for(int i=0; i<p.coords.length; i++)
		{
			System.out.println(Arrays.toString(p.coords[i]));
		}
		int max = p.sum(1, 1);
		int max1 = p.sum1(1, 1);
		System.out.println(max+":"+max1);
		for(int i=0; i<p.d.length; i++)
		{
			System.out.println(Arrays.toString(p.d[i]));
		}
		
	}
}
